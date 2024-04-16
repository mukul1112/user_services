package com.jwtexample.JwtToken.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPassword {
    private String newpassword;
    private String otp;
}
