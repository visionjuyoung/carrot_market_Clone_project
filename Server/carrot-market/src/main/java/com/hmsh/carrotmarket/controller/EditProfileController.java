package com.hmsh.carrotmarket.controller;


import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.service.SignUpService;
import com.hmsh.carrotmarket.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EditProfileController {

    private final SignUpService signUpService;
    private final FileUtil fileUtil;

    @PostMapping("/edit")
    public ResponseEntity editProfile (@RequestPart SignUpDTO dto,
                                       @RequestPart(required = false) MultipartFile file) throws Exception{
        File newFileName = fileUtil.makeNewFileName(file);

        file.transferTo(newFileName);

        log.info("newFileName={}", newFileName);
        log.info("phoneNumber={}, address={}, name={}", dto.getPhoneNumber(), dto.getAddress(), dto.getName());


        boolean result = signUpService.signUpMember(dto, newFileName);


        if (result) {
            return new ResponseEntity<>(newFileName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}