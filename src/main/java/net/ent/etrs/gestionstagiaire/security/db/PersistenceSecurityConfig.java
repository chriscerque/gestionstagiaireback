package net.ent.etrs.gestionstagiaire.security.db;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"net.ent.etrs.gestionstagiaire.model.repo"})
@EntityScan(basePackages = {"net.ent.etrs.gestionstagiaire.model"})
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@CommonsLog(topic = "SOUT")
public class PersistenceSecurityConfig {
    @Bean
    AuditorAware<String> auditorProvider() {
        log.trace(">>>>>>>>PersistenceConfig/auditorProvider");
        return new AuditorAwareImpl();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

}
