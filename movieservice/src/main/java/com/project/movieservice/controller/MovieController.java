package com.project.movieservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.movieservice.model.Movie;
import com.project.movieservice.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    
    // Display all movies
    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies";
    }
    
    // Show form for creating a new movie
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("isEdit", false);
        return "movie-form";
    }
    
    // Show form for editing a movie
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isEdit", true);
        return "movie-form";
    }
    
    // Handle form submission for both create and update
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        try {
            Movie savedMovie = movieService.saveMovie(movie);
            redirectAttributes.addFlashAttribute("message", "Movie saved successfully!");
            return "redirect:/movies";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving movie: " + e.getMessage());
            return "redirect:/movies/create";
        }
    }
    
    // Delete a movie
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            movieService.deleteMovie(id);
            redirectAttributes.addFlashAttribute("message", "Movie deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting movie: " + e.getMessage());
        }
        return "redirect:/movies";
    }
}


