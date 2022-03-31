package com.hmsh.carrotmarket.handler;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CResponseEntity<Object> handleInternalServerError(Exception e) {
        log.error("exception", e);
        return new CResponseEntity<>(false, StatusCode.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("IllegalArgumentException", e);
        return new CResponseEntity<>(false, StatusCode.BAD_REQUEST, null);
    }
}
