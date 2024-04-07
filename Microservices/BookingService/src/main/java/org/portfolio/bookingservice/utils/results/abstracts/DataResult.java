package org.portfolio.bookingservice.utils.results.abstracts;

public abstract class DataResult<T> extends Result {
    private T data;

    public T getData() {
        return data;
    }
}
