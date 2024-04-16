package com.jwtexample.JwtToken.Entitty;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "payments")
public class paymentForm {
    @Id
    private int id;
    private  int amount;
    private String name;
    private String email;
    private String addres;

}
