package com.hmsh.carrotmarket.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;

public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;
    private final long EXPIRE_TIME = 60 * 24; // 1 day

    public String generateToken(String content) throws Exception {
        return Jwts.builder()
                .setIssuedAt(Date.from(ZonedDateTime.now().plusMinutes(EXPIRE_TIME).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

}
