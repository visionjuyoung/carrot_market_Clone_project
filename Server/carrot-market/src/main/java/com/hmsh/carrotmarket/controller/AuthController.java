package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.StatusCode;
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
    public CResponseEntity<String> getCertificationNumber(@RequestParam String phoneNumber) {
        String certificationNumber = certificationNumberService.register(phoneNumber);
        return new CResponseEntity<>(true, StatusCode.OK, StatusCode.OK.getMessage(), certificationNumber);
    }

    @PostMapping("/certification")
    public ResponseEntity validateCertificationNumber(@RequestBody CertificationNumberDTO dto) {
        boolean result = certificationNumberService.validate(dto.getPhoneNumber(), dto.getNumber());

        if (result) {
            //전화번호가 이미 있으면 그 전화번호의 회원정보 객체 전달, 없으면 회원가입 페이지 요청
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
