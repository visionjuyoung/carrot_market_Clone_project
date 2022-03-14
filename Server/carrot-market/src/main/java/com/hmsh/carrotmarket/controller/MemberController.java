package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.entity.Member;
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

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable String phoneNumber) {

        Optional<Member> optionalMember = memberService.get(phoneNumber);

        if (optionalMember.isPresent()) {
            log.info("phoneNumber = {}, member = {}", phoneNumber, optionalMember);
            return new ResponseEntity<>(optionalMember.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getAddress(@PathVariable String address) {

        Optional<Member> optionalMember = memberService.get(address);

        if (optionalMember.isPresent()) {
            log.info("address = {}, member = {}", address, optionalMember);
            return new ResponseEntity<>(optionalMember.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
