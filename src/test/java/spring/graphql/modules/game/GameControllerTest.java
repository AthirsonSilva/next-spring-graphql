package spring.graphql.modules.game;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nextspring.modules.game.GameEntity;
import com.nextspring.modules.game.GameRepository;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
	@Mock
	private GameRepository gameRepository;

	@Test
	public void games() {
		GameEntity firstgame = new GameEntity();
		GameEntity secondgame = new GameEntity();

		firstgame.setName("First game");
		firstgame.setPrice(BigDecimal.ONE);

		secondgame.setName("Second game");
		secondgame.setPrice(BigDecimal.ONE);

		when(gameRepository.findAll()).thenReturn(List.of(firstgame, secondgame));

		Iterable<GameEntity> result = gameRepository.findAll();

		assertNotNull(result);
		result.forEach(gameEntity -> {
			assertNotNull(gameEntity);
			assertTrue(gameEntity.getName().equals(firstgame.getName())
					|| gameEntity.getName().equals(secondgame.getName()));
		});
	}

	@Test
	public void game() {
		GameEntity gameEntity = new GameEntity();

		gameEntity.setName("test");
		gameEntity.setPrice(BigDecimal.ONE);

		when(gameRepository.findById(gameEntity.getId())).thenReturn(of(gameEntity));

		GameEntity result = gameRepository.findById(gameEntity.getId()).orElseThrow();

		assertNotNull(result);
		assertEquals(gameEntity, result);
	}

	@Test
	public void creategame() {
		GameEntity gameEntity = new GameEntity();

		gameEntity.setName("test");
		gameEntity.setPrice(BigDecimal.ONE);

		when(gameRepository.save(gameEntity)).thenReturn(gameEntity);

		GameEntity result = gameRepository.save(gameEntity);

		assertNotNull(result);
		assertEquals(gameEntity, result);
	}

	@Test
	public void updategame() {
		GameEntity gameEntity = new GameEntity();

		gameEntity.setName("test");
		gameEntity.setPrice(BigDecimal.ONE);

		when(gameRepository.save(gameEntity)).thenReturn(gameEntity);

		GameEntity result = gameRepository.save(gameEntity);

		assertNotNull(result);
		assertEquals(gameEntity, result);

		gameEntity.setName("test2");
		gameEntity.setPrice(BigDecimal.TEN);

		when(gameRepository.save(gameEntity)).thenReturn(gameEntity);

		result = gameRepository.save(gameEntity);

		assertNotNull(result);
		assertEquals(gameEntity, result);
	}

	@Test
	public void deletegame() {
		GameEntity gameEntity = new GameEntity();

		gameEntity.setName("test");
		gameEntity.setPrice(BigDecimal.ONE);

		when(gameRepository.save(gameEntity)).thenReturn(gameEntity);

		GameEntity result = gameRepository.save(gameEntity);

		assertNotNull(result);
		assertEquals(gameEntity, result);

		doNothing().when(gameRepository).deleteById(gameEntity.getId());
		gameRepository.deleteById(gameEntity.getId());

		verify(gameRepository, times(1)).deleteById(gameEntity.getId());
	}
}