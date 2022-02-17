package com.gamepass.advicer.business.abstracts;

import com.gamepass.advicer.business.concretes.GameManager;
import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;
import com.gamepass.advicer.core.utilities.result.abstracts.Result;
import com.gamepass.advicer.core.utilities.result.concretes.SuccessDataResult;
import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.concretes.Genre;
import com.gamepass.advicer.entity.dtos.GameDto;
import com.gamepass.advicer.repos.GameDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock GameDao gameDao;
    private GameService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameManager(gameDao);
    }

    @Test
    void testGetGameList() {
        //given
        Optional<Integer> pageNo =Optional.of(0);
        Optional<Integer> pageSize = Optional.of(10);
        Pageable pageable = PageRequest.of(0,10);
        //when
        DataResult<List<GameDto>> games = underTest.getGameList(pageNo,pageSize);
        //then
        verify(gameDao).getGameList(pageable);
        assertFalse(!games.getSuccess());
    }

    @Test
    void testGetSingleGame() {
        //given
        int gameID=1;

        //when
        DataResult<GameDto> gameResult = this.underTest.getSingleGame(gameID);

        //then
        verify(gameDao).getSingleGame(gameID);
        assertFalse(!gameResult.getSuccess());
    }

    @Test
    void testGetRandomGame() {
        //given
        Optional<String> gameGenre = Optional.of("test");
        Optional<Integer> minUserScore = Optional.of(5);
        Optional<Integer> minMetaScore = Optional.of(15);

        //when
        DataResult<GameDto> gameDtoDataResult = this.underTest.getRandomGame(gameGenre,minUserScore,minMetaScore);

        //then
        if(!gameGenre.isPresent()) {

            if(minMetaScore.isPresent() && minUserScore.isPresent()) {
                verify(gameDao).getGamesByMetaScoreAndUserScore(minMetaScore.get(),minUserScore.get());
            }

            else if(minMetaScore.isPresent() && !minUserScore.isPresent()) {
                verify(gameDao).getGamesByMetaScore(minMetaScore.get());
            }

            else if(!minMetaScore.isPresent() && minUserScore.isPresent()) {
                verify(gameDao).getGamesByUserScore(minUserScore.get());
            }
            else {
                verify(gameDao).getFirstByOrderByGameId();
                verify(gameDao).count();
                verify(gameDao).findById(any());
            }
        }
        else {
            verify(gameDao).getByGenre_GenreName(any());
        }
        assertFalse(!gameDtoDataResult.getSuccess());
    }

    @Test
    void addGame() {
        //given
        Game game = new Game();
        game.setGameId(1);
        game.setGenre(new Genre(1));
        game.setCompletionTime("56");
        game.setImage("denemee");
        game.setName("deneme1");
        game.setMetaScore(12);
        game.setReleaseYear("2001");
        game.setUserScore(15);

        //when
        Result result = this.underTest.addGame(game);

        //then
        ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);
        verify(gameDao).save(gameArgumentCaptor.capture());

        Game capturedGame = gameArgumentCaptor.getValue();
        assertThat(capturedGame).isEqualTo(game);
        assertFalse(!result.getSuccess());
    }

    @Test
    void updateGame() {
        //given
        Game game = new Game();
        game.setGameId(1);
        game.setGenre(new Genre(1));
        game.setCompletionTime("56");
        game.setImage("denemee");
        game.setName("deneme1");
        game.setMetaScore(12);
        game.setReleaseYear("2001");
        game.setUserScore(15);

        //when
        Result result=  this.underTest.updateGame(game);

        //then
        ArgumentCaptor<Game> gameArgumentCaptor= ArgumentCaptor.forClass(Game.class);
        verify(gameDao).getById(game.getGameId());
        verify(gameDao).save(gameArgumentCaptor.capture());
        assertThat(gameArgumentCaptor.getValue()).isEqualTo(game);
        assertFalse(!result.getSuccess());
    }

    @Test
    void deleteGame() {
        //given
        int gameId = 1;
        //when
        Result result =this.underTest.deleteGame(gameId);
        //then
        verify(gameDao).deleteById(any());
        assertFalse(!result.getSuccess());
    }
}