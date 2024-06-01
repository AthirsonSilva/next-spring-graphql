package spring.graphql.modules.category

import com.nextspring.modules.category.CategoryEntity
import com.nextspring.modules.category.CategoryRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import java.util.function.Consumer

@ExtendWith(MockitoExtension::class)
internal class CategoryRepositoryTest {
    @Mock
    private val categoryRepository: CategoryRepository = Mockito.mock(CategoryRepository::class.java)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun create() {
        val categoryEntity = CategoryEntity(null, "test")

        Mockito.`when`(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity)

        val result = categoryRepository.save(categoryEntity)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(categoryEntity, result)
    }

    @Test
    fun findById() {
        val categoryEntity = CategoryEntity(null, "test")

        Mockito.`when`(categoryRepository.findById(categoryEntity.id!!)).thenReturn(Optional.of(categoryEntity))

        val result = categoryRepository.findById(categoryEntity.id!!).orElseThrow()!!

        Assertions.assertNotNull(result)
        Assertions.assertEquals(categoryEntity, result)
    }

    @Test
    fun findAll() {
        val firstCategory = CategoryEntity(null, "First Category")
        val secondCategory = CategoryEntity(null, "Second Category")

        Mockito.`when`(categoryRepository.findAll()).thenReturn(listOf(firstCategory, secondCategory))

        val result: Iterable<CategoryEntity?> = categoryRepository.findAll()

        Assertions.assertNotNull(result)
        result.forEach(Consumer { categoryEntity: CategoryEntity? ->
            Assertions.assertNotNull(categoryEntity)
            Assertions.assertTrue(categoryEntity!!.name == firstCategory.name || categoryEntity.name == secondCategory.name)
        })

        Assertions.assertEquals(2L, result.spliterator().exactSizeIfKnown)
    }

    @Test
    fun deleteById() {
        val categoryEntity = CategoryEntity(null, "test")

        Mockito.`when`(categoryRepository.findById(categoryEntity.id!!)).thenReturn(Optional.of(categoryEntity))

        val result = categoryRepository.findById(categoryEntity.id!!).orElseThrow()!!

        Assertions.assertNotNull(result)
        Assertions.assertEquals(categoryEntity, result)
    }

    @Test
    fun update() {
        val categoryEntity = CategoryEntity(null, "test")

        Mockito.`when`(categoryRepository.findById(categoryEntity.id!!)).thenReturn(Optional.of(categoryEntity))

        val result = categoryRepository.findById(categoryEntity.id!!).orElseThrow()!!

        Assertions.assertNotNull(result)
        Assertions.assertEquals(categoryEntity, result)
    }
}