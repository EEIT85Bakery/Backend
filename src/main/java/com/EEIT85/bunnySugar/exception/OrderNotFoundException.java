package com.EEIT85.bunnySugar.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
      super(message);
    }
}
