package com.nextspring.modules.category

import lombok.RequiredArgsConstructor
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
@RequiredArgsConstructor
class CategoryController(
    private val categoryRepository: CategoryRepository
) {

    @MutationMapping
    fun addCategory(@Argument input: CategoryInput): CategoryEntity {
        val categoryEntity = CategoryEntity(UUID.randomUUID(), input.name)

        return categoryRepository.save(categoryEntity)
    }

    @QueryMapping
    fun categories(): Iterable<CategoryEntity?> {
        return categoryRepository.findAll()
    }

    @QueryMapping
    fun category(@Argument id: UUID): CategoryEntity {
        return categoryRepository.findById(id).orElseThrow()!!
    }

    @QueryMapping
    fun updateCategory(@Argument id: UUID): CategoryEntity {
        return categoryRepository.findById(id).orElseThrow()!!
    }

    @MutationMapping
    fun deleteCategory(@Argument id: UUID) {
        categoryRepository.deleteById(id)
    }

}
