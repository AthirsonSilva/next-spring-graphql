package com.nextspring.modules.product;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, UUID> {

}
