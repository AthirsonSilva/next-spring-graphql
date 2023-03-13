package spring.graphql.modules.product;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.graphql.modules.category.CategoryEntity;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
	private CategoryEntity category;

	@Column(name = "category_id", nullable = false)
	private UUID categoryId;

	public ProductEntity(String name, BigDecimal price, UUID categoryId) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}
}
