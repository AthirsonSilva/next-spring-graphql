package spring.graphql.modules.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void addCategory() {
        CategoryEntity categoryEntity = new CategoryEntity("name");

        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        CategoryEntity result = categoryRepository.save(categoryEntity);

        assertEquals(categoryEntity, result);
        assertEquals(categoryEntity.getName(), result.getName());
    }

    @Test
    void categories() {
        List<CategoryEntity> categoryEntities = List.of(
                new CategoryEntity("name1"),
                new CategoryEntity("name2"),
                new CategoryEntity("name3")
        );

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        List<CategoryEntity> result = categoryRepository.findAll();

        assertEquals(categoryEntities, result);
        assertEquals(categoryEntities.size(), result.size());
    }

    @Test
    void category() {
        CategoryEntity categoryEntity = new CategoryEntity("name");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(of(categoryEntity));

        CategoryEntity result = categoryRepository.findById(categoryEntity.getId()).orElseThrow();

        assertEquals(categoryEntity, result);
        assertEquals(categoryEntity.getName(), result.getName());
    }

    @Test
    void updateCategory() {
        CategoryEntity categoryEntity = new CategoryEntity("name");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(of(categoryEntity));

        CategoryEntity result = categoryRepository.findById(categoryEntity.getId()).orElseThrow();

        assertEquals(categoryEntity, result);
        assertEquals(categoryEntity.getName(), result.getName());
    }

    @Test
    void deleteCategory() {
        CategoryEntity categoryEntity = new CategoryEntity("name");

        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        assertNotNull(savedCategory);
        assertEquals(categoryEntity, savedCategory);

        doNothing().when(categoryRepository).delete(categoryEntity);
        categoryRepository.delete(categoryEntity);

        verify(categoryRepository, times(1)).delete(categoryEntity);
    }
}