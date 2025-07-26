package com.odorok.OdorokApplication.security.exception;

public class JWTTokenExpiredException extends RuntimeException{
    public JWTTokenExpiredException(String msg) {
        super(msg);
    }
}
