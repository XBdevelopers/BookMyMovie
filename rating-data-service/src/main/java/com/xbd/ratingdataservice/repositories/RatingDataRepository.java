package com.xbd.ratingdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xbd.ratingdataservice.entities.Rating;

@Repository
public interface RatingDataRepository extends JpaRepository<Rating, Long> {

}
