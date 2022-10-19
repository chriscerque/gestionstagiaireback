package net.ent.etrs.gestionstagiaire.secu;

import net.ent.etrs.gestionstagiaire.model.entities.MyUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {


        System.out.println(">>>>>>>>>>>AuditorAwareImpl/getCurrentAuditor");
//        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("AuditorAwareImpl/getCurrentAuditor");
        System.out.println("authentication : " + authentication);
        if (authentication != null) {
            System.out.println("authentication 11");
        }

        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("authentication 22");
            return Optional.of("ANONYMOUS");
//            return Optional.of(((MyUser) new AnonymousAuthenticationFilter("Anonymous").getPrincipal()).getUsername());
        }
        if (authentication.getPrincipal() instanceof MyUser) {
            System.out.println("((User) authentication.getPrincipal()).getUsername() : " + ((MyUser) authentication.getPrincipal()).getUsername());
            return Optional.of(((MyUser) authentication.getPrincipal()).getUsername());
        } else {
            System.out.println("authentication.getClass() : " + authentication.getClass());
            return Optional.of(((AnonymousAuthenticationToken) authentication.getPrincipal()).getName());
        }
//        System.out.println("authentication 33");
//        return Optional.of("ANONYMOUS");
//        return Optional.of(((MyUser) new AnonymousAuthenticationFilter("Anonymous").getPrincipal()).getUsername());
    }
}
