package com.hmsh.carrotmarket;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusCode {
    OK(200, "요청 성공"),
    UNAUTHORIZED(401, "유효하지 않음"),
    NOT_FOUND(404, "요청한 리소스를 찾을 수 없음"),
    INTERNAL_SERVER_ERROR(500, "서버 에러")
    ;

    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
