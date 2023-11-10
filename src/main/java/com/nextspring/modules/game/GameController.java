package com.nextspring.modules.game;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GameController {

	private final GameService gameService;

	@QueryMapping
	public Iterable<GameEntity> getGames() {
		return gameService.getGames();
	}

	@QueryMapping
	public GameEntity findGameById(@Argument String id) {
		return gameService.findGameById(id.toString());
	}

	@MutationMapping
	public GameEntity addGame(@Argument GameInput game) {
		return gameService.addGame(game, game.categoryId());
	}

	@MutationMapping
	public GameEntity updateGame(@Argument GameInput game) {
		return gameService.updateGame(game);
	}

	@MutationMapping
	public void deleteGame(@Argument UUID id) {
		gameService.deleteGame(id);
	}

}
