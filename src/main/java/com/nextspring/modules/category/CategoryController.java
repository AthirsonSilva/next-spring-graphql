package com.nextspring.modules.category;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@MutationMapping
	CategoryEntity addCategory(@Argument CategoryInput category) {
		return categoryService.addCategory(category);
	}

	@QueryMapping
	Iterable<CategoryEntity> getCategories() {
		return categoryService.getCategories();
	}

	@QueryMapping
	CategoryEntity findCategoryById(@Argument UUID id) {
		return categoryService.findCategoryById(id);
	}

	@MutationMapping
	CategoryEntity updateCategory(@Argument UUID id, @Argument CategoryInput category) {
		return categoryService.updateCategory(id, category);
	}

	@MutationMapping
	void deleteCategory(@Argument UUID id) {
		categoryService.deleteCategory(id);
	}

}
