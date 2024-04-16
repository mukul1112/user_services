package com.jwtexample.JwtToken.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
@CrossOrigin
@RestController
public class Auth2Controller {



    @CrossOrigin
    @GetMapping("/oauth/login")
    public ResponseEntity<Void> redirectToOAuthProvider() {
        // Redirect to the OAuth provider's login page
        System.out.println("hello");
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/oauth2/authorization/google"))
                .build();
    }


}
