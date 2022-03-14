package com.hmsh.carrotmarket;


import lombok.Data;

@Data
public class CResponseEntity<T> {

    private final Boolean isSuccess;
    private final StatusCode code;
    private final String message;
    private final T result;

    public CResponseEntity(boolean isSuccess, StatusCode code, String message, T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public CResponseEntity(boolean isSuccess, StatusCode code, T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = code.getMessage();
        this.result = result;
    }
}
