package com.jwtexample.JwtToken.controller;

import com.jwtexample.JwtToken.Entitty.User;
import com.jwtexample.JwtToken.Service.CustomUserDetailServices;
import com.jwtexample.JwtToken.Service.EmailService;
import com.jwtexample.JwtToken.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class HomeContoller {

    @Autowired
    private UserServices userServices;

    @Autowired
     private CustomUserDetailServices userDetailServices;


    @CrossOrigin
    @PostMapping("/user-registration")
    public ResponseEntity<User> CreateUSer(@RequestBody User user){
        User user1=userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @CrossOrigin
    @GetMapping("/user/user-detail")
    public  ResponseEntity<List<User>>  getalluser(){
        List<User> user=userServices.getAllUser();

        return ResponseEntity.ok(user);
    }
    @CrossOrigin
    @GetMapping("/user/user-logedin")
    public ResponseEntity<User> getuser(Principal principal){
        String username=principal.getName();
        User user= (User) userDetailServices.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }



}
