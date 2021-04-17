package com.xbd.movieinfoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xbd.movieinfoservice.entities.Movie;

@Repository
public interface MovieInfoRepository extends JpaRepository<Movie, Long> {

}
