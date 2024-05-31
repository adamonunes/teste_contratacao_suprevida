package com.suprevida.testecontratacao.security.authentication;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.suprevida.testecontratacao.security.UserDetailsImpl;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "Y29udHJhdGFjYW9TdXByZXZpZGE="; 

    private static final String ISSUER = "Suprevida"; 
    
    private static final Long EXPERITION_TOKEN = 12L;

    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER) 
                    .withIssuedAt(creationDate()) 
                    .withExpiresAt(expirationDate()) 
                    .withSubject(user.getUsername()) 
                    .sign(algorithm); 
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token) 
                    .getSubject(); 
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(EXPERITION_TOKEN).toInstant();
    }

}
