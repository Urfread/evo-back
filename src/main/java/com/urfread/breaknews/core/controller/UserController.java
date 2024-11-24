package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.vo.UserVO;
import com.urfread.breaknews.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 注册请求
    @PostMapping("/register")
    public ResultData<UserVO> register(@RequestBody UserVO userVO) {
        return userService.register(userVO);
    }

    // 登录请求
    @PostMapping("/login")
    public ResultData<String> login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }
}
