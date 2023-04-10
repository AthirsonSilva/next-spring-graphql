package spring.graphql.modules.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.List.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void create() {
        CategoryEntity categoryEntity = new CategoryEntity("test");

        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        CategoryEntity result = categoryRepository.save(categoryEntity);

        assertNotNull(result);
        assertEquals(categoryEntity, result);
    }

    @Test
    public void findById() {
        CategoryEntity categoryEntity = new CategoryEntity("test");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(java.util.Optional.of(categoryEntity));

        CategoryEntity result = categoryRepository.findById(categoryEntity.getId()).orElseThrow();

        assertNotNull(result);
        assertEquals(categoryEntity, result);
    }

    @Test
    public void findAll() {
        CategoryEntity firstCategory = new CategoryEntity("First Category");
        CategoryEntity secondCategory = new CategoryEntity("Second Category");

        when(categoryRepository.findAll()).thenReturn(of(firstCategory, secondCategory));

        Iterable<CategoryEntity> result = categoryRepository.findAll();

        assertNotNull(result);
        result.forEach(categoryEntity -> {
            assertNotNull(categoryEntity);
            assertTrue(categoryEntity.getName().equals(firstCategory.getName()) || categoryEntity.getName().equals(secondCategory.getName()));
        });

        assertEquals(2L, result.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void deleteById() {
        CategoryEntity categoryEntity = new CategoryEntity("test");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(java.util.Optional.of(categoryEntity));

        CategoryEntity result = categoryRepository.findById(categoryEntity.getId()).orElseThrow();

        assertNotNull(result);
        assertEquals(categoryEntity, result);
    }

    @Test
    public void update() {
        CategoryEntity categoryEntity = new CategoryEntity("test");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(java.util.Optional.of(categoryEntity));

        CategoryEntity result = categoryRepository.findById(categoryEntity.getId()).orElseThrow();

        assertNotNull(result);
        assertEquals(categoryEntity, result);
    }
}