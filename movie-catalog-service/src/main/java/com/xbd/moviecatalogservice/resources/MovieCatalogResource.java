package com.xbd.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xbd.moviecatalogservice.models.Catalog;
import com.xbd.moviecatalogservice.models.Movie;
import com.xbd.moviecatalogservice.models.MovieList;
import com.xbd.moviecatalogservice.models.NewCatalog;
import com.xbd.moviecatalogservice.models.Rating;
import com.xbd.moviecatalogservice.services.MovieInfoCatalogService;
import com.xbd.moviecatalogservice.services.RatingDataCatalogService;

@Controller
public class MovieCatalogResource {

	@Autowired
	private MovieInfoCatalogService movieInfo;
	
	@Autowired
	private RatingDataCatalogService ratingData;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getWelcomePage(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/catalogs")
	public String getMovieCatalogs(Model model) {
		MovieList movieList = movieInfo.getAllMovieInfo();
		List<Catalog> catalogItems =  movieList.getMovies().stream().map(movie -> ratingData.getCatalogItem(movie)).collect(Collectors.toList());
		model.addAttribute("catalogItems", catalogItems);
		return "index";
	}

	@RequestMapping(value = "/catalogs/update", method = RequestMethod.POST)
	public String getNewMoviePage(Model model) {
		model.addAttribute("newCatalog", new NewCatalog());
		return "index";
	}
	
	@RequestMapping(value = "/catalogs/update/add", method = RequestMethod.POST)
	public String insertMovieById(@ModelAttribute("newCatalog") NewCatalog newCatalog, BindingResult bind, Model model) {
		System.out.println("*******************************Hit the Catalog Ciontroller **********************");
		Catalog catalog = new Catalog();
		Movie movie = new Movie();
		long l=(long)123;
		movie.setMovieId(l);
		movie.setName(newCatalog.getName());
		movie.setDesc(newCatalog.getDesc());
		catalog.setMovie(movie);
		System.out.println("*******************************Set movies **********************");
		//Movie updatedMovie = movieInfo.insertNewMovie(catalog.getMovie());
		Movie updatedMovie = movieInfo.insertNewMovie(catalog.getMovie());
		
		if(null != updatedMovie.getMovieId()) {
			/*
			 * Rating rating = new Rating(); rating.setMovieId(updatedMovie.getMovieId());
			 * rating.setStars(newCatalog.getStars()); catalog.setRating(rating); Rating
			 * updatedRating = ratingData.insertNewMovierating(catalog.getRating()); if(null
			 * != updatedRating && (updatedMovie.getMovieId() ==
			 * updatedRating.getMovieId()))
			 */ 
				model.addAttribute("message", "Movie details have been updated with id "+updatedMovie+".");
			model.addAttribute("newCatalog", new NewCatalog());
		}
		return "index";
	}
}
