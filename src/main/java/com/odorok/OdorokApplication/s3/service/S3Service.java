package com.odorok.OdorokApplication.s3.service;

import com.odorok.OdorokApplication.s3.exception.FileUploadException;
import com.odorok.OdorokApplication.s3.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.region}")
    private String region;

    public String upload(String domain, String userId, MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() +"_"+ file.getOriginalFilename();
            String key = S3Util.generateKey(domain, userId, fileName);
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            return S3Util.generateUrl(bucket,region,key);

        } catch (IOException e) {
            throw new FileUploadException("파일 업로드 실패", e);
        }
    }

    public void delete(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
    }
}