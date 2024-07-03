package com.dev.webapp.pgs_back.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Agregar paquetes a la vista
        model.addAttribute("packages", getPackages());
        return "dashboard";
    }

    // Método para obtener paquetes (puedes modificarlo para obtener datos de la base de datos)
    private List<Package> getPackages() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("Cyber", "Security", "image1.png"));
        packages.add(new Package("Web", "Development", "image2.png"));
        // Agrega más paquetes según sea necesario
        return packages;
    }

    private class Package {
        private String name;
        private String category;
        private String image;

        public Package(String name, String category, String image) {
            this.name = name;
            this.category = category;
            this.image = image;
        }

        // Getters y Setters
    }
}
