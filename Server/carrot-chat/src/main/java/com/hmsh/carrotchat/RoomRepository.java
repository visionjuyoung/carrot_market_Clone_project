package com.hmsh.carrotchat;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface RoomRepository extends ReactiveMongoRepository<Room, String> {

    @Query("{$and: [{productId: ?0}, {$or: [{seller: ?1}, {buyer: ?1}]}]}")
    Mono<Room> findRoomByProductIdAndBuyerOrSeller(Long productId, String user);


}
