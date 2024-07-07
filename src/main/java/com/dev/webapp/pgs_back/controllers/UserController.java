package com.dev.webapp.pgs_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.service.IUserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String showLoginAndRegistrationForm(Model model) {
        model.addAttribute("loginUserDto", new RegisterUserDto());
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginUserDto") @Valid RegisterUserDto loginUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserDto.getUsername());
            if (userDetails != null) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null && auth.isAuthenticated()) {
                    return "redirect:/dashboard";
                }
            }
        } catch (UsernameNotFoundException e) {
            bindingResult.rejectValue("username", "error.loginUserDto", "Invalid username or password");
            return "login";
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerUserDto") @Valid RegisterUserDto registerUserDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        try {
            userService.registerUser(registerUserDto);

            // Autenticación manual después del registro
            UserDetails userDetails = userDetailsService.loadUserByUsername(registerUserDto.getUsername());
            if (userDetails != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "redirect:/dashboard";
            }
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("referralCode", "error.referralCode", e.getMessage());
            return "login";
        } catch (UsernameNotFoundException e) {
            bindingResult.rejectValue("username", "error.registerUserDto", "User not found after registration");
            return "login";
        }
        return "login";
    }
}
