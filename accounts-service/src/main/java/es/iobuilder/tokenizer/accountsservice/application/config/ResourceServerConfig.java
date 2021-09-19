package es.iobuilder.tokenizer.accountsservice.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Resource Server Security COnfiguration
 */
@EnableWebSecurity
public class ResourceServerConfig {

    /**
     *  configure all the Security paths and configuration
     * @param http http security
     * @return Security Filter chain
     * @throws Exception Throws Exception if there is any error.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/accounts/**")
                .authorizeRequests()
                .mvcMatchers("/accounts/**")
                .access("hasAuthority('SCOPE_user.scope')")
                .and()
                .mvcMatcher("/transfer/**")
                .authorizeRequests()
                .mvcMatchers("/transfer/**")
                .access("hasAuthority('SCOPE_user.scope')")
                .and()
                .mvcMatcher("/deposit/**")
                .authorizeRequests()
                .mvcMatchers("/deposit/**")
                .access("hasAuthority('SCOPE_user.scope')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
