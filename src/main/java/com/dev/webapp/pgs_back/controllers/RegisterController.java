package com.dev.webapp.pgs_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.service.IUserService;

@Controller
public class RegisterController {
    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerUserDto") RegisterUserDto registerUserDto, Model model) {
        userService.registerUser(registerUserDto);
        return "redirect:/login";
    }
}
