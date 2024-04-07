package org.portfolio.bookingservice.utils.results.abstracts;

import org.portfolio.bookingservice.business.messages.Messages;

public abstract class Result{
    private Boolean isSuccess;
    public Boolean getSuccess() {
        return isSuccess;
    }
    public String getMessage() {
        return message;
    }
    private String message ;
}
