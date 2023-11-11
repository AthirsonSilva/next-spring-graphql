package com.nextspring.modules.category;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryEntity addCategory(CategoryInput category) {
		log.info("Adding category: {}", category);

		CategoryEntity categoryEntity = CategoryEntity
				.builder()
				.id(UUID.randomUUID())
				.name(category.name())
				.build();

		return categoryRepository.save(categoryEntity);
	}

	public Iterable<CategoryEntity> getCategories() {
		log.info("Getting categories");

		List<CategoryEntity> findAll = categoryRepository.findAll();

		log.info("Found categories: {}", findAll.size());

		return findAll;
	}

	public CategoryEntity findCategoryById(UUID id) {
		log.info("Finding category by id: {}", id);

		CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();

		log.info("Found category: {}", foundCategory);

		return foundCategory;
	}

	public CategoryEntity updateCategory(UUID id, CategoryInput category) {
		log.info("Updating category: ID -> {}, category -> {}", category);

		CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();

		log.info("Found category: {}", foundCategory);

		foundCategory.setName(category.name());

		log.info("Updated category: {}", foundCategory);

		categoryRepository.save(foundCategory);

		return foundCategory;
	}

	public void deleteCategory(UUID id) {
		log.info("Deleting category: {}", id);

		categoryRepository.deleteById(id);
	}

}
