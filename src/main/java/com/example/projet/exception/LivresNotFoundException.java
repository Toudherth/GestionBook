package com.example.projet.exception;

public class LivresNotFoundException extends RuntimeException {
    public LivresNotFoundException(String message) {
        super(message);
    }
    
}
