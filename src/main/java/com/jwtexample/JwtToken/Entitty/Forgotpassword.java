package com.jwtexample.JwtToken.Entitty;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "forgetpassword")
public class Forgotpassword {

    private String email;

    @Id
    private String otp;

}
