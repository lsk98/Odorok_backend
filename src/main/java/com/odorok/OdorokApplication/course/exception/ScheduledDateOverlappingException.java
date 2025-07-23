package com.odorok.OdorokApplication.course.exception;

public class ScheduledDateOverlappingException extends RuntimeException{
    public ScheduledDateOverlappingException(String msg) {
        super(msg);
    }
}
