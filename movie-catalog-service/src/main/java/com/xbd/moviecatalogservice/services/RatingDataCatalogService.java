package com.xbd.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xbd.moviecatalogservice.models.Catalog;
import com.xbd.moviecatalogservice.models.Movie;
import com.xbd.moviecatalogservice.models.Rating;

@Service
public class RatingDataCatalogService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getCatalogItemFallback")
	public Catalog getCatalogItem(Movie movie) {
		Rating rating = restTemplate.getForObject("http://rating-data-service/ratings/" + movie.getMovieId(), Rating.class);
		Catalog newCatalog = new Catalog();
		newCatalog.setMovie(movie);
		newCatalog.setRating(rating);
		return newCatalog;
	}
	
	@HystrixCommand(fallbackMethod = "insertNewMovieratingFallback")
	public Rating insertNewMovierating(Rating rating) {
		return restTemplate.getForObject("http://rating-data-service/ratings/update/" + rating, Rating.class);
	}
	
	public Catalog getCatalogItemFallback(Movie movie) {
		return new Catalog();
	}
	
	public Rating insertNewMovieratingFallback(Rating rating) {
		return new Rating();
	}
}
