package com.practice.member.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(new CommonErrorDTO(
                e.getExceptionCode().getMessage(),
                e.getExceptionCode().getHttpStatus().value()
        ), HttpStatus.NOT_FOUND);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        e.printStackTrace();

        return new ResponseEntity<>(new CommonErrorDTO(
                ExceptionCode.UNKNOWN_ERROR.getMessage(),
                ExceptionCode.UNKNOWN_ERROR.getHttpStatus().value()
        ), HttpStatus.NOT_FOUND);
    }
}
