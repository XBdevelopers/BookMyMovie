package com.xbd.movieinfoservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MOVIES_INFO")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
	@SequenceGenerator(name = "movie_seq", sequenceName = "movie_sequence_identity", initialValue = 101, allocationSize = 10)
	private Long movieId;
	
	@Column(name = "MOVIE_NAME")
	private String name;
	
	@Column(name = "MOVIE_DESCRIPTION")
	private String desc;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
