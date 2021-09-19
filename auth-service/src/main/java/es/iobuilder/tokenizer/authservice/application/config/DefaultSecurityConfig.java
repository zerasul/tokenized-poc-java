package es.iobuilder.tokenizer.authservice.application.config;

import es.iobuilder.tokenizer.authservice.application.security.service.AuthUserDetailService;
import es.iobuilder.tokenizer.authservice.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Default Security Configuration. This class contains all the configuration needed for the security contraints.
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

    /**
     * Application COntext
     */
    @Autowired
    ApplicationContext applicationContext;

    /**
     * Default Security Filter Chain. Defines the default Security Contraints. Allows Authorized Requests, login form and /user Api.
     * @param http Http Security Contraints
     * @return Security Filter Chaing
     * @throws Exception throws Exception if there is any errors.
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
        )
                .formLogin(withDefaults()).antMatcher("/user");
        return http.build();
    }

    /**
     * User details definition. Check if the user is allowed to login
     * @return UserDetails Service
     */
    @Bean
    UserDetailsService users() {
        UserService userService= applicationContext.getBean(UserService.class);
        return new AuthUserDetailService(userService);
    }

}
