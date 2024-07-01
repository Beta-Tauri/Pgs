package com.dev.webapp.pgs_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.webapp.pgs_back.models.entity.Usuario;
import com.dev.webapp.pgs_back.models.service.IUserService;

@Controller
public class DashboardController {
    @Autowired
    private IUserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Usuario usuario = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuario", usuario);
        return "dashboard";
    }
}
