package com.hmsh.carrotmarket.util;

import com.hmsh.carrotmarket.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Service
public class FileUtil {

    public File makeNewFileName(MultipartFile file){
        FileDTO fileDTO = FileDTO.builder()
                .uuid(UUID.randomUUID().toString())
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();

        File newFileName = new File("/home/ubuntu/upload/" + fileDTO.getUuid() + "_" + fileDTO.getFileName());

        return newFileName;
    }
}
