package net.ent.etrs.gestionstagiaire.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@CommonsLog(topic = "SOUT")
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;

    public JwtTokenUtil() {
        //TODO sout
        log.trace("JwtTokenUtil constructor");
    }

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException, IllegalArgumentException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException, IllegalArgumentException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(getTokenWithOutBearer(token)).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        log.trace("JwtTokenUtil / doGenerateToken");
        log.trace("secret : " + secret);
        log.trace("subject : " + subject);
        return "Bearer " + Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        if (token == null || userDetails == null) {
            return false;
        }
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private String getTokenWithOutBearer(String token) {
        if (token.startsWith("Bearer ")) {
            return token.split("Bearer ")[1];
        }
        return token;

    }
}
