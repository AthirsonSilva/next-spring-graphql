package com.nextspring.modules.category

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "categories")
data class CategoryEntity(
    var id: UUID?,
    var name: String,
)

data class CategoryInput(val name: String)
