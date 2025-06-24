package com.odorok.OdorokApplication.s3.service;

import com.odorok.OdorokApplication.s3.dto.S3UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {
    public S3UploadResult upload(String domain, String userId, MultipartFile file);

    List<String> uploadMany(String domain, String userId, List<MultipartFile> fileList);

    public void delete(String key);
}
