package com.nextspring.modules.game;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<GameEntity, UUID> {

}
