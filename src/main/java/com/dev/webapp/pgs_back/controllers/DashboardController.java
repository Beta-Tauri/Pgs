package com.dev.webapp.pgs_back.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.webapp.pgs_back.models.entity.Usuario;
import com.dev.webapp.pgs_back.models.service.IUserService;

@Controller
public class DashboardController {

    @Autowired
    private IUserService userService;

  /*
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        model.addAttribute("packages", getPackages());
        return "dashboard";
    }

    private List<Package> getPackages() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("Cyber", "Security", "image1.png"));
        packages.add(new Package("Web", "Development", "image2.png"));
        // Agrega más paquetes según sea necesario
        return packages;
    }

    private static class Package {
        private String name;
        private String category;
        private String image;

        public Package(String name, String category, String image) {
            this.name = name;
            this.category = category;
            this.image = image;
        }

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }
    */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Aquí puedes agregar datos adicionales que se mostrarán en el dashboard
        return "dashboard";
    }
}
