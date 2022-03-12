package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.entity.CertificationNumber;
import com.hmsh.carrotmarket.repository.CertificationNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationNumberServiceImpl implements CertificationNumberService {

    private final CertificationNumberRepository certificationNumberRepository;

    @Override
    public String register(String phoneNumber) {
        int number = (int) (Math.random() * 10000);
        String randomNumber = String.format("%04d", number);

        CertificationNumber certificationNumber = CertificationNumber.builder()
                .phoneNumber(phoneNumber)
                .number(randomNumber)
                .build();

        CertificationNumber save = certificationNumberRepository.save(certificationNumber);

        return save.getNumber();
    }

    @Override
    public boolean validate(String phoneNumber, String number) {
        Optional<CertificationNumber> optionalCertificationNumber
                = certificationNumberRepository.findById(phoneNumber);

        if (optionalCertificationNumber.isPresent()) {
            CertificationNumber certificationNumber = optionalCertificationNumber.get();
            return certificationNumber.getNumber().equals(number);
        }

        return false;
    }
}
