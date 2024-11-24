package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.enums.ResultCodeEnum;
import com.urfread.breaknews.core.common.model.ResultData; // 导入 ResultData 类用于标准化 API 响应
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    // 指定文件上传目录
    private final String uploadDir = "C:\\AMyGitHubRepository\\js-learning\\breakNews\\src\\main\\resources\\uploads";
    private final String imageUrlPrefix = "http://localhost:8080/image/view/";

    /**
     * 文件上传接口
     *
     * @param imageFile 上传的图片文件
     * @return 上传结果，成功则返回文件名，失败返回错误信息
     */
    @PostMapping("/upload")
    public ResultData<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            // 如果文件为空，返回上传失败信息
            return ResultData.failure(ResultCodeEnum.UNKNOWN, "请上传文件");
        }

        try {
            // 确保上传目录存在，如果不存在则创建目录
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // 创建目录
            }

            // 获取文件名并生成带有唯一标识符的文件名
            String fileName = imageFile.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID() + "_" + fileName;
            File destinationFile = new File(uploadDir, uniqueFileName);

            // 打印文件名，用于调试
            System.out.println("文件名: " + fileName);

            // 保存文件到指定目录
            imageFile.transferTo(destinationFile);

            // 返回成功结果和文件名
            return ResultData.success("文件上传成功: " + uniqueFileName);
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常堆栈信息以便调试
            // 文件上传失败时返回错误信息
            return ResultData.failure(ResultCodeEnum.UNKNOWN, "文件上传失败");
        }
    }

    /**
     * 获取已上传的文件列表
     *
     * @return 返回包含文件 URL 的列表
     */
    @GetMapping("/list")
    public ResultData<List<String>> listUploadedImages() {
        // 创建上传目录的 File 对象
        File uploadDirFile = new File(uploadDir);
        File[] files = uploadDirFile.listFiles(); // 获取目录下的文件列表

        List<String> fileUrls = new ArrayList<>();
        if (files != null) {
            // 遍历文件，将每个文件的 URL 添加到列表中
            for (File file : files) {
                String fileUrl = imageUrlPrefix + file.getName(); // 构建文件的访问 URL
                fileUrls.add(fileUrl);
            }
        }

        // 返回文件 URL 列表
        return ResultData.success(fileUrls);
    }

    /**
     * 查看指定文件
     *
     * @param fileName 文件名
     * @return 返回文件资源或错误状态
     */
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
        try {
            // 根据文件名查找文件
            File imageFile = new File(uploadDir, fileName);
            if (!imageFile.exists()) {
                // 如果文件不存在，返回 404 状态
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            // 将文件加载为资源
            Resource fileResource = new UrlResource(imageFile.toURI());
            // 返回文件资源，设置为内联显示，并带有文件名
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(fileResource);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // URL 解析异常时返回服务器内部错误状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//    /**
//     * 回显封面
//     */
//    @GetMapping("/avatar/{fileName}")
//    public ResponseEntity<Resource> viewAvatar(@PathVariable String fileName) {
//        try {
//            // 根据文件名查找文件
//            File imageFile = new File(uploadDir+"/avatars", fileName);
//            if (!imageFile.exists()) {
//                // 如果文件不存在，返回 404 状态
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(null);
//            }
//
//            // 将文件加载为资源
//            Resource fileResource = new UrlResource(imageFile.toURI());
//            // 返回文件资源，设置为内联显示，并带有文件名
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
//                    .body(fileResource);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            // URL 解析异常时返回服务器内部错误状态
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
