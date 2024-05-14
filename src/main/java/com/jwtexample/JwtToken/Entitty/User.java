package com.jwtexample.JwtToken.Entitty;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//"id": "de95",
//        "firstname": "karan",
//        "lastname": "kahna",
//        "email": "kahan@gamil.com",
//        "dob": "2024-04-11T18:30:00.000Z",
//        "gender": "Male",
//        "education": "PostGraduate",
//        "company": "kahjl",
//        "experience": 7,
//        "package": 5

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private String name;
    private String lastname;
    private String dob;
    private String gender;
    private String education;
    private String company;
    private String experience;
    private String ctc;

    @Id
    private String id;
    private String email;
    private String about;
    private String password;
    private String clientid;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
