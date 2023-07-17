package com.randradee.studies.security.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.randradee.studies.security.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("security-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String validateToken(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("security-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("");
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
