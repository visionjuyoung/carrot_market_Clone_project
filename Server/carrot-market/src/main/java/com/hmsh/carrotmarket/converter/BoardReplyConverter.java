package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.BoardReplyRegistrationDTO;
import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.BoardReply;

public class BoardReplyConverter {

    public static BoardReply replyRegDTOToReply(BoardReplyRegistrationDTO dto) {
        return BoardReply.builder()
                .board(Board.builder().id(dto.getBoardId()).build())
                .member(Member.builder().phoneNumber(dto.getPhoneNumber()).build())
                .content(dto.getContent())
                .build();
    }
}
