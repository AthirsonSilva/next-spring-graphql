package com.nextspring.modules.category;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<CategoryEntity, UUID> {
}
