package com.example.cars.exception;

import com.example.cars.exception.types.EmptyDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            EmptyDataException.class
    })
    public ResponseEntity<?> errorNotFound(Exception ex) {
        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<?> badRequestError(Exception ex) {
        return ResponseEntity.badRequest().build();
    }
}
