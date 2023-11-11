package com.nextspring.modules.game;

import java.util.List;
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

		List<GameEntity> findAll = gameRepository.findAll();

		log.info("Found games: {}", findAll.size());

		return findAll;
	}

	public GameEntity findGameById(String id) {
		log.info("findGameById -> id: {}", id);

		GameEntity foundCategory = gameRepository.findById(UUID.fromString(id)).orElseThrow();

		log.info("Found game: {}", foundCategory);

		return foundCategory;
	}

	public GameEntity addGame(GameInput game, UUID categoryId) {
		log.info("addGame -> game: {}, categoryId: {}", game, categoryId);

		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow();

		log.info("Found category for the game: {}", categoryEntity);

		GameEntity gameEntity = GameEntity.builder()
				.id(UUID.randomUUID())
				.name(game.name())
				.price(game.price())
				.category(categoryEntity)
				.build();

		log.info("Adding game: {}", gameEntity);

		return gameRepository.insert(gameEntity);
	}

	public GameEntity updateGame(UUID id, GameInput game) {
		log.info("updateGame: ID -> {}, game -> {}", id, game);

		GameEntity gameEntity = gameRepository.findById(id).orElseThrow();

		log.info("Found game: {}", gameEntity);

		gameEntity.setName(game.name());
		gameEntity.setPrice(game.price());

		log.info("Updated game: {}", gameEntity);

		return gameRepository.save(gameEntity);
	}

	public void deleteGame(UUID id) {
		log.info("deleteGame -> id: {}", id);

		gameRepository.deleteById(id);
	}

}
