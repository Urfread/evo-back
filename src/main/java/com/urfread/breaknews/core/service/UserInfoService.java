package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.User;
import com.urfread.breaknews.core.common.vo.UserInfoVO;
import com.urfread.breaknews.core.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-18 11:33
 */
@Service
public class UserInfoService {
    private final UserRepository userInfoRepository;
    public UserInfoService(UserRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    public int updateAvatar(String uniqueFileName, String uid) {
        return userInfoRepository.updateAvatar("http://localhost:8080/image/view/"+uniqueFileName, Integer.valueOf(uid));
    }

    public UserInfoVO findUserInfo(String uid) {
        User user=userInfoRepository.findByUid(Integer.valueOf(uid)).orElse(null);
        if (user==null)return null;
        return UserInfoVO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .build();
    }
}
