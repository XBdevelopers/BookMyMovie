package com.xbd.moviecatalogservice.models;

public class Catalog {

	private Movie movie;
	private Rating rating;
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
