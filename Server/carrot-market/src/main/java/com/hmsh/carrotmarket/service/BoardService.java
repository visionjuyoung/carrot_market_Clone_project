package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

    Long register(BoardDTO boardDTO, MultipartFile[] files);

    BoardDTO get(Long id);

}
