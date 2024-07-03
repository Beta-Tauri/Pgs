package com.dev.webapp.pgs_back.models.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.dao.IReferralCodeDao;
import com.dev.webapp.pgs_back.models.dao.IUsuarioDao;
import com.dev.webapp.pgs_back.models.entity.ReferralCode;
import com.dev.webapp.pgs_back.models.entity.Usuario;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IReferralCodeDao referralCodeDao;

    @PostConstruct
    @Transactional
    public void generateInitialReferralCodes() {
        for (int i = 0; i < 10; i++) {
            String referralCodeString = generateReferralCode();
            ReferralCode referralCode = new ReferralCode();
            referralCode.setCode(referralCodeString);
            referralCode.setUsed(false); // asegurarse de que el código no está marcado como usado
            referralCodeDao.save(referralCode);
        }
    }

    private String generateReferralCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase().replace("-", "");
    }

    @Override
    @Transactional
    public void registerUser(RegisterUserDto registerUserDto) {
        ReferralCode code = referralCodeDao.findByCode(registerUserDto.getReferralCode());
        if (code != null) {
            Usuario usuario = new Usuario();
            usuario.setUsername(registerUserDto.getUsername());
            usuario.setPassword(new BCryptPasswordEncoder().encode(registerUserDto.getPassword()));
            usuario.setEmail(registerUserDto.getEmail());

            if (!code.isUsed()) {
                code.setUsed(true);
                referralCodeDao.save(code);
                usuario.setReferralCode(code.getCode());
            } else {
                // Código ya usado, permitir registro pero asignar nuevo código de referido al nuevo usuario
                usuario.setReferralCode(generateReferralCode());
            }

            usuarioDao.save(usuario);

            // Generar y guardar un nuevo código de referido para el nuevo usuario
            ReferralCode newReferralCode = new ReferralCode();
            newReferralCode.setCode(generateReferralCode());
            newReferralCode.setUsed(false);
            referralCodeDao.save(newReferralCode);
        } else {
            throw new IllegalArgumentException("Código de referido inválido");
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    public List<Usuario> findAllUsers() {
        return usuarioDao.findAllUsers();
    }

    @Override
    public void generateReferralCodes() {
        // Lógica para generar códigos de referidos adicionales
    }

    @Override
    public List<String> getInitialReferralCodes() {
        List<ReferralCode> codes = referralCodeDao.findAll();
        return codes.stream().map(ReferralCode::getCode).collect(Collectors.toList());
    }
}
