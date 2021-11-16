package com.gamepass.advicer.entity.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {	
	private int id;
	
	private String name;
	
	private String image;
	
	private String completion_time;
	
	private String release_year;
	
	private long meta_score;
	
	private long user_score;
	
	private String genre;
}
