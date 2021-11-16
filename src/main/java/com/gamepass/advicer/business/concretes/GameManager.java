package com.gamepass.advicer.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gamepass.advicer.business.abstracts.GameService;
import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;
import com.gamepass.advicer.core.utilities.result.abstracts.Result;
import com.gamepass.advicer.core.utilities.result.concretes.ErrorResult;
import com.gamepass.advicer.core.utilities.result.concretes.SuccessDataResult;
import com.gamepass.advicer.core.utilities.result.concretes.SuccessResult;
import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.dtos.GameDto;
import com.gamepass.advicer.repos.GameDao;

@Service
public class GameManager implements GameService{

	private final GameDao gameDao; 
	
	@Autowired
	public GameManager(GameDao gameDao) {
		this.gameDao = gameDao;
	}
	
	@Override
	public DataResult<List<GameDto>> getGameList(Optional<Integer> pageNo, Optional<Integer> pageSize) {
		int pageno = pageNo.isPresent() &&pageNo.get()>0 ? pageNo.get() : 1;
		int pagesize = pageSize.isPresent() &&pageSize.get()>=10 && pageSize.get()<=20 ? pageSize.get() : 10;
		
		Pageable pageable = PageRequest.of(pageno-1, pagesize);
		
		return new SuccessDataResult<List<GameDto>>("Games listed.",this.gameDao.getGameList(pageable)); 
		
	}
	

	@Override
	public DataResult<GameDto> getSingleGame(int gameId) {
		return new SuccessDataResult<GameDto>("Game listed.",this.gameDao.getSingleGame(gameId));
	}

	@Override
	public DataResult<GameDto> getRandomGame(Optional<String> gameGenre,Optional<Integer> minUserScore,Optional<Integer> minMetaScore) {
		
		if(!gameGenre.isPresent()) {
			
			if(minMetaScore.isPresent() && minUserScore.isPresent()) {
				List<GameDto> games = this.gameDao.getGamesByMetaScoreAndUserScore(minMetaScore.get(), minUserScore.get());
				int index =(int) (Math.random() *games.size());
				return new SuccessDataResult<GameDto>("Random game listed.",games.get(index));
			}
			
			else if(minMetaScore.isPresent() && !minUserScore.isPresent()) {
				List<GameDto> games = this.gameDao.getGamesByMetaScore(minMetaScore.get());
				int index =(int) (Math.random() *games.size());
				return new SuccessDataResult<GameDto>("Random game listed.",games.get(index));
			}
			
			else if(!minMetaScore.isPresent() && minUserScore.isPresent()) {
				List<GameDto> games = this.gameDao.getGamesByUserScore(minUserScore.get());
				int index =(int) (Math.random() *games.size());
				return new SuccessDataResult<GameDto>("Random game listed.",games.get(index));
			}
			else {
				Game game= this.gameDao.getFirstByOrderByGameId();
				long count = this.gameDao.count();
				long upper = count +game.getGameId();
				
				int id = (int) (Math.random()*(upper - game.getGameId())) + game.getGameId();
				Optional<Game> randomGame = this.gameDao.findById(id);
				GameDto dto = new GameDto(
						randomGame.get().getGameId(),
						randomGame.get().getName(),
						randomGame.get().getImage(),
						randomGame.get().getCompletionTime(),
						randomGame.get().getReleaseYear(),
						randomGame.get().getMetaScore(),
						randomGame.get().getUserScore(),
						randomGame.get().getGenre().getGenreName()
					);
				return new SuccessDataResult<GameDto>("Random game listed.",dto);
			}
		}
		else {
			List<Game> games = new ArrayList<Game>();
			games = this.gameDao.getByGenre_GenreName(gameGenre.get());
			int index =(int) (Math.random() *games.size());
			Game randomGame = games.get(index);
			GameDto dto = new GameDto(
						randomGame.getGameId(),
						randomGame.getName(),
						randomGame.getImage(),
						randomGame.getCompletionTime(),
						randomGame.getReleaseYear(),
						randomGame.getMetaScore(),
						randomGame.getUserScore(),
						randomGame.getGenre().getGenreName()
					);
					
			return new SuccessDataResult<GameDto>("Random game listed.",dto);
		}
	}

	@Override
	public Result addGame(Game game) {
		try {
			this.gameDao.save(game);
			return new SuccessResult("Game added to the service.");
		} catch (Exception e) {
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result updateGame(Game game) {
		try {
			Game upGame= this.gameDao.getById(game.getGameId());
			upGame = game;
			this.gameDao.save(upGame);
			return new SuccessResult("Game updated successfuly.");
		} catch (Exception e) {
			return new ErrorResult(e.getMessage());
		}
	}

	@Override
	public Result deleteGame(int gameId) {
		try {
			this.gameDao.deleteById(gameId);
			return new SuccessResult("Game deleted successfuly.");
		} catch (Exception e) {
			return new ErrorResult(e.getMessage());
		}
	}
	

}
