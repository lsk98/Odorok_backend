package com.odorok.OdorokApplication.security.jwt;

import com.odorok.OdorokApplication.security.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtil {
    private final SecretKey key;

    public JWTUtil() {
        key = Jwts.SIG.HS256.key().build();
    }

    @Value("${jwt.access.expmin}")
    private long accessExpMin;
    @Value("${jwt.refresh.expmin}")
    private long refreshExpMin;

    public String createAccessToken(User user) {
        return create("accessToken", accessExpMin,
                Map.of( "id", user.getId(), "email", user.getEmail(), "nickname", user.getNickname(), "name", user.getName()));
    }

    public String createRefreshToken(User user) {
        return create("refreshToken", refreshExpMin,
                Map.of("id", user.getId(), "email", user.getEmail()));
    }

    public String create(String subject, long expireMin, Map<String, Object> claims) {
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * expireMin);
        String token = Jwts.builder().subject(subject).claims(claims).expiration(expireDate).signWith(key).compact();
        log.debug("token 생성: {}", token);
        return token;
    }

    public Claims getClaims(String jwt) {
        var parser = Jwts.parser().verifyWith(key).build();
        var jws = parser.parseSignedClaims(jwt);
        log.debug("claims: {}", jws.getPayload());
        return jws.getPayload();
    }
}
