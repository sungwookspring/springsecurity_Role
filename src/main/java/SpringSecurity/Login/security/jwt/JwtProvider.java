package SpringSecurity.Login.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtProvider {
    private SecretKey key = Keys.hmacShaKeyFor("randomStringrandomStringPdsdfsdfsdfsdfsdfsdfsdfsdfdsfsdfsdfsdfsdfsdfsdflease".getBytes());
    private int jwtExpirationMs = 1;

    public String generateJwtToken(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parserClaim(String token){
        return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
    }
}
