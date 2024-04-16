package com.jwtexample.JwtToken.Repository;

import com.jwtexample.JwtToken.Entitty.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

     public Optional<User> findByEmail(String email);


}
