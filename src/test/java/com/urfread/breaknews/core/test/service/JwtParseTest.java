package com.urfread.breaknews.core.test.service;

import com.urfread.breaknews.core.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtParseTest {

    @Test
    public void testParseJwt() {
        // 这是您的 JWT token
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJqd3RJZCI6ImFhYmNkNTZiLWNjZDUtNDU4Yy05ZmM1LWZlYzdiNGNkMzg3NiIsInN1YiI6IjYiLCJpYXQiOjE3MjkyMjU5MzcsImV4cCI6MTcyOTgzMDczN30.-3GKrKgfx-hKVX9Aaz9sL4lEs1U0QD1G_CXij4LYQBbco9N_2Uc0HFpgHZ0CPCmbKjKW568I6ToH8FrI1OVNRA";

        // 尝试解析 JWT
        try {
            // 提取用户ID (uid)
            String uid = JwtUtils.getUidFromToken(token);
            System.out.println("User ID (uid): " + uid);

            // 获取过期时间
            Date expirationDate = JwtUtils.getExpirationDateFromToken(token);
            System.out.println("Expiration Date: " + expirationDate);

            // 检查 token 是否有效
            boolean isTokenValid = JwtUtils.validateToken(token, uid);
            System.out.println("Is Token Valid: " + isTokenValid);

        } catch (Exception e) {
            System.out.println("An error occurred while parsing the JWT token: " + e.getMessage());
        }
    }
}
