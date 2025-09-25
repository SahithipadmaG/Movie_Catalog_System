package com.project.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dto.UserDto;
import com.project.userservice.model.User;
import com.project.userservice.respository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        return user.get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found for update");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
    
  
    public UserDto convertToDTO(User user) {
        UserDto dto = new UserDto(user.getId(), user.getName(), user.getEmail() , user.getPhone());
        System.out.println("Converting User: " + user + " to DTO: " + dto);
        return dto;
    }
    public User findByEmailAndPassword(String email, String password) {
     
        List<User> users = userRepository.findAll();
        
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        
        return null;
    }


    
    // Method to check if email already exists
    public boolean existsByEmail(String email) {
        List<User> users = userRepository.findAll();
        
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        
        return false;
    }

}