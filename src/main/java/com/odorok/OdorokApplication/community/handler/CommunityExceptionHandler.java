package com.odorok.OdorokApplication.community.handler;

import com.odorok.OdorokApplication.s3.exception.FileUploadException;
import com.odorok.OdorokApplication.s3.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommunityExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleUpload(FileUploadException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("파일 업로드 실패: " + e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("런타임 오류: " + e.getMessage());
    }
}