package com.hmsh.carrotchat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;


    public Mono<Chat> saveMessage(ChatDTO chatDTO) {
        Chat chat = convertToChat(chatDTO);
        return chatRepository.save(chat);
    }

    public Flux<Chat> getChatList(Long productId) {
        return chatRepository.getAllByProductId(productId);
    }

    private Chat convertToChat(ChatDTO chatDTO) {
        return Chat.builder()
                .productId(chatDTO.getProductId())
                .sellerId(chatDTO.getSellerId())
                .senderId(chatDTO.getSenderId())
                .message(chatDTO.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
