package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.ChatDTO;
import com.hmsh.carrotmarket.dto.ChatListDTO;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    boolean sendMessage(ChatDTO chatDTO);
    ChatListDTO getMessage(ChatDTO chatDTO);
}
