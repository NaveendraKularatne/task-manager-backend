package com.osos.taskmanager.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.osos.taskmanager.dto.UserInfoRequestDto;
import com.osos.taskmanager.dto.UserInfoResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;


@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserInfoRequestDto userInfoRequestDto) {
        Date date = new Date();
        Date validity = new Date(date.getTime() + 1000 * 60 * 60);

        return JWT.create()
                .withIssuer(userInfoRequestDto.getName())
                .withIssuedAt(date)
                .withExpiresAt(validity)
                .withClaim("name", userInfoRequestDto.getName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        userInfoResponseDto.setName(decodedJWT.getClaim("name").asString());

        return new UsernamePasswordAuthenticationToken(userInfoResponseDto, null, Collections.emptyList());
    }
}
