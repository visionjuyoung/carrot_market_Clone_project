package com.hmsh.carrotmarket.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;

@Slf4j
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

    public String getSubject(String token) {
        String subject = "";
        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
            DefaultClaims defaultClaims = (DefaultClaims) defaultJws.getBody();
            subject = defaultClaims.getSubject();
            log.info("defaultJws = {}", defaultJws);
            log.info("defaultClaims = {}", defaultClaims);
        } catch (Exception e) {
            log.error("유효하지 않은 토큰");
        }

        return subject;
    }

}
