package com.jwtexample.JwtToken.Repository;

import com.jwtexample.JwtToken.Entitty.Forgotpassword;
import com.jwtexample.JwtToken.Entitty.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ForgotpasswordRepository extends MongoRepository<Forgotpassword,String> {
    public Optional<Forgotpassword> findByEmail(String email);
}
