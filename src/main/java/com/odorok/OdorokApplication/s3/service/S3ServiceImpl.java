package com.odorok.OdorokApplication.s3.service;

import com.odorok.OdorokApplication.s3.dto.S3UploadResult;
import com.odorok.OdorokApplication.commons.exception.FileUploadException;
import com.odorok.OdorokApplication.s3.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.region}")
    private String region;

    private S3UploadResult upload(String domain, String userId, MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String key = S3Util.generateKey(domain, userId, fileName);
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
            return new S3UploadResult(key, S3Util.generateUrl(bucket, region, key));
        } catch (IOException e) {
            log.warn("s3 파일 삽입 실패 - key: {}, message: {}", key, e.getMessage(), e);
            throw new FileUploadException("파일 업로드 실패", e);
        }
    }

    @Override
    public List<String> uploadMany(String domain, String userId, List<MultipartFile> fileList) {
        if (fileList == null || fileList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> urlList = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                S3UploadResult result = upload(domain, userId, file);
                urlList.add(result.getUrl());
            }
            return urlList;
        } catch (Exception e) {
            deleteMany(urlList);
            throw e;
        }
    }


    private void delete(String url) {
        String key = S3Util.extractKeyFromUrl(url);
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());
        } catch (Exception e) {
            log.warn("삭제 도중 예외 발생 - key: {}, message: {}", key, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteMany(List<String> urls) {
        for (String url : urls) {
            delete(url);
        }
    }

}