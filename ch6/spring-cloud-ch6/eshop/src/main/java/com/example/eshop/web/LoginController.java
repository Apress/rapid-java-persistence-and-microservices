package com.example.eshop.web;

import com.example.eshop.dto.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    String DEFAULT_EMAIL = "raj@example.com";
    String DEFAULT_PASSWORD = "password";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest) throws ServletException {

        var jwtToken = "";

        val email = loginRequest.getEmail();
        val password = loginRequest.getPassword();

        val bCryptPasswordEncoder = new BCryptPasswordEncoder();
        val encodedPassword = bCryptPasswordEncoder.encode(password);

        if(!(DEFAULT_PASSWORD.equals(encodedPassword))
                || (DEFAULT_EMAIL.equals(email)))   {
            // throw error
        }

        jwtToken = Jwts.builder().setSubject(email)
                    .claim("roles", "admin")
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .setIssuedAt(new Date())
                    .compact();

        return jwtToken;
    }

}
