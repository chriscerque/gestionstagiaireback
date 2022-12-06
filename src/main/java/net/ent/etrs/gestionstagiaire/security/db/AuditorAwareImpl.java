package net.ent.etrs.gestionstagiaire.security.db;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.MyUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@CommonsLog(topic = "SOUT")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {


        log.trace(">>>>>>>>>>>AuditorAwareImpl/getCurrentAuditor");
//        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.trace("AuditorAwareImpl/getCurrentAuditor");
        log.trace("authentication : " + authentication);
        if (authentication != null) {
            log.trace("authentication 11");
        }

        if (authentication == null || !authentication.isAuthenticated()) {
            log.trace("authentication 22");
            return Optional.of("ANONYMOUS");
//            return Optional.of(((MyUser) new AnonymousAuthenticationFilter("Anonymous").getPrincipal()).getUsername());
        }
        if (authentication.getPrincipal() instanceof MyUserDetails) {
            log.trace("((User) authentication.getPrincipal()).getUsername() : " + ((MyUserDetails) authentication.getPrincipal()).getUsername());
            return Optional.of(((MyUserDetails) authentication.getPrincipal()).getUsername());
        } else {
            log.trace("authentication.getClass() : " + authentication.getClass());
            return Optional.of(((AnonymousAuthenticationToken) authentication.getPrincipal()).getName());
        }
//        log.trace("authentication 33");
//        return Optional.of("ANONYMOUS");
//        return Optional.of(((MyUser) new AnonymousAuthenticationFilter("Anonymous").getPrincipal()).getUsername());
    }
}
