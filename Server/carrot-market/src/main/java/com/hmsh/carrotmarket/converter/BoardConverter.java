package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.BoardDTO;
import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.Member;

import java.util.List;

public class BoardConverter {

    public static Board dtoToBoard(BoardDTO boardDTO) {
        return Board.builder()
                .member(Member.builder().phoneNumber(boardDTO.getPhoneNumber()).build())
                .content(boardDTO.getContent())
                .boardCategory(boardDTO.getBoardCategory())
                .build();
    }

    public static BoardDTO boardToBoardDTO(Board board, List<String> imagePathList) {
        return BoardDTO.builder()
                .id(board.getId())
                .content(board.getContent())
                .boardCategory(board.getBoardCategory())
                .imagePathList(imagePathList)
                .member(MemberConverter.memberToMemberDTO(board.getMember()))
                .build();
    }
}
