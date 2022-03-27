package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.ChatDTO;
import com.hmsh.carrotmarket.dto.ChatListDTO;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat")
    public CResponseEntity<Object> sendMessage(ChatDTO chatDTO) throws IOException {

            boolean result = chatService.sendMessage(chatDTO);

        if (result) {
            return new CResponseEntity<>(true, StatusCode.OK, "전송 성공");
        } else {
            return new CResponseEntity<>(false, StatusCode.UNAUTHORIZED, "메시지가 없거나 전송 실패");
        }
    }

    @GetMapping("/chat")
    public ChatListDTO getAllMessage(ChatDTO chatDTO){
        return chatService.getMessage(chatDTO);
    }
}
