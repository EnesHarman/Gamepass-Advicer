package com.gamepass.advicer.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamepass.advicer.business.abstracts.GameService;
import com.gamepass.advicer.core.utilities.result.abstracts.DataResult;
import com.gamepass.advicer.core.utilities.result.abstracts.Result;
import com.gamepass.advicer.entity.concretes.Game;
import com.gamepass.advicer.entity.dtos.GameDto;

@RestController
@RequestMapping("/api/games")
public class GameController {
	
	private final GameService gameService;
	
	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getGameList(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize){
		DataResult<List<GameDto>> result = this.gameService.getGameList(pageNo, pageSize);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.internalServerError().body("There is an error with server. Please try again later.");
		}
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<?> getSingleGame(@PathVariable int gameId){
		DataResult<GameDto> result = this.gameService.getSingleGame(gameId);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.internalServerError().body("There is an error with server. Please try again later.");
		}
	}
	
	@GetMapping("/random")
	public ResponseEntity<?> getRandomGame(@RequestParam Optional<String> gameGenre, @RequestParam Optional<Integer> minUserScore, @RequestParam Optional<Integer> minMetaScore){
		DataResult<GameDto>result = this.gameService.getRandomGame(gameGenre,minUserScore,minMetaScore);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.internalServerError().body("There is an error with server. Please try again later.");
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addGame(@RequestBody Game game){
		Result result = this.gameService.addGame(game);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.internalServerError().body(result.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateGame(@RequestBody Game game){
		Result result = this.gameService.updateGame(game);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.internalServerError().body(result.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{gameId}")
	public ResponseEntity<?> deleteGame(@PathVariable int gameId){
		Result result = this.gameService.deleteGame(gameId);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.internalServerError().body(result.getMessage());
		}
	}
}
