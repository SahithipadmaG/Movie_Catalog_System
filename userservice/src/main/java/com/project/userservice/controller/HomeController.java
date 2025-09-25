package com.project.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.project.userservice.client.MovieClient;
import com.project.userservice.model.User;
import com.project.userservice.service.UserService;
import com.project.userservice.session.UserSession;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private MovieClient movieClient;
    
    @Autowired
    private UserSession userSession;
    
    @Value("${movie.service.url}")
    private String movieServiceUrl;
    
    @GetMapping
    public String redirectToHome() {
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        // Get current logged in user
        if (userSession.isLoggedIn()) {
            User currentUser = userService.getUserById(userSession.getUserId());
            model.addAttribute("user", currentUser);
        } else {
            return "redirect:/login";
        }
        
        // Check if movie service is available
        boolean movieServiceAvailable = false;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(movieServiceUrl + "/actuator/health", String.class);
            movieServiceAvailable = result != null && result.contains("UP");
        } catch (Exception e) {
            movieServiceAvailable = false;
        }
        
        model.addAttribute("movieServiceAvailable", movieServiceAvailable);
        
        // Get movie count
        if (movieServiceAvailable) {
            try {
                Integer movieCount = movieClient.getMovieCount();
                model.addAttribute("movieCount", movieCount);
            } catch (Exception e) {
                model.addAttribute("movieCount", "Error fetching movie count");
            }
        }
        
        return "home";
    }
}