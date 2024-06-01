package com.nextspring.modules.product

import lombok.RequiredArgsConstructor
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
@RequiredArgsConstructor
class ProductController(
    private val productRepository: ProductRepository
) {

    @QueryMapping
    fun products(): Iterable<ProductEntity> {
        return productRepository.findAll()
    }

    @QueryMapping
    fun product(@Argument id: UUID): ProductEntity {
        return productRepository.findById(id).orElseThrow()
    }

    @MutationMapping
    fun createProduct(@Argument input: ProductInput): ProductEntity {
        val product = ProductEntity()

        return productRepository.save(product)
    }

    @MutationMapping
    fun updateProduct(@Argument input: ProductInput): ProductEntity {
        val product = productRepository.findById(input.categoryId).orElseThrow()

        product.name = input.name

        return productRepository.save(product)
    }

    @MutationMapping
    fun deleteProduct(@Argument id: UUID) {
        productRepository.deleteById(id)
    }

}
