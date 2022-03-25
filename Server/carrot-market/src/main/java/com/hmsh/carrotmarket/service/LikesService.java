package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.LikesDTO;

public interface LikesService {

    Long register(LikesDTO likesDTO) throws Exception;

    void remove(LikesDTO likesDTO) throws Exception;
}
