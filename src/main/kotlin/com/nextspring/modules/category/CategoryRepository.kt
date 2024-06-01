package com.nextspring.modules.category

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CategoryRepository : MongoRepository<CategoryEntity, UUID>
