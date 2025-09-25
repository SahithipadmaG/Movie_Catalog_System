package com.project.userservice.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.userservice.client.MovieClient;
import com.project.userservice.dto.MovieDto;

@Component
public class MovieClient {
	
	 private static final Logger logger = LoggerFactory.getLogger(MovieClient.class);
	    private final RestTemplate restTemplate;
	    private final String movieServiceUrl;

   /* @Autowired
    private RestTemplate restTemplate; 
    
    @Value("${movie.service.url}")
    private String movieServiceUrl;
    */
	    
	    public MovieClient(@Value("${movie-service.url:http://localhost:8082}") String movieServiceUrl) {
	        this.restTemplate = new RestTemplate();
	        this.movieServiceUrl = movieServiceUrl;
	        logger.info("MovieClient initialized with URL: {}", movieServiceUrl);
	    }
	    
	    public String getMovieServiceStatus() {
	        try {
	            logger.info("Checking movie service status at: {}/api/status", movieServiceUrl);
	            return restTemplate.getForObject(movieServiceUrl + "/api/status", String.class);
	        } catch (Exception e) {
	            logger.error("Error checking user service status: {}", e.getMessage());
	            return "User Service is unavailable: " + e.getMessage();
	        }
	    }
	    
    public List<MovieDto> getAllMovies() {
        try {
        	 
             
            MovieDto[] response = restTemplate.getForObject(movieServiceUrl + "/api/movies", MovieDto[].class);
            return response != null ? Arrays.asList(response) : Collections.emptyList();
           
        } catch (RestClientException e) {
            // Log error
            System.err.println("Error fetching movies: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    public MovieDto getMovieById(Long id) {
        try {
            return restTemplate.getForObject(movieServiceUrl + "/api/movies/" + id, MovieDto.class);
        } catch (RestClientException e) {
            // Log error
            System.err.println("Error fetching movie with ID " + id + ": " + e.getMessage());
            return null;
        }
    }

	public Integer getMovieCount() {
		// TODO Auto-generated method stub
		return null;
	}
 

}