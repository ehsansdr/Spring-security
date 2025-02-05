package com.ehsan.springsecurityapp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greet() {
        return "Hello Spring Security";
    }


    @GetMapping("/session-id")
    public String getSessionId(HttpServletRequest request) {
        return "welcome to Spring Security " + request.getSession().getId();

    }

    @GetMapping("/user-name")
    public String getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the User object (assuming you're using Spring Security's UserDetailsService)
            User principal = (User) authentication.getPrincipal();

            // If you store user ID as a custom attribute, you can retrieve it here.
            // This example assumes the user ID is part of the username or in a custom property.
            String userId = principal.getUsername();  // or principal.getId() if you have a custom User class
            return userId;
        }

        return null;
    }
}
