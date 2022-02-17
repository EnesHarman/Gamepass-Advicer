package com.gamepass.advicer.repos;

import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.concretes.Genre;
import com.gamepass.advicer.entity.dtos.GameDto;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameDaoTest {

    GameDao underTest;
    GenreDao genreDao;

    @Autowired
    public GameDaoTest(GameDao underTest,GenreDao genreDao) {
        this.underTest = underTest;
        this.genreDao = genreDao;
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void tearDownBefore(){
        Genre genre = new Genre();
        genre.setGenre_id(1);
        genre.setGenreName("test");
        this.genreDao.save(genre);

        Game game = new Game();
        game.setGameId(1);
        game.setGenre(genre);
        game.setCompletionTime("56");
        game.setImage("denemee");
        game.setName("deneme1");
        game.setMetaScore(12);
        game.setReleaseYear("2001");
        game.setUserScore(15);
        underTest.save(game);
    }

    @Test
    void testGetGameList() {

        //given

        Pageable pageable = PageRequest.of(0,10);

        //when
        List<GameDto> games = underTest.getGameList(pageable);

        //then
        assertFalse(games.isEmpty());

    }


    @Test
    void testGetSingleGame() {

        //given
        int gameId=1;

        //when
        GameDto gameDto = underTest.getSingleGame(gameId);

        //then
        assertNotEquals(gameDto,null);

    }

    @Test
    void testGetGamesByMetaScoreAndUserScore() {

        //given
        long metaScore = 10;
        long userScore =5;

        //when
        List<GameDto> gameDto = underTest.getGamesByMetaScoreAndUserScore(metaScore,userScore);

        //then
        assertFalse(gameDto.isEmpty());

    }

    @Test
    void testGetGamesByMetaScore() {
        //given
        long metaScore =5;

        //when
        List<GameDto> gameDtos = underTest.getGamesByMetaScore(metaScore);

        //then
        assertFalse(gameDtos.isEmpty());
    }

    @Test
    void testGetGamesByUserScore() {
       //given
        long userScore =5;

        //when
        List<GameDto> gameDtos = this.underTest.getGamesByUserScore(userScore);

        //then
        assertFalse(gameDtos.isEmpty());
    }

    @Test
    void getFirstByOrderByGameId() {

        //when
        Game firstIdGame = this.underTest.getFirstByOrderByGameId();

        //then
        assertFalse(Objects.isNull(firstIdGame));
    }

    @Test
    void getByGenre_GenreName() {

        //when
        List<Game> games = underTest.getByGenre_GenreName("test");

        //then
        assertFalse(games.isEmpty());
    }
}