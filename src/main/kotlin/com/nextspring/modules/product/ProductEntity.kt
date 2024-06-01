package com.nextspring.modules.product

import com.nextspring.modules.category.CategoryEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document(collection = "products")
data class ProductEntity(
    @Id
    var id: UUID? = null,
    var name: String? = null,
    var price: BigDecimal? = null,
    var category: CategoryEntity? = null,
)

data class ProductInput(val name: String, val categoryId: UUID, val price: BigDecimal)
