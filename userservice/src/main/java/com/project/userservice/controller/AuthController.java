package com.project.userservice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.userservice.model.User;
import com.project.userservice.service.UserService;
import com.project.userservice.session.UserSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserSession userSession;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userSession.clearSession();
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, 
                              @RequestParam String password,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        
        User user = userService.findByEmailAndPassword(email, password);
        
        if (user != null) {
            // Set user in session
            userSession.setUserId(user.getId());
            userSession.setUserName(user.getName());
            userSession.setUserEmail(user.getEmail());
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }
    }
    
    @PostMapping("/register")
    public String processRegistration(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String phone,
                                     @RequestParam String password,
                                     @RequestParam String confirmPassword,
                                     RedirectAttributes redirectAttributes) {
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/register";
        }
        
        // Check if email already exists
        if (userService.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("error", "Email already registered");
            return "redirect:/register";
        }
        
        // Create new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        
        userService.saveUser(newUser);
        
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }
}