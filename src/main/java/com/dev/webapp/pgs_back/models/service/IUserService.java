package com.dev.webapp.pgs_back.models.service;

import java.util.List;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.entity.Usuario;

public interface IUserService {
    List<Usuario> findAllUsers();
    void registerUser(RegisterUserDto registerUserDto);
    void generateReferralCodes();
    void generateInitialReferralCodes();
    Usuario findByUsername(String name);
    List<String> getInitialReferralCodes();
}
