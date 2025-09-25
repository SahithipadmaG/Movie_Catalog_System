package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.userservice.client.MovieClient;
import com.project.userservice.dto.MovieDto;
import com.project.userservice.service.UserService;

@Controller
@RequestMapping("/cross-service")
public class CrossServiceController {

    @Autowired
    private MovieClient movieClient;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/movies")
    public String getMovies(Model model) {
        List<MovieDto> movies = movieClient.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("serviceType", "movie");
        return "cross-service-list";
    }
    
}


