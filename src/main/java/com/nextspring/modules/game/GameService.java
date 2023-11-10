package com.nextspring.modules.game;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nextspring.modules.category.CategoryEntity;
import com.nextspring.modules.category.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GameService {

	private final GameRepository gameRepository;
	private final CategoryRepository categoryRepository;

	public Iterable<GameEntity> getGames() {
		log.info("getGames");

		return gameRepository.findAll();
	}

	public GameEntity findGameById(String id) {
		log.info("findGameById -> id: {}", id);

		return gameRepository.findById(UUID.fromString(id)).orElseThrow();
	}

	public GameEntity addGame(GameInput game, UUID categoryId) {
		log.info("addGame -> game: {}, categoryId: {}", game, categoryId);

		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow();

		GameEntity gameEntity = GameEntity.builder()
				.id(UUID.randomUUID())
				.name(game.name())
				.price(game.price())
				.category(categoryEntity)
				.build();

		return gameRepository.insert(gameEntity);
	}

	public GameEntity updateGame(GameInput game) {
		log.info("updateGame -> game: {}", game);

		GameEntity gameEntity = gameRepository.findById(game.categoryId()).orElseThrow();

		gameEntity.setName(game.name());
		gameEntity.setPrice(game.price());

		return gameRepository.save(gameEntity);
	}

	public void deleteGame(UUID id) {
		log.info("deleteGame -> id: {}", id);

		gameRepository.deleteById(id);
	}

}
