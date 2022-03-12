package com.hmsh.carrotmarket.service;

public interface CertificationNumberService {

    String register(String phoneNumber);

    boolean validate(String phoneNumber, String number);
}
