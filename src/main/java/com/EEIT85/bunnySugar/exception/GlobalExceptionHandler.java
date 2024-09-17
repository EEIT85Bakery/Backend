package com.EEIT85.bunnySugar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("error", "Not Found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", "/products"); // 產品相關的路徑

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFoundException(OrderNotFoundException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("error", "Order Not Found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", "/orders"); // 訂單相關的路徑

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFoundException(MemberNotFoundException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("error", "Member Not Found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", "/memberPage");

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}

