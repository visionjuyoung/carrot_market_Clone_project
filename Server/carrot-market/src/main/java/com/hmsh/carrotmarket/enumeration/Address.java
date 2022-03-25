package com.hmsh.carrotmarket.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Address {
    IUI("이의동"),
    HA("하동"),
    WONCHEON("원천동"),
    MAETAN("매탄동"),
    YEONGTONG("영통동"),
    SIN("신동"),
    MANGPO("망포동")
    ;

    private final String region;

    Address(String region) {
        this.region = region;
    }

    @JsonValue
    public String getRegion() {
        return this.region;
    }
}
