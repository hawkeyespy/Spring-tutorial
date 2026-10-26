package com.example.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class define all the Beans required for the spring security for basic auth.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    /**
     * Providing rules for the API to be authenticated.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain basicAuth(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/hello")
                                    .permitAll()                          //No authentication required
                                .requestMatchers("/user/**")
                                    .permitAll()                          //No authentication required
                                        .anyRequest().authenticated())    //Authenticate everything else
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * This is the bean where we are connecting our repository using service for fetching the user details from the DB.
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    /**
     * This bean is used by spring to provide "AuthenticationManager" and "AuthenticationProvider".
     * For Basic auth "DaoAuthenticationProvider" is used which uses "UserDetailsService" and "PasswordEncoder".
     * This is the bean where we are bridging the spring security to authentication provider and our service to connect using repository.
     *
     * @param userDetailsService Our custom UserService Bean
     * @param passwordEncoder
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
