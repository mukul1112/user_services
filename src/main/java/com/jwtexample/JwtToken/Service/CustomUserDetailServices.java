package com.jwtexample.JwtToken.Service;

import com.jwtexample.JwtToken.Entitty.User;
import com.jwtexample.JwtToken.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Load user From database
       User user= userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("user Not found||||||||"));

        return user;
    }
}
