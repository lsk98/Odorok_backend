package com.odorok.OdorokApplication.security.exception;

public class DuplicateUserEmailException extends RuntimeException{
    public DuplicateUserEmailException(String msg) {
        super(msg);
    }
}
