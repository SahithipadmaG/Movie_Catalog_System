package com.project.movieservice.client;

import com.project.movieservice.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserClient {
    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);
    private final RestTemplate restTemplate;
    private final String userServiceUrl;
    
    public UserClient(@Value("${user.service.url:http://localhost:8081}") String userServiceUrl) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        this.userServiceUrl = userServiceUrl;
        logger.info("UserClient initialized with URL: {}", userServiceUrl);
    }
    
    public String getUserServiceStatus() {
        try {
            logger.info("Checking user service status at: {}/api/status", userServiceUrl);
            return restTemplate.getForObject(userServiceUrl + "/api/status", String.class);
        } catch (Exception e) {
            logger.error("Error checking user service status: {}", e.getMessage());
            return "User Service is unavailable: " + e.getMessage();
        }
    }
    
    public List<UserDto> getAllUsers() {
        try {
            logger.info("Fetching all users from: {}/api/users", userServiceUrl);
            UserDto[] usersArray = restTemplate.getForObject(userServiceUrl + "/api/users", UserDto[].class);
            if (usersArray != null) {
                logger.info("Retrieved {} users from user service", usersArray.length);
                return Arrays.asList(usersArray);
            } else {
                logger.warn("User service returned null response");
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            logger.error("Error fetching users from user service: {}", e.getMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Unexpected error fetching users: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    public UserDto getUserById(Long id) {
        try {
            // Fixed URL to use users instead of movies
            return restTemplate.getForObject(userServiceUrl + "/api/users/" + id, UserDto.class);
        } catch (RestClientException e) {
            logger.error("Error fetching user with ID {}: {}", id, e.getMessage());
            return null;
        }
    }
}