package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.util.FileUtil;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final FileUtil fileUtil;
    private boolean result;
    @PostMapping("/signup")
    public CResponseEntity<String> signUpMember(SignUpDTO dto, MultipartFile file) throws Exception {
        File newFileName = null;
        if (!Objects.isNull(file) && !file.isEmpty()) {
            newFileName = fileUtil.makeNewFileName(file);
            file.transferTo(newFileName);
            log.info("newFileName={}", newFileName);
        }

        log.info("phoneNumber={}, address={}, name={}", dto.getPhoneNumber(), dto.getAddress(), dto.getName());

        boolean result = signUpService.signUpMember(dto, newFileName);

        if (result) {
            return new CResponseEntity<>(true, StatusCode.OK, "회원가입 성공");
        } else {
            return new CResponseEntity<>(false, StatusCode.UNAUTHORIZED, null);
        }
    }
}
