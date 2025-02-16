package com.example.loginapp.controllers;

import com.example.loginapp.models.User;
import com.example.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("message", "Username already exists!");
            return "register";
        }

        User newUser = new User(username, password);
        userRepository.save(newUser);

        model.addAttribute("message", "Registration successful! You can now log in.");
        return "register";
    }
}
