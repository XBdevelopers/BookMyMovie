package com.xbd.ratingdataservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xbd.ratingdataservice.entities.Rating;
import com.xbd.ratingdataservice.entities.RatingList;
import com.xbd.ratingdataservice.services.RatingDataService;

@RestController
public class RatingDataResource {

	@Autowired
	private RatingDataService service;
	
	@RequestMapping({"/", "/ratings"})
	public RatingList getAllMovieRatings() {
		return service.fetchAllratings();
	}
	
	@RequestMapping("/ratings/{id}")
	public Rating getRatingById(@PathVariable("id") Long movieId) {
		return service.fetchRatingById(movieId);
	}
	
	@RequestMapping("/ratings/update/{rating}")
	public Rating updateMovieRating(@PathVariable("rating") Rating myrating) {
		return service.createOrUpdateRating(myrating);
	}
	
	@RequestMapping("/ratings/delete/{id}/{rating}")
	public boolean removeMovieRating(@PathVariable("id") Long movieId, @PathVariable("rating") float stars) {
		Rating entity = new Rating();
		entity.setMovieId(movieId);
		entity.setStars(stars);
		return service.deleteRating(entity);
	}
}
