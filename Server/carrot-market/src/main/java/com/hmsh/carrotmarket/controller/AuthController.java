package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.dto.CertificationNumberDTO;
import com.hmsh.carrotmarket.service.CertificationNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CertificationNumberService certificationNumberService;

    @GetMapping("/certification")
    public ResponseEntity<String> getCertificationNumber(@RequestParam String phoneNumber) {
        String certificationNumber = certificationNumberService.register(phoneNumber);
        return new ResponseEntity<>(certificationNumber, HttpStatus.OK);
    }

    @PostMapping("/certification")
    public ResponseEntity<Boolean> validateCertificationNumber(@RequestBody CertificationNumberDTO dto) {
        boolean result = certificationNumberService.validate(dto.getPhoneNumber(), dto.getNumber());

        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
}
