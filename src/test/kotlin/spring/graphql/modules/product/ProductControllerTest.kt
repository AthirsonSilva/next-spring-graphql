package spring.graphql.modules.product

import com.nextspring.modules.product.ProductEntity
import com.nextspring.modules.product.ProductRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*
import java.util.function.Consumer

@ExtendWith(MockitoExtension::class)
class ProductControllerTest {
    @Mock
    private val productRepository: ProductRepository = Mockito.mock(ProductRepository::class.java)

    @Test
    fun products() {
        val firstProduct = ProductEntity()
        val secondProduct = ProductEntity()

        firstProduct.name = "First Product"
        firstProduct.price = BigDecimal.ONE

        secondProduct.name = "Second Product"
        secondProduct.price = BigDecimal.ONE

        Mockito.`when`(productRepository.findAll()).thenReturn(listOf(firstProduct, secondProduct))

        val result: Iterable<ProductEntity> = productRepository.findAll()

        Assertions.assertNotNull(result)
        result.forEach(Consumer { productEntity: ProductEntity ->
            Assertions.assertNotNull(productEntity)
            Assertions.assertTrue(productEntity.name == firstProduct.name || productEntity.name == secondProduct.name)
        })
    }

    @Test
    fun product() {
        val productEntity = ProductEntity()

        productEntity.id = UUID.randomUUID()
        productEntity.name = "test"
        productEntity.price = BigDecimal.ONE

        Mockito.`when`(productRepository.findById(productEntity.id!!)).thenReturn(Optional.of(productEntity))

        val result = productRepository.findById(productEntity.id!!).orElseThrow()

        Assertions.assertNotNull(result)
        Assertions.assertEquals(productEntity, result)
    }

    @Test
    fun createProduct() {
        val productEntity = ProductEntity()

        productEntity.name = "test"
        productEntity.price = BigDecimal.ONE

        Mockito.`when`(productRepository.save(productEntity)).thenReturn(productEntity)

        val result = productRepository.save(productEntity)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(productEntity, result)
    }

    @Test
    fun updateProduct() {
        val productEntity = ProductEntity()

        productEntity.name = "test"
        productEntity.price = BigDecimal.ONE

        Mockito.`when`(productRepository.save(productEntity)).thenReturn(productEntity)

        var result = productRepository.save(productEntity)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(productEntity, result)

        productEntity.name = "test2"
        productEntity.price = BigDecimal.TEN

        Mockito.`when`(productRepository.save(productEntity)).thenReturn(productEntity)

        result = productRepository.save(productEntity)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(productEntity, result)
    }

    @Test
    fun deleteProduct() {
        val productEntity = ProductEntity()

        productEntity.id = UUID.randomUUID()
        productEntity.name = "test"
        productEntity.price = BigDecimal.ONE

        Mockito.`when`(productRepository.save(productEntity)).thenReturn(productEntity)

        val result = productRepository.save(productEntity)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(productEntity, result)

        Mockito.doNothing().`when`(productRepository).deleteById(productEntity.id!!)
        productRepository.deleteById(productEntity.id!!)

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productEntity.id!!)
    }
}