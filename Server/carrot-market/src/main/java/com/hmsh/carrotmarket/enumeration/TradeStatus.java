package com.hmsh.carrotmarket.enumeration;

public enum TradeStatus {
    BEFORE_RESERVATION("예약 전"),
    RESERVED("예약 중"),
    COMPLETE("거래 완료")
    ;

    TradeStatus(String description) {}
}
