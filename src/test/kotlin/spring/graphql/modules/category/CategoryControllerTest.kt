package spring.graphql.modules.category

import com.nextspring.modules.category.CategoryEntity
import com.nextspring.modules.category.CategoryRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CategoryControllerTest {
    @Mock
    private val categoryRepository: CategoryRepository = Mockito.mock(CategoryRepository::class.java)

    @Test
    fun addCategory() {
        val categoryEntity = CategoryEntity(null, "name")

        Mockito.`when`(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity)

        val result = categoryRepository.save(categoryEntity)

        Assertions.assertEquals(categoryEntity, result)
        Assertions.assertEquals(categoryEntity.name, result.name)
    }

    @Test
    fun categories() {
        val categoryEntities = listOf(
            CategoryEntity(null, "name1"),
            CategoryEntity(null, "name2"),
            CategoryEntity(null, "name3")
        )

        Mockito.`when`(categoryRepository.findAll()).thenReturn(categoryEntities)

        val result = categoryRepository.findAll()

        Assertions.assertEquals(categoryEntities, result)
        Assertions.assertEquals(categoryEntities.size, result.size)
    }

    @Test
    fun category() {
        val categoryEntity = CategoryEntity(null, "name")

        Mockito.`when`(categoryRepository.findById(categoryEntity.id!!)).thenReturn(Optional.of(categoryEntity))

        val result = categoryRepository.findById(categoryEntity.id!!).orElseThrow()!!

        Assertions.assertEquals(categoryEntity, result)
        Assertions.assertEquals(categoryEntity.name, result.name)
    }

    @Test
    fun updateCategory() {
        val categoryEntity = CategoryEntity(null, "name")

        Mockito.`when`(categoryRepository.findById(categoryEntity.id!!)).thenReturn(Optional.of(categoryEntity))

        val result = categoryRepository.findById(categoryEntity.id!!).orElseThrow()!!

        Assertions.assertEquals(categoryEntity, result)
        Assertions.assertEquals(categoryEntity.name, result.name)
    }

    @Test
    fun deleteCategory() {
        val categoryEntity = CategoryEntity(null, "name")

        Mockito.`when`(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity)

        val savedCategory = categoryRepository.save(categoryEntity)
        Assertions.assertNotNull(savedCategory)
        Assertions.assertEquals(categoryEntity, savedCategory)

        Mockito.doNothing().`when`(categoryRepository).delete(categoryEntity)
        categoryRepository.delete(categoryEntity)

        Mockito.verify(categoryRepository, Mockito.times(1)).delete(categoryEntity)
    }
}