package com.project.userservice.dto;



public class MovieDto {
    private Long id;
    private String name;
    private String genre;
    private String description;
	private Double rating;
	
    
    // Default constructor
    public MovieDto() {
    }
    
    // Constructor with fields
    public MovieDto( Long id,String name,String description, Double rating, String genre) {
        this.id = id;
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
        return "MovieDto [id=" + id + ", name=" + name + ",genre=" + genre + ", description=" + description + ", rating=" + rating + "]";
    }
}