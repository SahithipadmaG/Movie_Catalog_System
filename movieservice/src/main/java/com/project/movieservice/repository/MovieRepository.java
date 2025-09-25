package com.project.movieservice.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.movieservice.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findAll();
   
	Optional<Movie> findById(Long id);

	Movie save(Movie movie);

	boolean existsById(Long id);

	void deleteById(Long id);
    
    
}