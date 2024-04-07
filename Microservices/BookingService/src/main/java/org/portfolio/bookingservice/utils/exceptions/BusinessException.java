package org.portfolio.bookingservice.utils.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}