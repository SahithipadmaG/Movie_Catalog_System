package com.project.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.userservice.model.User;
import com.project.userservice.service.UserService;
import com.project.userservice.session.UserSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserSession userSession;

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }
    
    @GetMapping
    public String getAllUsers(Model model) {
        // Only show the current logged in user
        if (userSession.isLoggedIn()) {
            User currentUser = userService.getUserById(userSession.getUserId());
            model.addAttribute("users", java.util.Collections.singletonList(currentUser));
            return "users";
        } else {
            return "redirect:/login";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Check if user is editing their own profile
        if (!id.equals(userSession.getUserId())) {
            return "redirect:/users";
        }
        
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true); 
        return "user-form";
    }
    
  /*  @PostMapping
    public String saveUser(@ModelAttribute User user) {
        // If editing, make sure it's the current user
        if (user.getId() != null && !user.getId().equals(userSession.getUserId())) {
            return "redirect:/users";
        }
        
        userService.saveUser(user);
        
        // Update session with new user info if it's the current user
        if (user.getId() != null && user.getId().equals(userSession.getUserId())) {
            userSession.setUserName(user.getName());
            userSession.setUserEmail(user.getEmail());
        }
        
        return "redirect:/users";
    } */
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Only allow deletion of own account
        if (id.equals(userSession.getUserId())) {
            userService.deleteUser(id);
            // Logout after deleting own account
            return "redirect:/logout";
        }
        return "redirect:/users";
    }
    
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute User user) {
        if (user.getId() == null || !user.getId().equals(userSession.getUserId())) {
            return "redirect:/users";
        }
        userService.updateUser(user);
        userSession.setUserName(user.getName());
        userSession.setUserEmail(user.getEmail());
        userSession.setUserPassword(user.getPassword());
        userSession.setUserPhone(user.getPhone());

        return "redirect:/users";
}
    
}