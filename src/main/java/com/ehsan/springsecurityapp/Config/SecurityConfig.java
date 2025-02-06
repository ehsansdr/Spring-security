package com.ehsan.springsecurityapp.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable()); // disable csrf

        http.authorizeHttpRequests(request ->
                request.anyRequest().authenticated());

        // http.formLogin(Customizer.withDefaults());

        // with out this you can see the page in chrome but even if you
        // send request with postman you will get 200 but the login page not
        // the real page
        http.httpBasic(Customizer.withDefaults());


        // with this and http.formLogin(Customizer.withDefaults()); with each other
        // bt only with this you will not do login again
        // you will get the login page on and on in chrome
        // but you will get not login page on and on in postman

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build(); // This essentially creates the filter chain with the default security settings.
    }



}
