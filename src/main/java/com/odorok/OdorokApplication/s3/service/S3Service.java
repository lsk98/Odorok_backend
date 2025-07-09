package com.odorok.OdorokApplication.s3.service;

import com.odorok.OdorokApplication.s3.dto.S3UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface S3Service {
    //파일을 항상 리스트 단위로 삭제 업로드
    public List<String> uploadMany(String domain, String userId, List<MultipartFile> fileList);
    public void deleteMany(List<String> urls);
}
