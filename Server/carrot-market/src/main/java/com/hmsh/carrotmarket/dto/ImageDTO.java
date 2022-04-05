package com.hmsh.carrotmarket.dto;

import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ImageDTO {

    private String uuid;

    private String imgName;

    private String path;

    public String getImageURL() {
        return path + "/" + uuid + "_" + imgName;
    }
}
