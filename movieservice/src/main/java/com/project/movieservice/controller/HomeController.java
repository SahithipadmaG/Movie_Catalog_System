package com.project.movieservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.movieservice.client.UserClient;
import com.project.movieservice.model.Movie;

@Controller
public class HomeController {
    @Autowired
    private UserClient userClient;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userServiceStatus", userClient.getUserServiceStatus());
       
        model.addAttribute("movie", new Movie());
        return "home";
    }
}
