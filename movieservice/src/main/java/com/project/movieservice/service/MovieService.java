package com.project.movieservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movieservice.dto.MovieDto;
import com.project.movieservice.model.Movie;
import com.project.movieservice.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        return movie.get();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie) {
        if (movie.getId() == null || !movieRepository.existsById(movie.getId())) {
            throw new RuntimeException("Movie not found for update");
        }
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }
    
    public MovieDto convertToDTO(Movie movie) {
        return new MovieDto(movie.getId(), movie.getName(),movie.getGenre(), movie.getDescription(), movie.getRating());
    }
}