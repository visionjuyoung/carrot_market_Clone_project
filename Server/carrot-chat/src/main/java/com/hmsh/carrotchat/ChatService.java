package com.hmsh.carrotchat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;


    public Flux<Chat> getMessage(Long productId, String user1, String user2) {
        return chatRepository.getChatsBySenderAndReceiver(productId, user1, user2)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Chat> saveMessage(ChatDTO chatDTO) {
        Chat chat = Chat.builder()
                .productId(chatDTO.getProductId())
                .sender(chatDTO.getSender())
                .receiver(chatDTO.getReceiver())
                .message(chatDTO.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        return chatRepository.save(chat);
    }

    public Map<Long, List<Chat>> getUserChats(String user) {
        Flux<Chat> chat = chatRepository.getChatsByUser(user)
                .subscribeOn(Schedulers.boundedElastic());

       Map<Long, List<Chat>> userChat = chat.toStream().collect(Collectors.groupingBy(Chat::getProductId));

        return userChat;
    }
}
