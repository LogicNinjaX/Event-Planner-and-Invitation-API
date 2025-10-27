package com.app.parking.util;


import com.app.parking.config.JwtConfig;
import com.app.parking.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {

    private final JwtConfig jwtConfig;
    private String secret;
    public  Duration JWT_EXPIRATION;


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public JwtTokenUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    @PostConstruct
    public void init(){
        this.secret = jwtConfig.getSecret();
        this.JWT_EXPIRATION = Duration.ofMillis(jwtConfig.getExpiration());
        LOGGER.info("JWT secret and expiration loaded.");
    }



    private SecretKey getSecretKey(){
            return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(UserDetails userDetails, UUID userId, String role){
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(userId.toString())
                .claim("username", userDetails.getUsername())
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(JWT_EXPIRATION)))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }


    public Claims getClaims(String token){
        JwtParser parser = Jwts.parser().verifyWith(getSecretKey()).build();
        return parser.parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        Claims claims = getClaims(token);
        String username = claims.get("username", String.class);
        UUID userId = UUID.fromString(claims.getSubject()); // Subject (User Id)
        Date expiration = claims.getExpiration();

        boolean idsMatch = false;

        if (userDetails instanceof CustomUserDetails customUser){
            idsMatch = userId.equals(customUser.getUserId());
        }

        return username.equals(userDetails.getUsername())
                && idsMatch
                && !expiration.before(new Date())
                && userDetails.isAccountNonLocked()
                && userDetails.isEnabled();
    }

}
