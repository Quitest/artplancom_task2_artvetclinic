package com.example.vetclinic.exception;

public class UserAccountExistException extends RuntimeException {
    public UserAccountExistException(String message) {
        super(message);
    }
}
