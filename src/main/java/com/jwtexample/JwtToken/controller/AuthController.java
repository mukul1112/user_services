package com.jwtexample.JwtToken.controller;

import com.jwtexample.JwtToken.Entitty.Forgotpassword;
import com.jwtexample.JwtToken.Entitty.JwtRequest;
import com.jwtexample.JwtToken.Entitty.JwtResponse;
import com.jwtexample.JwtToken.Entitty.User;
import com.jwtexample.JwtToken.Repository.ForgotpasswordRepository;
import com.jwtexample.JwtToken.Repository.UserRepository;
import com.jwtexample.JwtToken.Security.JwtHelper;
import com.jwtexample.JwtToken.Service.EmailService;
import com.jwtexample.JwtToken.dto.NewPassword;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ForgotpasswordRepository forgotpasswordRepository;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/get-otp")
    public ResponseEntity<String> sendotp(@RequestBody String email)
    {
        System.out.println("i am in email sending service"+email);

        String serverOtp=emailService.sendOtp(email);
        System.out.println(serverOtp );
        System.out.println( email.replace("\"", ""));
        Forgotpassword user=new Forgotpassword();
       String newemail=email.replace("\"", "");
        System.out.println("new email is---   ;-  "+newemail);
        user.setEmail(newemail);
        user.setOtp(serverOtp);
        forgotpasswordRepository.save(user);


        return  ResponseEntity.ok(email);
    }
    @CrossOrigin
    @PostMapping("/verify-otp")
    public ResponseEntity<String> Verifyourotp(@RequestBody NewPassword cr)
    {
        System.out.println("i am in verify otp contoller sending service");
        Forgotpassword user=forgotpasswordRepository.findById(cr.getOtp()).orElse(null);
        System.out.println("the email  is :- "+user.getEmail());
        System.out.println("the otp  is :- "+user.getOtp());
        if(user!=null)
        {
            String useremail= user.getEmail();
            System.out.println("the email is"+useremail);
            User user1=userRepository.findByEmail(useremail).orElseThrow(()-> new RuntimeException("cant fetch user with this email"));
            user1.setPassword(passwordEncoder.encode(cr.getNewpassword()));
            userRepository.save(user1);
            forgotpasswordRepository.deleteById(cr.getOtp());

            return ResponseEntity.ok("OTP verification successful");
        }

        else {
                return ResponseEntity.badRequest().body("Incorrect OTP. Please check and try again.");
            }

        }

        @PostMapping("/change-password")
        public ResponseEntity<String> changepassword(@RequestBody String newpassword,HttpSession session){

            String useremail= (String) session.getAttribute("email");
            System.out.println("the email is"+useremail);
            User user=userRepository.findByEmail(useremail).orElseThrow(()-> new RuntimeException("cant fetch user with this email"));
            user.setPassword(passwordEncoder.encode(newpassword));
            userRepository.save(user);

             return ResponseEntity.ok("password change succesful");
        }









    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
