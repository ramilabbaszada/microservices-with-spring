package org.portfolio.homeservice.utils.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}