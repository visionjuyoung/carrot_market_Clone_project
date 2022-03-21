package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/img")
    public ResponseEntity<byte[]> getImage(String filename) {
        return fileService.getImage(filename);
    }

}
