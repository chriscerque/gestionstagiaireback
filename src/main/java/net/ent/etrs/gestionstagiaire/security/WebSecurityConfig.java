package net.ent.etrs.gestionstagiaire.security;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.security.jwt.JwtAuthenticationEntryPoint;
import net.ent.etrs.gestionstagiaire.security.jwt.JwtRequestFilter;
import net.ent.etrs.gestionstagiaire.security.services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@CommonsLog(topic = "SOUT")
public class WebSecurityConfig {

    //    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    //    @Autowired
////	private UserDetailsService userDetailsService;
//    private MyUserDetailService userDetailsService;

    //    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;

        log.trace("this.jwtAuthenticationEntryPoint : " + this.jwtAuthenticationEntryPoint);
    }


    //    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password(bCryptPasswordEncoder.encode("userPass"))
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(bCryptPasswordEncoder.encode("adminPass"))
//                .roles("USER", "ADMIN")
//                .build());
//        return manager;
//    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder, MyUserDetailService userDetailService)
            throws Exception {
        //TODO SOUT
        log.trace("WebConfig / authManager");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, MyUserDetailService userDetailsService) throws Exception {
//        log.trace("WebConfig / configureGlobal");
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.trace("WebConfig / passwordEncoder");
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        log.trace("WebConfig / authenticationManagerBean");
////        return authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and().build();
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.trace("WebConfig / configure");
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .addFilterBefore(jwtRequestFilter, AnonymousAuthenticationFilter.class)
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/api/auth/authenticate", "/api/auth/register").permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
