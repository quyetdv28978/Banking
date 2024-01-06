package com.banking.exception.advice;

import com.banking.exception.CCCDisExistException;
import com.banking.exception.ErrorResponse;
import com.banking.exception.KhachHangNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class KhachHangControllerAdvisor {

    @ExceptionHandler(CCCDisExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //409
    public ErrorResponse CCCDisExistHandler(CCCDisExistException ex, WebRequest request){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
        return error;
    }

    @ExceptionHandler(KhachHangNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse KhachHangNotFoundHandler(KhachHangNotFoundException ex, WebRequest request){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
        return error;
    }
}
