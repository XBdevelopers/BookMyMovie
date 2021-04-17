package com.xbd.ratingdataservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbd.ratingdataservice.entities.Rating;
import com.xbd.ratingdataservice.entities.RatingList;
import com.xbd.ratingdataservice.repositories.RatingDataRepository;

@Service
public class RatingDataService {

	@Autowired
	private RatingDataRepository repository;
	
	public RatingList fetchAllratings() {
		List<Rating> fetchedRatings = repository.findAll();
		
		RatingList ratingList = new RatingList();
		
		if(fetchedRatings.size()>0)
			ratingList.setRatings(fetchedRatings);
		else
			ratingList.setRatings(new ArrayList<Rating>());
		
		return ratingList;
	}
	
	public Rating fetchRatingById(Long id) {
		Optional<Rating> rating = repository.findById(id);
		
		if(rating.isPresent())
			return rating.get();
		else
			return new Rating();
	}
	
	public Rating createOrUpdateRating(Rating entity) {
		Optional<Rating> ratingStatus = repository.findById(entity.getMovieId());
		
		if(ratingStatus.isPresent()) {
			Rating updateRating = ratingStatus.get();
			updateRating.setStars(entity.getStars());
			return repository.save(updateRating);
		}else
			return repository.save(entity);
	}
	
	public boolean deleteRating(Rating entity) {
			Optional<Rating> ratingStatus = repository.findById(entity.getMovieId());
			
			if(ratingStatus.isPresent()) {
				repository.delete(entity);
				return true;
			}else
				return false;
	}
}
