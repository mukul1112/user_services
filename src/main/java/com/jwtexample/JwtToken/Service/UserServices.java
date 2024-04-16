package com.jwtexample.JwtToken.Service;

import com.jwtexample.JwtToken.Entitty.User;
import com.jwtexample.JwtToken.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    private UserRepository userReposetories;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    public User saveUser(User user) {
        String RandomUserid= UUID.randomUUID().toString();
        user.setId(RandomUserid);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userReposetories.save(user);
    }
    public List<User> getAllUser() {
        return userReposetories.findAll();
    }

   public boolean founduserByEmail(String email)
   {
       boolean ans=userReposetories.findByEmail(email).isPresent();
       return ans;
   }


}
