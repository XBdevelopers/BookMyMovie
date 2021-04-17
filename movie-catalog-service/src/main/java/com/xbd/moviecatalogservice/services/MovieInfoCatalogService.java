package com.xbd.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xbd.moviecatalogservice.models.Movie;
import com.xbd.moviecatalogservice.models.MovieList;

@Service
public class MovieInfoCatalogService {

	@Autowired
	private RestTemplate restTamplate;
	
	@HystrixCommand(fallbackMethod = "getAllMovieInfoFallback")
	public MovieList getAllMovieInfo() {
		return restTamplate.getForObject("http://movie-info-service/movies", MovieList.class);
	}
	
	@HystrixCommand(fallbackMethod = "insertNewMovieFallback")
	public Movie insertNewMovie(Movie movie) {
		//return restTamplate.getForObject("http://movie-info-service/movies/update/" + movie, Movie.class);
		return restTamplate.postForEntity("http://movie-info-service/movies/update/" , movie, Movie.class).getBody();
	}
	
	public MovieList getAllMovieInfoFallback() {
		MovieList fallBackMovieList = new MovieList();
		fallBackMovieList.setMovies(
				Arrays.asList(new Movie())
				);
		return fallBackMovieList;
	}
	
	public Movie insertNewMovieFallback(Movie movie) {
		return new Movie();
	}
}
