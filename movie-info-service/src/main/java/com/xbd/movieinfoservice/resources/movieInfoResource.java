package com.xbd.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xbd.movieinfoservice.entities.Movie;
import com.xbd.movieinfoservice.entities.MovieList;
import com.xbd.movieinfoservice.services.MovieInfoService;

@RestController
public class movieInfoResource {

	@Autowired
	private MovieInfoService service;
	
	@RequestMapping({"/", "/movies"})
	public MovieList getAllMovies(){
		return service.fetchAllMovies();
	}
	
	@RequestMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable("movieId") Long id) {
		return service.fetchMovieById(id);
	}
	
	//@RequestMapping("/movies/update/{movie}")
	@PostMapping("/movies/update")
	public Movie updateMovieList(@RequestBody Movie mv) {
		System.out.println("*******************************Hit the info Service**********************");
		String movie=mv.getName();
		System.out.println("*******************************Hit the info Service**********************");
		String movieStatus = service.createOrUpadateMovieList(movie);
		Movie mvTmp=new Movie();
		mvTmp.setName(movieStatus);
		mvTmp.setDesc("Description");
		mvTmp.setMovieId((long) 12345);
		if(null == mvTmp.getMovieId() || mvTmp.getMovieId() == 0)
			return new Movie();
		else 
			return mvTmp;
	}
	
	@RequestMapping("/movies/delete/{id}")
	public String deleteMovie(@PathVariable("id") Long movieId) {
		boolean status = service.deleteMovieById(movieId);
		if(status)
			return "Hurrayh! Record has been deleted successfully.";
		else
			return "Oops! Failed to delete the reacord.";
	}
}
