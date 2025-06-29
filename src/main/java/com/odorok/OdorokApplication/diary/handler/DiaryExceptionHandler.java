package com.odorok.OdorokApplication.diary.handler;

import com.odorok.OdorokApplication.commons.exception.BadRequestException;
import com.odorok.OdorokApplication.commons.exception.GptCommunicationException;
import com.odorok.OdorokApplication.commons.exception.NotFoundException;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.odorok.OdorokApplication.commons.response.CommonResponseBuilder.*;


@RestControllerAdvice
public class DiaryExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException e) {
        ResponseRoot response = fail(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException e) {
        ResponseRoot response = fail(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(GptCommunicationException.class)
    public ResponseEntity<?> handleGptCommunication(GptCommunicationException e) {
        ResponseRoot response = fail(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalState(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(fail(e.getMessage()));
    }
}
