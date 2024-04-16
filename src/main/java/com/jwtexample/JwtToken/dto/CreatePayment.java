package com.jwtexample.JwtToken.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePayment {
    private int id;
    private  int amount;
    private String name;
    private String email;
    private String addres;

}
