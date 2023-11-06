package com.nextspring.modules.product;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

	private final ProductRepository productRepository;

	@QueryMapping
	public Iterable<ProductEntity> products() {
		return productRepository.findAll();
	}

	@QueryMapping
	public ProductEntity product(@Argument UUID id) {
		return productRepository.findById(id).orElseThrow();
	}

	@MutationMapping
	public ProductEntity createProduct(@Argument ProductInput input) {
		ProductEntity product = new ProductEntity(UUID.randomUUID(), input.name(), input.price(), null);

		return productRepository.save(product);
	}

	@MutationMapping
	public ProductEntity updateProduct(@Argument ProductInput input) {
		ProductEntity product = productRepository.findById(input.categoryId()).orElseThrow();

		product.setName(input.name());

		return productRepository.save(product);
	}

	@MutationMapping
	public void deleteProduct(@Argument UUID id) {
		productRepository.deleteById(id);
	}

	record ProductInput(String name, UUID categoryId, BigDecimal price) {
	}
}
