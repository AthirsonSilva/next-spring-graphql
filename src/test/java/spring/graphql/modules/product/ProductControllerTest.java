package spring.graphql.modules.product;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductRepository productRepository;

    @Test
    public void products() {
        ProductEntity firstProduct = new ProductEntity();
        ProductEntity secondProduct = new ProductEntity();

        firstProduct.setName("First Product");
        firstProduct.setPrice(BigDecimal.ONE);
        firstProduct.setCategoryId(UUID.randomUUID());

        secondProduct.setName("Second Product");
        secondProduct.setPrice(BigDecimal.ONE);
        secondProduct.setCategoryId(UUID.randomUUID());

        when(productRepository.findAll()).thenReturn(List.of(firstProduct, secondProduct));

        Iterable<ProductEntity> result = productRepository.findAll();

        assertNotNull(result);
        result.forEach(productEntity -> {
            assertNotNull(productEntity);
            assertTrue(productEntity.getName().equals(firstProduct.getName()) || productEntity.getName().equals(secondProduct.getName()));
        });
    }

    @Test
    public void product() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName("test");
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setCategoryId(UUID.randomUUID());

        when(productRepository.findById(productEntity.getId())).thenReturn(of(productEntity));

        ProductEntity result = productRepository.findById(productEntity.getId()).orElseThrow();

        assertNotNull(result);
        assertEquals(productEntity, result);
    }

    @Test
    public void createProduct() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName("test");
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setCategoryId(UUID.randomUUID());

        when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity result = productRepository.save(productEntity);

        assertNotNull(result);
        assertEquals(productEntity, result);
    }

    @Test
    public void updateProduct() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName("test");
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setCategoryId(UUID.randomUUID());

        when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity result = productRepository.save(productEntity);

        assertNotNull(result);
        assertEquals(productEntity, result);

        productEntity.setName("test2");
        productEntity.setPrice(BigDecimal.TEN);

        when(productRepository.save(productEntity)).thenReturn(productEntity);

        result = productRepository.save(productEntity);

        assertNotNull(result);
        assertEquals(productEntity, result);
    }

    @Test
    public void deleteProduct() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName("test");
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setCategoryId(UUID.randomUUID());

        when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity result = productRepository.save(productEntity);

        assertNotNull(result);
        assertEquals(productEntity, result);

        doNothing().when(productRepository).deleteById(productEntity.getId());
        productRepository.deleteById(productEntity.getId());

        verify(productRepository, times(1)).deleteById(productEntity.getId());
    }
}