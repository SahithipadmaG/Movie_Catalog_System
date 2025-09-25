package com.project.movieservice.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.movieservice.client.UserClient;
import com.project.movieservice.dto.MovieDto;
import com.project.movieservice.dto.UserDto;
import com.project.movieservice.model.Movie;
import com.project.movieservice.service.MovieService;

@Controller
@RequestMapping("/cross-service")
public class CrossServiceController {
    
    private static final Logger logger = LoggerFactory.getLogger(CrossServiceController.class);
    
    @Autowired
    private UserClient userClient;
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> users = userClient.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("serviceType", "user");
        return "cross-service-list";
    }

    
    // REST API endpoint for movie Service
    @RestController
    @RequestMapping("/api")
    public static class MovieApiController {
        
        @Autowired
        private MovieService movieService;
        
        @GetMapping("/movies")
        public ResponseEntity<List<MovieDto>> getAllMovies() {
            List<Movie> movies = movieService.getAllMovies();
            List<MovieDto> movieDtos = movies.stream()
                    .map(movie -> movieService.convertToDTO(movie))
                    .toList();
            return ResponseEntity.ok(movieDtos);
        }
        
        @GetMapping("/status")
        public ResponseEntity<String> getStatus() {
            return ResponseEntity.ok("Movie Service is up and running");
        }
    }
}
/*package com.project.movieservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieservice.client.UserClient;
import com.project.movieservice.dto.MovieDto;
import com.project.movieservice.dto.UserDto;
import com.project.movieservice.model.Movie;
import com.project.movieservice.service.MovieService;

@Controller
@RequestMapping("/cross-service")
public class CrossServiceController {

    @Autowired
    private UserClient userClient;
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> users = userClient.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("serviceType", "User");
        return "cross-service-list";
    }
    
    // REST API endpoint for movie Service
    @RestController
    @RequestMapping("/api")
    public class MovieApiController {
        
        @GetMapping("/movies")
        public ResponseEntity<List<MovieDto>> getAllMovies() {
            List<Movie> movies = movieService.getAllMovies();
            List<MovieDto> MovieDtos = movies.stream()
                    .map(movie -> movieService.convertToDTO(movie))
                    .toList();
            return ResponseEntity.ok(MovieDtos);
        }
        
        @GetMapping("/status")
        public ResponseEntity<String> getStatus() {
            return ResponseEntity.ok("Movie Service is up and running");
        }
    }
} */