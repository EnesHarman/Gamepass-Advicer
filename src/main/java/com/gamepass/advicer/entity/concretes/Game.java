package com.gamepass.advicer.entity.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Games")
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameId;
	
	@Column(name="name", columnDefinition = "nvarchar(255)", nullable = false)
	private String name;
	
	@Column(name="image", columnDefinition = "nvarchar(255)")
	private String image;
	
	@Column(name="completion_time", columnDefinition = "varchar(10)" )
	private String completionTime;
	
	@Column(name="release_year", columnDefinition =  "varchar(10)", nullable = false)
	private String releaseYear;
	
	@Column(name="meta_score", columnDefinition =  "long")
	private long metaScore;
	
	@Column(name="user_score", columnDefinition =  "long")
	private long userScore;
	
	@ManyToOne()
	@JoinColumn(name = "genre_id")
	Genre genre;
}
