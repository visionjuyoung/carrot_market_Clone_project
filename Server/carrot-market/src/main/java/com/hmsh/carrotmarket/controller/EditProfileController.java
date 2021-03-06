package com.hmsh.carrotmarket.controller;


import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.service.SignUpService;
import com.hmsh.carrotmarket.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EditProfileController {

    private final SignUpService signUpService;
    private final FileUtil fileUtil;
    private boolean result;

    @PatchMapping("/edit")
    public CResponseEntity<MemberDTO> editProfile (EditUserDTO dto,
                                                   MultipartFile file) throws Exception{
        File newFileName = null;

        if (!Objects.isNull(file) && !file.isEmpty()) {
            newFileName = fileUtil.makeNewFileName(file);
            file.transferTo(newFileName);
            log.info("newFileName={}", newFileName);
        }

        log.info("phoneNumber={}, address={}, name={}", dto.getPhoneNumber(), dto.getAddress(), dto.getName());

        MemberDTO result = signUpService.editMember(dto, newFileName);

        return new CResponseEntity<MemberDTO>(true, StatusCode.OK, result);

    }
}
