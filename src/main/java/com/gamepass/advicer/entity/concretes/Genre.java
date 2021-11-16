package com.gamepass.advicer.entity.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Genres")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","games"})
public class Genre {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genre_id;
	
	@Column(name="name")
	private String genreName;
	
	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	List<Game> games;
}
