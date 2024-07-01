package com.dev.webapp.pgs_back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.entity.Usuario;
import com.dev.webapp.pgs_back.models.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public List<Usuario> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public void registerUser(@RequestBody RegisterUserDto registerUserDto) {
        userService.registerUser(registerUserDto);
    }

    @GetMapping("/generate-referral-codes")
    public void generateReferralCodes() {
        userService.generateReferralCodes();
    }
}
