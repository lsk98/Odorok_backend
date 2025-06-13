package com.odorok.OdorokApplication.s3.testController;

import com.odorok.OdorokApplication.s3.exception.UnauthorizedException;
import com.odorok.OdorokApplication.s3.util.S3Util;
import com.odorok.OdorokApplication.s3.service.S3Service;
import com.odorok.OdorokApplication.s3.validator.S3Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class TestController {
    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam String domain,
                                              @RequestParam String userId,
                                              @RequestParam MultipartFile file){
        String url = s3Service.upload(domain,userId,file);
        return ResponseEntity.ok(url);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam String userId,@RequestParam String url) {
        if(!S3Validator.isOwner(url,userId)){
            throw new UnauthorizedException("삭제 권한 없음");
        }
        String key = S3Util.extractKeyFromUrl(url);
        s3Service.delete(key);
        return ResponseEntity.noContent().build();
    }
}
