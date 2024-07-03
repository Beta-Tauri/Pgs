package com.dev.webapp.pgs_back.models.dao;

import java.util.List;

import com.dev.webapp.pgs_back.models.entity.Usuario;

public interface IUsuarioDao {
    List<Usuario> findAllUsers();
    Usuario findByUsername(String username);
    Usuario findByReferralCode(String referralCode);
    void save(Usuario usuario);
    int count();
}
