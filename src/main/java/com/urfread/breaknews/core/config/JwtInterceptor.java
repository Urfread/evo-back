package com.urfread.breaknews.core.config;
import com.urfread.breaknews.core.security.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 JWT 令牌
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false; // 没有令牌，拒绝访问
        }
        token = token.replace("Bearer ", "");
        try {
            // 验证并解析 JWT
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtUtils.getKey()) // 假设 JwtUtils 有获取 key 的方法
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            // 获取用户uid
            String uid = claims.getSubject();
            // 将用户id存入请求属性，供控制器使用
            request.setAttribute("uid", uid);
            return true; // 继续处理请求
        } catch (ExpiredJwtException e) {
            // Token 过期
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"token expired\"}");
            response.getWriter().flush();
            return false;
        }catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false; // 令牌无效
        }
    }
}
