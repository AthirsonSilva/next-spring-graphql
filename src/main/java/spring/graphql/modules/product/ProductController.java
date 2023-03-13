package spring.graphql.modules.product;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.UUID;

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
        public ProductEntity createProduct(@Argument ProductInput productEntity) {
            return productRepository.save(new ProductEntity(
                    productEntity.name(),
                    productEntity.price(),
                    productEntity.categoryId()
            ));
        }

        @MutationMapping
        public ProductEntity updateProduct(@Argument ProductInput productEntity) {
            ProductEntity product = productRepository.findById(productEntity.categoryId()).orElseThrow();

            product.setName(productEntity.name());

            return productRepository.save(product);
        }

        @MutationMapping
        public void deleteProduct(@Argument UUID id) {
            productRepository.deleteById(id);
        }

        record ProductInput(String name, UUID categoryId, BigDecimal price) {
        }
}
