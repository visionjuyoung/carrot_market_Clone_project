package CarrotJwt.JwtProvider;

import CarrotJwt.JwtDao.AuthDTO;
import JwtService.CustomUserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final String secretKey = "c88d74ba-1554-48a4-b549-b926f5d77c9e";
    private final long accessExpireTime = 1 * 60 * 1000L;
    private final long refreshExpireTime = 1 * 60 * 2000L;
    private final CustomUserDetailService customUserDetailService;

    public String createAccessToken(AuthDTO.LoginDTO loginDTO){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", loginDTO.getEmail());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpireTime);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return jwt;
    }

    public Map<String, String> createRefreshToken(AuthDTO.LoginDTO loginDTO){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", loginDTO.getEmail());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + refreshExpireTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String refreshTokenExpirationAt = simpleDateFormat.format(expiration);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("refreshToken", jwt);
        result.put("refreshTokenExpirationAt", refreshTokenExpirationAt);
        return result;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserInfo(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserInfo(String token){
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email");
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("token");
    }

    public boolean validateJwtToken(ServletRequest request, String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e){
            request.setAttribute("exception", "MalformedJwtException");
        } catch (ExpiredJwtException e){
            request.setAttribute("exception", "ExpiredJwtException");
        } catch (UnsupportedJwtException e){
            request.setAttribute("exception", "UnsupportedJwtException");
        } catch (IllegalArgumentException e){
            request.setAttribute("exception", "IllegalArgumentException");
        }
        return false;
    }
}
