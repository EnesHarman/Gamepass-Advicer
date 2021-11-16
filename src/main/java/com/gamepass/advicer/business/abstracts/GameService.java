package com.gamepass.advicer.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;
import com.gamepass.advicer.core.utilities.result.abstracts.Result;
import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.dtos.GameDto;

public interface GameService {

	DataResult<List<GameDto>> getGameList(Optional<Integer> pageNo, Optional<Integer> pageSize);

	DataResult<GameDto> getSingleGame(int gameId);

	DataResult<GameDto> getRandomGame(Optional<String> gameGenre,Optional<Integer> minUserScore,Optional<Integer> minMetaScore);

	Result addGame(Game game);

	Result updateGame(Game game);

	Result deleteGame(int gameId);

}
