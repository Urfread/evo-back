package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        // 获取请求源的 IP 地址
        String ipAddress = request.getRemoteAddr();
        // 使用日志输出 IP 地址
        logger.info("Received request from IP address: {}", ipAddress);
        // 返回 IP 地址和消息
        return ResultData.success("Hello, World! Your IP address is: " + ipAddress).toString();
    }

    // 1. Path Variable 请求示例
    @GetMapping("/path/{username}")
    public String welcomeWithPathVariable(@PathVariable String username) {
        return ResultData.success("Welcome, " + username).toString();
    }

    // 2. Query Parameter 请求示例
    @GetMapping("/query")
    public String welcomeWithQueryParameter(@RequestParam String username) {
        return ResultData.success("Welcome, " + username).toString();
    }

    // 3. Request Body 请求示例
    @PostMapping("/body")
    public String welcomeWithRequestBody(@RequestBody User user) {
        return ResultData.success("Welcome, " + user.getUsername()).toString();
    }

    // 4. Request Header 请求示例
    @GetMapping("/header")
    public String welcomeWithHeader(@RequestHeader("username") String username) {
        return ResultData.success("Welcome, " + username).toString();
    }

    // 测试模型类
    public static class User {
        private String username;

        // Getter and Setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
