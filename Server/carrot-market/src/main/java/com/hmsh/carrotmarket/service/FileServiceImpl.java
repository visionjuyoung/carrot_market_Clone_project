package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.ImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${upload_path}")
    private String uploadPath;


    /**
     * 파일명과 같은 이미지를 바이트코드로 변환해 ResponseEntity 로 반환
     * @param filename 파일명
     * @return 이미지 정보가 담긴 ResponseEntity
     */
    public ResponseEntity<byte[]> getImage(String filename) {
        byte[] result;
        HttpHeaders headers = new HttpHeaders();
        try {
            File file = new File(URLDecoder.decode(filename, "UTF-8"));
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            result = FileCopyUtils.copyToByteArray(file);

        } catch (IOException e) {
            log.warn("이미지 변환 실패 {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 업로드된 이미지 파일을 저장
     * @param uploadFiles 업로드된 이미지 파일
     * @return 업로드된 이미지 파일을 ImageDTO 로 변환한 리스트
     */
    public List<ImageDTO> uploadImageFiles(MultipartFile[] uploadFiles) {

        List<ImageDTO> imgDTOList = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFiles) {

            if (!Objects.requireNonNull(multipartFile.getContentType()).startsWith("image")) {
                log.warn("업로드한 파일이 이미지 파일이 아님");
            }

            String originalFilename = multipartFile.getOriginalFilename();
            String filename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + uuid + "_" + filename;

            Path savePath = Paths.get(saveName);

            try {
                multipartFile.transferTo(savePath);

                imgDTOList.add(ImageDTO.builder()
                        .imgName(filename)
                        .path(uploadPath)
                        .uuid(uuid)
                        .build());

            } catch (IOException e) {
                log.error("이미지 저장 에러 imgName = {}", filename);
                e.printStackTrace();
            }

        }

        return imgDTOList;
    }
}
