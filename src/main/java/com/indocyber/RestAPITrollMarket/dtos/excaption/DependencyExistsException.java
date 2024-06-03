package com.indocyber.RestAPITrollMarket.dtos.excaption;

public class DependencyExistsException extends RuntimeException{
    public DependencyExistsException(String message) {
        super(message);
    }
}
