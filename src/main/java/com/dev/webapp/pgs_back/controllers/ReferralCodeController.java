package com.dev.webapp.pgs_back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.webapp.pgs_back.models.service.IUserService;

@Controller
public class ReferralCodeController {
    @Autowired
    private IUserService userService;

    @GetMapping("/referral-codes")
    public String referralCodes(Model model) {
        List<String> initialReferralCodes = userService.getInitialReferralCodes();
        model.addAttribute("initialReferralCodes", initialReferralCodes);
        return "referral-codes";
    }
}
