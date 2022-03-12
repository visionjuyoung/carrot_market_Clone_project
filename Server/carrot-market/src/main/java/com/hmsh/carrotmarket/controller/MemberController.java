package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDTO> getMember(@PathVariable String phoneNumber) {

        MemberDTO memberDTO = memberService.getDTO(phoneNumber);
        log.info("phoneNumber = {}, memberDTO = {}", phoneNumber, memberDTO);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }
}
