package com.EEIT85.bunnySugar.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {

        super(s); // 將訊息傳給父類別 RuntimeException 以便後續處理
        System.out.println("Resource not Found");
    }
}
