package com.jwtexample.JwtToken.Config;

import com.jwtexample.JwtToken.Entitty.User;
import com.jwtexample.JwtToken.Repository.UserRepository;
import com.jwtexample.JwtToken.Service.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private boolean isOAuth2Authentication = false;

    private String useremail;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        isOAuth2Authentication=true;
            OAuth2AuthenticationToken   oAuth2AuthenticationToken= (OAuth2AuthenticationToken) authentication;
            DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
            System.out.println(principal.toString());
            Map<String,Object> principalAttributes =principal.getAttributes();
            String email =principalAttributes.getOrDefault("email", "").toString();
            String username1=principalAttributes.getOrDefault("name", "").toString();
            String password=principalAttributes.getOrDefault("password", "").toString();

          userRepository.findByEmail(email)
                  .ifPresentOrElse(user -> {
                      System.out.println("---------------userexist=============");
                     useremail=email;

                  },()->{
                        System.out.println("=====not registred user");
                        User userc=new User();
                        userc.setName(username1);
                        userc.setEmail(email);
                        userc.setPassword("1236594365468749876451897/");
                        userc.setClientid(username1+"d4dgrp10uiz");
                        userServices.saveUser(userc);
                        useremail=email;


                  });


           this.setAlwaysUseDefaultTargetUrl(true);
          this.setDefaultTargetUrl("http://127.0.0.1:5500/clockchain.html");
          super.onAuthenticationSuccess(request, response, authentication);

    }
    public boolean isOAuth2Authentication() {
        return isOAuth2Authentication;
    }
    public String getOauthUseremail(){
        return useremail;
    }




}
