package com.example.demo.jwt;

import com.example.demo.entities.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "SECRET_KEY";

    // יצירת הטוקן
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .claim("role","Role_"+ user.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // שעה תוקף
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // חילוץ המידע מהטוקן
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // אימות אם הטוקן פג תוקף
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // חילוץ שם המשתמש
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // אימות הטוקן
    public boolean validateToken(String token, User user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
