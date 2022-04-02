package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.BoardReplyConverter;
import com.hmsh.carrotmarket.dto.BoardReplyRegistrationDTO;
import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.repository.BoardReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService {

    private final BoardReplyRepository boardReplyRepository;

    @Override
    public Long register(BoardReplyRegistrationDTO replyRegistrationDTO) {
        BoardReply reply = BoardReplyConverter.replyRegDTOToReply(replyRegistrationDTO);
        BoardReply save = boardReplyRepository.save(reply);
        return save.getId();
    }
}
