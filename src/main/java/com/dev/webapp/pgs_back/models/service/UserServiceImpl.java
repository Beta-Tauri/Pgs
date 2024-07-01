package com.dev.webapp.pgs_back.models.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.webapp.pgs_back.dto.RegisterUserDto;
import com.dev.webapp.pgs_back.models.dao.IReferralCodeDao;
import com.dev.webapp.pgs_back.models.dao.IUsuarioDao;
import com.dev.webapp.pgs_back.models.entity.ReferralCode;
import com.dev.webapp.pgs_back.models.entity.Usuario;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Autowired
    private IReferralCodeDao referralCodeDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> findAllUsers() {
        return usuarioDao.findAllUsers();
    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(registerUserDto.getUsername());
        usuario.setEmail(registerUserDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        usuario.setReferralCode(assignReferralCode());
        usuario.setLevel(0);
        usuarioDao.save(usuario);
    }

    @Override
    public void generateReferralCodes() {
        for (int i = 0; i < 10; i++) {
            String referralCode = generateReferralCode();
            ReferralCode code = new ReferralCode();
            code.setCode(referralCode);
            referralCodeDao.save(code);
        }
    }

    @Override
    @Transactional
    public void generateInitialReferralCodes() {
        for (int i = 0; i < 10; i++) {
            String referralCode = generateReferralCode();
            ReferralCode code = new ReferralCode();
            code.setCode(referralCode);
            referralCodeDao.save(code);
        }
    }

    private String generateReferralCode() {
        String referralCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase().replace("-", "");
        while (referralCodeDao.findByCode(referralCode) != null) {
            referralCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase().replace("-", "");
        }
        return referralCode;
    }

    private String assignReferralCode() {
        ReferralCode code = referralCodeDao.findFirstByUsedFalse();
        if (code != null) {
            code.setUsed(true);
            referralCodeDao.save(code);
            return code.getCode();
        }
        throw new IllegalStateException("No referral codes available");
    }

    @Override
    public Usuario findByUsername(String name) {
        return usuarioDao.findByUsername(name);
    }

    @Override
    public List<String> getInitialReferralCodes() {
        return referralCodeDao.findAll().stream().map(ReferralCode::getCode).toList();
    }
}
