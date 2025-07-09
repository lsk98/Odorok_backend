package com.odorok.OdorokApplication.s3.service;

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

    //s3에 하나의 파일을 업로드하는 기능 url값을 반환함
    private String upload(String domain, String userId, MultipartFile file) {
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
            return S3Util.generateUrl(bucket, region, key);
        } catch (IOException e) {
            log.warn("s3 파일 삽입 실패 - key: {}, message: {}", key, e.getMessage(), e);
            throw new FileUploadException("파일 업로드 실패", e);
        }
    }
    //s3에 다수의 파일을 업로드하는 기능 url List를 반환함
    @Override
    public List<String> uploadMany(String domain, String userId, List<MultipartFile> fileList) {
        if (fileList == null || fileList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> urlList = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                String result = upload(domain, userId, file);
                urlList.add(result);
            }
            return urlList;
        } catch (Exception e) {
            deleteMany(urlList);
            throw e;
        }
    }
    //url을 이용하여 파일을 삭제하는 기능
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
    //url을 이용하여 s3에서 다수의 파일을 삭제하는 기능
    @Override
    public void deleteMany(List<String> urls) {
        for (String url : urls) {
            delete(url);
        }
    }

}