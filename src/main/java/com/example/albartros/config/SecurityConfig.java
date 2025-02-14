package com.example.albartros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authorized -> authorized
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers(
                        HttpMethod.POST, "news",
                        "destination", "abbreviation-category", "abbreviation", "country", "agency", "discount", "events", "fact", "food", "hotel", "insurance-company", "insurance-plan", "memo", "tour", "booking")
                .hasAnyRole("ADMIN", "STAFF")
                .requestMatchers(HttpMethod.DELETE, "destination", "abbreviation-category", "abbreviation", "country", "agency", "discount", "events", "fact", "food", "hotel", "insurance-company", "insurance-plan", "memo", "tour", "booking", "news").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers(HttpMethod.PUT, "booking/", "news", "destination", "abbreviation-category", "abbreviation", "country", "agency", "discount", "events", "fact", "food", "hotel", "insurance-company", "insurance-plan", "memo", "tour").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("booking","booking/", "insurance-purchase/").hasAnyRole("AGENT", "ADMIN", "STAFF")
        );
        http.authorizeHttpRequests(authorized -> authorized.requestMatchers("/auth/register",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-ui.html").permitAll());

        http.authorizeHttpRequests(authorized -> authorized.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }
}
