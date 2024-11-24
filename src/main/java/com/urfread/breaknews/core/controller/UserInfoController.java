package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.enums.ResultCodeEnum;
import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.vo.UserInfoVO;
import com.urfread.breaknews.core.service.UserInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-18 08:09
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    private final String avatarUploadDir = "C:\\AMyGitHubRepository\\js-learning\\breakNews\\src\\main\\resources\\uploads"; // 指定上传目录
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/find")
    public ResultData<UserInfoVO> findUserInfo(HttpServletRequest req){
        String uid = (String) req.getAttribute("uid");
        return ResultData.success(userInfoService.findUserInfo(uid));
    }
    @PostMapping("/uploadAvatar")
    public ResultData<String> uploadAvatar(@RequestParam("avatar") MultipartFile avatarFile,HttpServletRequest req) {
        if (avatarFile.isEmpty()) {
            return ResultData.failure(ResultCodeEnum.UNKNOWN, "请上传头像文件");
        }

        try {
            // 确保上传目录存在（假设你已经有一个定义的头像上传目录 avatarUploadDir）
            File avatarUploadDirFile = new File(avatarUploadDir);
            if (!avatarUploadDirFile.exists()) {
                avatarUploadDirFile.mkdirs(); // 创建目录
            }

            // 获取文件名并构建目标文件
            String originalFileName = avatarFile.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID()+"_"+originalFileName ;
            File destinationFile = new File(avatarUploadDir, uniqueFileName);

            // 保存到数据库
            userInfoService.updateAvatar(uniqueFileName,(String) req.getAttribute("uid"));
            // 保存文件
            avatarFile.transferTo(destinationFile);

            // 返回头像 URL（根据具体情况，你可以返回相对路径或者完整的 URL）
            String avatarUrl = "/uploads/avatars/" + uniqueFileName; // 假设头像存储在这个目录
            return ResultData.success(avatarUrl);
        } catch (IOException e) {
            e.printStackTrace(); // 打印堆栈跟踪
            return ResultData.failure(ResultCodeEnum.UNKNOWN, "头像上传失败");
        }
    }
}
