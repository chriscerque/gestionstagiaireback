package net.ent.etrs.gestionstagiaire.security.jwt;


import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
@CommonsLog(topic = "SOUT")
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    public JwtAuthenticationEntryPoint() {
        log.trace("JwtAuthenticationEntryPoint / constructor()");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.trace("JwtAuthenticationEntryPoint / commence()");
        log.debug("request : " + request);
        log.debug("response : " + response);
        log.debug("authException : " + authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized!!!!");
    }
}
