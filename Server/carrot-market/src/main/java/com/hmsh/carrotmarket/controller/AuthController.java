package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.dto.CertificationNumberDTO;
import com.hmsh.carrotmarket.entity.SignUpMember;
import com.hmsh.carrotmarket.service.CertificationNumberService;
import com.hmsh.carrotmarket.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CertificationNumberService certificationNumberService;
    private final SignUpService signUpService;

    @GetMapping("/certification")
    public ResponseEntity<String> getCertificationNumber(@RequestParam String phoneNumber) {
        String certificationNumber = certificationNumberService.register(phoneNumber);
        return new ResponseEntity<>(certificationNumber, HttpStatus.OK);
    }

    @PostMapping("/certification")
    public ResponseEntity validateCertificationNumber(@RequestBody CertificationNumberDTO dto) {
        boolean result = certificationNumberService.validate(dto.getPhoneNumber(), dto.getNumber());

        if (result) {
            if(signUpService.memberCheck(dto.getPhoneNumber())){
                Optional<SignUpMember> getMember = signUpService.getMember(dto.getPhoneNumber());
                return new ResponseEntity<>(getMember, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("notExist", HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
}
