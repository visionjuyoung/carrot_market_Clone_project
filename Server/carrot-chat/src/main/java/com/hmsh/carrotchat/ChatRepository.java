package com.hmsh.carrotchat;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    @Tailable
    @Query(value = "{$and: [{productId: ?0}, {$or: [{sender: ?1, receiver: ?2}, {sender: ?2, receiver: ?1}]}]}")
    Flux<Chat> getChatsBySenderAndReceiver(Long productId, String user1, String user2);

    @Tailable
    Flux<Chat> getAllByProductId(Long productId);

}
