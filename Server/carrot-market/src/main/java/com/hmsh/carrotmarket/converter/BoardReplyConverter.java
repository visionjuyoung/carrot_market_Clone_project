package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.BoardReplyDTO;
import com.hmsh.carrotmarket.dto.BoardReplyListDTO;
import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.entity.ProductImage;
import com.hmsh.carrotmarket.enumeration.Address;
import com.hmsh.carrotmarket.enumeration.TradeStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BoardReplyConverter {

    public static BoardReply replyDTOToReply(BoardReplyDTO dto) {
        return BoardReply.builder()
                .board(Board.builder().id(dto.getBoardId()).build())
                .member(Member.builder().phoneNumber(dto.getPhoneNumber()).build())
                .likes(0)
                .content(dto.getContent())
                .build();
    }

    public static BoardReplyDTO replyToReplyDTO(BoardReply boardReply, List<String> imagePathList) {
        return BoardReplyDTO.builder()
                .id(boardReply.getId())
                .content(boardReply.getContent())
                .boardId(boardReply.getBoard().getId())
                .imagePathList(imagePathList)
                .phoneNumber(boardReply.getMember().getPhoneNumber())
                .build();
    }

    public static BoardReplyDTO replyToLikeReplyDTO(Object[] objects) {

        return BoardReplyDTO.builder()
                .id((Long) objects[0])
                .content((String) objects[1])
                .boardId(((Long) objects[2]))
                .modDate((LocalDateTime) objects[3])
                .like((Long) objects[4])
                .build();
    }

    public static BoardReplyListDTO replyToReplyListDTO(BoardReply boardReply, List<String> imagePathList) {
        return BoardReplyListDTO.builder()
                .member(MemberConverter.memberToMemberDTO(boardReply.getMember()))
                .content(boardReply.getContent())
                .imagePathList(imagePathList)
                .modDate(boardReply.getModDate())
                .build();
    }
}
