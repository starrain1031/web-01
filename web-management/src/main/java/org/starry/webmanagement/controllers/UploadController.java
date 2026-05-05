package org.starry.webmanagement.controllers;


import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.utils.TencentCOSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
public class UploadController {

    @Autowired
    private TencentCOSOperator cosOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("upload: {}", file);
        if (!file.isEmpty()) {
            // 上传文件
            String url = cosOperator.putLocalFile(file.getBytes(), file.getOriginalFilename());
            return Result.success(url);
        }
        return Result.error("Failed to upload");
    }

}
