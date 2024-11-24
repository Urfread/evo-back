package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.User;
import com.urfread.breaknews.core.common.enums.ResultCodeEnum;
import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.vo.UserVO;
import com.urfread.breaknews.core.repository.UserRepository;
import com.urfread.breaknews.core.security.JwtUtils;

import com.urfread.breaknews.core.security.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 用户注册
     */
    public ResultData<UserVO> register(UserVO userVO) {
        // 检查用户名是否已存在
        Optional<User> existingUser = userRepository.findByUsername(userVO.getUsername());
        if (existingUser.isPresent()) {
            return ResultData.failure(ResultCodeEnum.VALIDATION_FAILED, "用户名已存在");
        }

        // 保存用户到数据库
        User user = User.builder()
                .username(userVO.getUsername())
                .password(PasswordUtil.hashPassword(userVO.getPassword())) // 使用 SHA-256 加密密码
                .deleted(false)
                .build();

        userRepository.save(user);

        // 返回成功的响应，不返回密码
        userVO.setPassword(null);
        userVO.setRePassword(null);
        return ResultData.success(userVO);
    }

    /**
     * 用户登录
     */
    public ResultData<String> login(UserVO userVO) {
        Optional<User> userOptional = userRepository.findByUsername(userVO.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 检查密码
            if (user.getPassword().equals(PasswordUtil.hashPassword(userVO.getPassword()))) {
                // 生成JWT Token
                String token = JwtUtils.generateToken(user.getUid().toString());
                return ResultData.success(token);
            }else{
                return ResultData.failure(ResultCodeEnum.VALIDATION_FAILED, "密码错误");
            }
        }else{
            return ResultData.failure(ResultCodeEnum.VALIDATION_FAILED, "用户不存在");
        }
    }
}
