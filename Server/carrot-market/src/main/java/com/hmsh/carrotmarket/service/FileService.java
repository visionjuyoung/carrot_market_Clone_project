package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.ImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    ResponseEntity<byte[]> getImage(String filename);

    List<ImageDTO> uploadFiles(MultipartFile[] uploadFiles);
}
