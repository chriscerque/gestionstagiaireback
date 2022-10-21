package net.ent.etrs.gestionstagiaire.config;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.ent.etrs.gestionstagiaire.controllers.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    public JwtRequestFilter(MyUserDetailService myUserDetailService, JwtTokenUtil jwtTokenUtil) {
//        //TODO SOUT
//        System.out.println("JwtRequestFilter constructor");
//        this.myUserDetailService = myUserDetailService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("JwtRequestFilter / doFilterInternal");
        System.out.println("request.getRequestURI() : " + request.getRequestURI());
        System.out.println("request.getHeaderNames() : " + request.getHeaderNames());
        request.getHeaderNames().asIterator().forEachRemaining(s -> System.out.println(s));
        System.out.println("----------------------------------------------------------------");
        System.out.println("response : " + response);
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("requestTokenHeader : " + requestTokenHeader);
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            System.out.println("JwtRequestFilter / doFilterInternal 21");
            jwtToken = requestTokenHeader.substring(7);
            try {
                System.out.println("JwtRequestFilter / doFilterInternal 22");
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            } catch (UnsupportedJwtException e) {
                System.out.println("JWT Token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("JWT Token has expired");
            } catch (SignatureException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        System.out.println("JwtRequestFilter / doFilterInternal 31 ");
        System.out.println("username : " + username);
        // Once we get the token validate it.
        System.out.println("SecurityContextHolder.getContext() : " + SecurityContextHolder.getContext());
        System.out.println("SecurityContextHolder.getContext().getAuthentication() : " + SecurityContextHolder.getContext().getAuthentication());
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("JwtRequestFilter / doFilterInternal 32 dans if");
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);
            System.out.println("userDetails : " + userDetails);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                System.out.println("jwtTokenUtil.validateToken(jwtToken, userDetails)");
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
        System.out.println("JwtRequestFilter / doFilterInternal fin");
        chain.doFilter(request, response);


    }

}
