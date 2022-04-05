package com.hmsh.carrotchat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;


    @PostMapping("/send")
    public Mono<Chat> saveMessage(@RequestBody ChatDTO chatDTO) {
        log.info("send message chatDTO = {}", chatDTO);
        return chatService.saveMessage(chatDTO);
    }

    @GetMapping(value = "/productId/{productId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getUserChats(@PathVariable Long productId) {
        log.info("채팅 조회 productId = {}", productId);
        return chatService.getChatList(productId);
    }
}
