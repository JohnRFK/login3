package com.john.login3.login3.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final int EXP_ONE_DAY = 1000 * 60 * 24;
    private final String SECRET_KEY = "808e02d95ff938fccc4729d94231f3ba31d12f4436fb41152e";

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    public String getToken(Map<String, Object> extraClaims, UserDetails user) {
        // TODO Auto-generated method stub
        return Jwts.builder()
            .claims(extraClaims)
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXP_ONE_DAY))
            .signWith(getKey())
            .compact();
    }

    private SecretKey getKey() {
        
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.SECRET_KEY));
    }

    public String getUsernameFromToken(String token) {
        // TODO Auto-generated method stub
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // TODO Auto-generated method stub
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        
        return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
