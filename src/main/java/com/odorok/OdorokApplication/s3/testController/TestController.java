package com.odorok.OdorokApplication.s3.testController;

import com.odorok.OdorokApplication.s3.dto.S3UploadResult;
import com.odorok.OdorokApplication.s3.util.S3Util;
import com.odorok.OdorokApplication.s3.service.S3ServiceImpl;
import com.odorok.OdorokApplication.s3.validator.S3Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class TestController {
    private final S3ServiceImpl s3Service;

    @PostMapping("/upload")
    public ResponseEntity<S3UploadResult> uploadImage(@RequestParam String domain,
                                              @RequestParam String userId,
                                              @RequestParam MultipartFile file){
        S3UploadResult url = s3Service.upload(domain,userId,file);
        return ResponseEntity.ok(url);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam String userId,@RequestParam String url) {
        String key = S3Util.extractKeyFromUrl(url);
        s3Service.delete(key);
        return ResponseEntity.noContent().build();
    }
}
