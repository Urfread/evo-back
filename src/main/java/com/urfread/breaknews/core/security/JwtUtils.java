package com.urfread.breaknews.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {

    private static final String SECRET = "yourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKey";  // 这里是你的密钥
    private static final int JWT_EXPIRATION_IN_MS = 7 * 24 * 60 * 60 * 1000;  // 7天

    // 生成JWT Token
    public static String generateToken(String uid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("jwtId", UUID.randomUUID().toString());  // 添加会话ID以确保JWT唯一
        return doGenerateToken(claims, uid);
    }

    // 根据uid生成token
    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // 验证token是否有效
    public static boolean validateToken(String token, String uid) {
        final String uidFromToken = getUidFromToken(token);
        return (uidFromToken.equals(uid) && !isTokenExpired(token));
    }

    // 从token中获取用户email
    public static String getUidFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 检查token是否过期
    private static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 从token中获取过期时间
    public static Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
    public static String getKey() {
        return SECRET;
    }
}
