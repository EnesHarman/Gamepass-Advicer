package com.gamepass.advicer.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.dtos.GameDto;

public interface GameDao extends JpaRepository<Game, Integer>{
	
	@Query("Select new com.gamepass.advicer.entity.dtos.GameDto "
			+ "(g.id, g.name, g.image, g.completionTime,g.releaseYear, g.metaScore, g.userScore, gen.genreName) From Genre gen Inner Join gen.games g")
	List<GameDto> getGameList(Pageable pageable);
	
	@Query("Select new com.gamepass.advicer.entity.dtos.GameDto "
			+ "(g.id, g.name, g.image, g.completionTime,g.releaseYear, g.metaScore, g.userScore, gen.genreName) From Genre gen Inner Join gen.games g where g.id=:gameId")
	GameDto getSingleGame(int gameId);
	
	@Query("Select new com.gamepass.advicer.entity.dtos.GameDto "
			+ "(g.id, g.name, g.image, g.completionTime,g.releaseYear, g.metaScore, g.userScore, gen.genreName) From Genre gen Inner Join gen.games g where g.metaScore> :metaScore and g.userScore > :userScore")
	List<GameDto> getGamesByMetaScoreAndUserScore(long metaScore, long userScore);
	
	@Query("Select new com.gamepass.advicer.entity.dtos.GameDto "
			+ "(g.id, g.name, g.image, g.completionTime,g.releaseYear, g.metaScore, g.userScore, gen.genreName) From Genre gen Inner Join gen.games g where g.metaScore> :metaScore")
	List<GameDto> getGamesByMetaScore(long metaScore);
	
	@Query("Select new com.gamepass.advicer.entity.dtos.GameDto "
			+ "(g.id, g.name, g.image, g.completionTime,g.releaseYear, g.metaScore, g.userScore, gen.genreName) From Genre gen Inner Join gen.games g where g.userScore> :userScore")
	List<GameDto> getGamesByUserScore(long userScore);
	
	Game getFirstByOrderByGameId();
	
	List<Game> getByGenre_GenreName(String genreName);
}
