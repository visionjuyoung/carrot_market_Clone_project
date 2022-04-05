package com.hmsh.carrotmarket.dto;

import com.hmsh.carrotmarket.enumeration.BoardCategory;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardDTO {

    private Long id;

    private String content;

    private BoardCategory boardCategory;

    private String phoneNumber;

    private List<String> imagePathList;

    private MemberDTO member;

}
