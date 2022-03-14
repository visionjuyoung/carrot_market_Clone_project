package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.StatusCode;
import com.hmsh.carrotmarket.dto.CertificationNumberDTO;
import com.hmsh.carrotmarket.service.CertificationNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CertificationNumberService certificationNumberService;

    @GetMapping("/certification")
    public CResponseEntity<String> getCertificationNumber(@RequestParam String phoneNumber) {
        String certificationNumber = certificationNumberService.register(phoneNumber);
        return new CResponseEntity<>(true, StatusCode.OK, StatusCode.OK.getMessage(), certificationNumber);
    }

    @PostMapping("/certification")
    public CResponseEntity<Boolean> validateCertificationNumber(@RequestBody CertificationNumberDTO dto) {
        boolean result = certificationNumberService.validate(dto.getPhoneNumber(), dto.getNumber());
        if (result) {
            return new CResponseEntity<>(true, StatusCode.OK, true);
        } else {
            return new CResponseEntity<>(false, StatusCode.UNAUTHORIZED, "인증 실패", false);
        }
    }
}
