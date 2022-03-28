package com.hmsh.carrotchat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @GetMapping(value = "/productId/{productId}/user1/{user1}/user2/{user2}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessage(@PathVariable Long productId, @PathVariable String user1,
                                 @PathVariable String user2) {
        return chatService.getMessage(productId, user1, user2);
    }

    @PostMapping("/send")
    public Mono<Chat> saveMessage(@RequestBody ChatDTO chatDTO) {
        return chatService.saveMessage(chatDTO);
    }

    @GetMapping(value = "/list/user/{user}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getUserChats(@PathVariable String user) {
        return chatService.getUserChats(user);
    }
}
