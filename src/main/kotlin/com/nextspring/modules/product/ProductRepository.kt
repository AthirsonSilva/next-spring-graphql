package com.nextspring.modules.product

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ProductRepository : MongoRepository<ProductEntity, UUID>
