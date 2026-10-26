package com.example.springtutorial;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public static final String SECRET = "MySuperSecretKey123#456@789#(()JIJIOJ*U9908908055645456464!$";

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .addClaims(new HashMap<>())
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims verifySignatureAndExtractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public  String extractUsername(String jwtToken) {
        return verifySignatureAndExtractAllClaims(jwtToken).getSubject();
    }

    public Date getExpirationDate(String jwtToken) {
        return verifySignatureAndExtractAllClaims(jwtToken).getExpiration();
    }

    public boolean isTokenExpired(String jwtToken) {
        return getExpirationDate(jwtToken).before(new Date());
    }
}
