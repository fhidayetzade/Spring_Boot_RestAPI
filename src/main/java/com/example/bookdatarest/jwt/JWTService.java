package com.example.bookdatarest.jwt;

import com.example.bookdatarest.model.Book;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    String SECRET_KEY = "FUoWoU2bC4A15g3XyuUJTGwJ8jmaYbIIiV0xhahF2OzaXzHfYqLphnfeK1ZdtDCX";
    public String generateToke(String username) {
        Map<String, Object> claims = new HashMap<>();
        return generateTokenForUser(claims,username);
    }

    private String generateTokenForUser(Map<String, Object> claims, String username) {
        return
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(username)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                        .compact();
    }



    public String extractUsernameForToken(String token){
        return extractClaim(token, Claims::getSubject);
    }


    private Claims extractAllClaims(String token) {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }
    public Date extractExpirationTimeForToken (String token){
        return extractClaim(token, Claims:: getExpiration);
    }
    public Boolean isTokenValid (String token, UserDetails userDetails) {
        final String username = extractUsernameForToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private <T>  T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired (String token){
        return extractExpirationTimeForToken(token).before(new Date());
    }
    private Key getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
