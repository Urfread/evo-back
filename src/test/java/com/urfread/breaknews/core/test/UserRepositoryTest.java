package com.urfread.breaknews.core.test;

import com.urfread.breaknews.core.common.entity.User;

import com.urfread.breaknews.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertAndDeleteUser() {
        // 插入测试
        User user = User.builder()
                .username("testuser")
                .password("password123")
                .deleted(false)
                .build();
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getUid());

        // 删除测试
        userRepository.deleteById(savedUser.getUid().toString());
        assertFalse(userRepository.findById(savedUser.getUid().toString()).isPresent());
    }
}
