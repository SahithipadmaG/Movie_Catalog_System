package com.project.movieservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre;
    private String name;
    private String description;
    private Double rating;
    
    // Default constructor (required by JPA)
    public Movie() {
    }
    
    // Constructor with fields
    public Movie(String name, String description, Double rating,String genre) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.genre=genre;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + ",description=" + description + ", rating=" + rating + "]";
    }

	
}