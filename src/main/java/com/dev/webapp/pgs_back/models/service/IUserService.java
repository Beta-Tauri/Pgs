package com.dev.webapp.pgs_back.models.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.entity.Usuario;

public interface IUserService {
    List<Usuario> findAllUsers();
    void registerUser(RegisterUserDto registerUserDto);
    void generateReferralCodes();
    void generateInitialReferralCodes();
    Usuario findByUsername(String name);
    List<String> getInitialReferralCodes();
    boolean checkPassword(String password, String password2);
    UserDetails loadUserByUsername(String username);
}
