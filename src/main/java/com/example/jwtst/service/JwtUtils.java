package com.example.jwtst.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final String SECRET_KEY = "quang-depttria-vli-ay-tuiasdzxjckjsdlhashd-123123"; // Thay thế bằng secret key thật
    private static final long EXPIRATION_TIME = 86400000; // 1 ngày
    String encodedSecretKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    // Tạo JWT từ username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, encodedSecretKey)
                .compact();
    }

    // Trích xuất username từ JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Kiểm tra tính hợp lệ của JWT
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Kiểm tra xem token đã hết hạn chưa
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Lấy ngày hết hạn từ token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Trích xuất một claim (yêu cầu) cụ thể từ JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Trích xuất tất cả claims từ token
    private Claims extractAllClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

