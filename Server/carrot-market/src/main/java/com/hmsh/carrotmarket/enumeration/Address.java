package com.hmsh.carrotmarket.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final Map<String, Address> BY_REGION =
            Arrays.stream(values()).collect(Collectors.toMap(Address::getRegion, a -> a));

    Address(String region) {
        this.region = region;
    }

    @JsonValue
    public String getRegion() {
        return this.region;
    }

    public static Address getByRegion(String region) {
        return BY_REGION.getOrDefault(region, null);
    }


}
