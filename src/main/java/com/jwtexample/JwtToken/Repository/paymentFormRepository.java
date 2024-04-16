package com.jwtexample.JwtToken.Repository;

import com.jwtexample.JwtToken.Entitty.paymentForm;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface paymentFormRepository extends MongoRepository<paymentForm,Integer> {
}
