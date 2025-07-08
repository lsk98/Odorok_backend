package com.odorok.OdorokApplication.commons.exception;

//파일 업로드 시의 예외 객체
public class FileUploadException extends RuntimeException {
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}