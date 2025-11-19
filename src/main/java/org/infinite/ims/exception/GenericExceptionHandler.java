package org.infinite.ims.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.infinite.ims.util.ApiResponseBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(e.getClass() + e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) throws JsonProcessingException {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.CONFLICT)
                        .message("Data Integrity Violation "+ e.getMessage())
                        .build()
        );
    }
}
