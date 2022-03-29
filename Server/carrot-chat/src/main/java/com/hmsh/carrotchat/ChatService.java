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

    private final RoomRepository roomRepository;


    public Flux<Chat> getMessage(Long productId, String user1, String user2) {
        return chatRepository.getChatsBySenderAndReceiver(productId, user1, user2)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Transactional
    public Mono<Chat> saveMessage(ChatDTO chatDTO) {
        return Mono.just(chatDTO)
                .map(this::convertToChat)
                .flatMap(this::setRoomId)
                .flatMap(chatRepository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Chat> getUserChats(String user) {
        return chatRepository.getChatsByUser(user)
                .subscribeOn(Schedulers.boundedElastic());
    }


    private Mono<Chat> setRoomId(Chat chat) {
        return roomRepository
                .findRoomByProductIdAndBuyerOrSeller(chat.getProductId(), chat.getSender())
                .defaultIfEmpty(Room.builder()
                        .productId(chat.getProductId())
                        .seller(chat.getReceiver())
                        .buyer(chat.getSender())
                        .productId(chat.getProductId())
                        .build())
                .flatMap(roomRepository::save)
                .map((room -> {
                    chat.setRoomId(room.getId());
                    return chat;
                }));
    }

    private Chat convertToChat(ChatDTO chatDTO) {
        return Chat.builder()
                .productId(chatDTO.getProductId())
                .sender(chatDTO.getSender())
                .receiver(chatDTO.getReceiver())
                .message(chatDTO.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
