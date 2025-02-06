package com.ehsan.springsecurityapp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//This annotation is used to enable Spring Security’s web security features.
// It triggers the configuration of Spring Security
// by creating a SecurityConfig class where you define your custom security settings.
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

/*
        // CSRF (Cross-Site Request Forgery) protection is enabled by default in Spring Security.
        // This line disables CSRF protection
        // In stateless applications (like APIs), CSRF protection is often not needed
        // because requests don't carry a session state,
        // and tokens aren't required.
        http.csrf(customizer -> customizer.disable()); // disable csrf
        // you can do that in this way :
//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf =
//                new Customizer<CsrfConfigurer<HttpSecurity>>() {
//                    @Override
//                    public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//                        customizer.disable();
//                    }
//                };
//        http.csrf(custCsrf);
        // the upper line is equal to http.csrf(customizer -> customizer.disable());


        // This defines authorization rules for incoming HTTP requests.
        // It says that all requests must be authenticated.
        http.authorizeHttpRequests(request ->
                request.anyRequest().authenticated());
        // http.formLogin(Customizer.withDefaults());

        // with out this you can see the page in chrome but even if you
        // send request with postman you will get 200 but the login page not
        // the real page
        http.httpBasic(Customizer.withDefaults());
        // This means that the server will expect the client to provide credentials
        // (username and password) in the Authorization header of the HTTP request.


        // with this and http.formLogin(Customizer.withDefaults()); with each other
        // bt only with this you will not do login again
        // you will get the login page on and on in chrome
        // but you will get not login page on and on in postman

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // In a stateless application (like an API),
        // you don’t want Spring Security to create or store a session on the server for each user request. Instead,
        // authentication is typically handled using tokens (like JWTs),
        // and each request must include the necessary credentials (like a token) for authentication.
        //If you don't use STATELESS: Spring Security would create a session for each request,
        // which is typically not desired for stateless APIs but might be useful in stateful web applications
        // (like those using cookies for session management).

        return http.build();
        */

        // OR IN THE BUILDER PATTERN
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build(); // This essentially creates the filter chain with the default security settings.
    }


    // @Bean
    public UserDetailsService userDetailsService(){
        // we can use this because this class implement the UserDetailsService
        // you may customize this

        // if you use this user pass you can use the given user pass word in the
        // application.properties
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("novin")
                .password("n@123")
                .roles("USER")
                .build();

        // you can create more user

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("harsh")
                .password("H@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        // this one need to get to the data base
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // the default one the plain text
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

}
