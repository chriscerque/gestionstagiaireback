package net.ent.etrs.gestionstagiaire.security.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.security.services.MyUserDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@CommonsLog(topic = "SOUT")
public class JwtRequestFilter extends OncePerRequestFilter {

    //    @Autowired
    private MyUserDetailService myUserDetailService;

    //    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(MyUserDetailService myUserDetailService, JwtTokenUtil jwtTokenUtil) {
        //TODO SOUT
        log.trace("JwtRequestFilter constructor");
        this.myUserDetailService = myUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        log.trace("JwtRequestFilter / doFilterInternal");
        log.trace("request.getRequestURI() : " + request.getRequestURI());
        log.trace("request.getHeaderNames() : " + request.getHeaderNames());
//        request.getHeaderNames().asIterator().forEachRemaining(s -> log.trace(s));
        log.trace("----------------------------------------------------------------");
        log.trace("response : " + response);
        final String requestTokenHeader = request.getHeader("Authorization");
        log.trace("requestTokenHeader : " + requestTokenHeader);
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            log.trace("JwtRequestFilter / doFilterInternal 21");
            jwtToken = requestTokenHeader.substring(7);
            try {
                log.trace("JwtRequestFilter / doFilterInternal 22");
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.trace("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.trace("JWT Token has expired");
            } catch (UnsupportedJwtException e) {
                log.trace("JWT Token Unsupported");
            } catch (MalformedJwtException e) {
                log.trace("JWT Token Malformed");
            } catch (SignatureException e) {
                log.trace("JWT Token Bad Signature");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        log.trace("JwtRequestFilter / doFilterInternal 31 ");
        log.trace("username : " + username);
        // Once we get the token validate it.
        log.trace("SecurityContextHolder.getContext() : " + SecurityContextHolder.getContext());
        log.trace("SecurityContextHolder.getContext().getAuthentication() : " + SecurityContextHolder.getContext().getAuthentication());
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.trace("JwtRequestFilter / doFilterInternal 32 dans if");
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);
            log.trace("userDetails : " + userDetails);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                log.trace("jwtTokenUtil.validateToken(jwtToken, userDetails)");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        log.trace("JwtRequestFilter / doFilterInternal fin");
        chain.doFilter(request, response);


    }

}
