package com.dev.webapp.pgs_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.webapp.pgs_back.models.service.IUserService;

@SpringBootApplication
public class PgsBackApplication implements CommandLineRunner {

    @Autowired
    private IUserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PgsBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.generateInitialReferralCodes();
    }
}
