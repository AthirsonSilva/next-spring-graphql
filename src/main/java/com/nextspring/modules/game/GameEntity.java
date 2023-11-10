package com.nextspring.modules.game;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nextspring.modules.category.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "games")
public class GameEntity {
	@Id
	private UUID id;
	private String name;
	private BigDecimal price;
	private CategoryEntity category;
}
