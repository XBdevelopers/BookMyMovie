package com.xbd.movieinfoservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbd.movieinfoservice.entities.Movie;
import com.xbd.movieinfoservice.entities.MovieList;
import com.xbd.movieinfoservice.repositories.MovieInfoRepository;

@Service
public class MovieInfoService {

	@Autowired
	private MovieInfoRepository repository;
	
	public MovieList fetchAllMovies(){
		List<Movie> allMovies = repository.findAll();

		MovieList movieList = new MovieList();
		
		if(allMovies.size()>0) 
			movieList.setMovies(allMovies);
		else
			movieList.setMovies(new ArrayList<Movie>());
		
		return movieList;
	}
	
	public Movie fetchMovieById(Long movieId) {
		Optional<Movie> movie = repository.findById(movieId);
		
		if(movie.isPresent())
			return movie.get();
		else
			return new Movie();
	}
	
	public String createOrUpadateMovieList(String newMovieInfo) {
		Optional<Movie> movie = repository.findById((long) 1234);
		
		System.out.println("*******************************info servece hits sevice class**********************");
		if(movie.isPresent()) {
			Movie newMovieEntity = movie.get();
			newMovieEntity.setName(newMovieInfo);
			newMovieEntity.setDesc("TSTDSC");
			System.out.println("*******************************info servece hits sevice class1**********************");
			newMovieEntity = repository.save(newMovieEntity);
			return newMovieEntity.getName();
		}
		else {
			Movie newMovie =new Movie();
			newMovie.setName(newMovieInfo);
			newMovie.setDesc("TSTDSC");
			newMovie = repository.save(newMovie);
			return newMovie.getName();
		}
	}
	
	public boolean deleteMovieById(Long id) {
		Optional<Movie> entity = repository.findById(id);
		
		if(entity.isPresent()) {
			repository.deleteById(id);
			return true;
		}else
			return false;
	}
}
