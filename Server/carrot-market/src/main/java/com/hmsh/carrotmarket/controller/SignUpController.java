package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.dto.CertificationNumberDTO;
import com.hmsh.carrotmarket.dto.FileDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity signUpMember(@RequestParam MultipartFile file, @RequestBody SignUpDTO dto) throws Exception{

        if(!file.isEmpty()){
            FileDTO fileDTO = FileDTO.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .build();

            File newFileName = new File(fileDTO.getUuid() + "_" + fileDTO.getFileName());

            try{
                file.transferTo(newFileName);
            } catch (IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(file.getOriginalFilename());
            }

        }

        boolean result = signUpService.signUpMember(dto);

        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}
