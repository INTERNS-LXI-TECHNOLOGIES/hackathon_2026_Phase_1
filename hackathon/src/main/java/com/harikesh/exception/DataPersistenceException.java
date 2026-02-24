package com.harikesh.exception;

public class DataPersistenceException extends Exception {
    String message ="Testing";
    public DataPersistenceException(String message) {
        //super(message);
        this.message=message;
    }
}