package com.rtfs.BookShelf.service;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtService {
	static final long EXPIRATIONTIME = 86400000;
	static final String PREFIX = "Bearer";

	@Value("${jwt.secret}")
    private String secret;
	
	private Key getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

	public String getToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(getKey())
            .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder()
                .setSigningKey(getKey()).build()
                .parseClaimsJws(token.replace(PREFIX, "").trim())
                .getBody().getSubject();
            if (user != null) return user;
        }
        return null;
    }
}
