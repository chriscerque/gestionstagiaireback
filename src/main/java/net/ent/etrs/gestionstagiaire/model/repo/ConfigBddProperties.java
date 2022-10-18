package net.ent.etrs.gestionstagiaire.model.repo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class ConfigBddProperties {

//    private Environment environment;

    @Getter @Setter
    private String url;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String driver;
}
