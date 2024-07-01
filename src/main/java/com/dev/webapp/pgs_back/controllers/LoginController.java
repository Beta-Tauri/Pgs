package com.dev.webapp.pgs_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.entity.Usuario;
import com.dev.webapp.pgs_back.models.service.IUserService;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Usuario usuario = userService.findByUsername(username);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}
