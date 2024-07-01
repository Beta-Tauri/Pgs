package com.dev.webapp.pgs_back.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dev.webapp.pgs_back.models.entity.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;

@Repository
public class UsuarioDaoImp implements IUsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> findAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    @Override
    public Usuario findByUsername(String username) {
        return entityManager.find(Usuario.class, username);
    }

    @Override
    public Usuario findByReferralCode(String referralCode) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.referralCode = :referralCode", Usuario.class)
                  .setParameter("referralCode", referralCode);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Usuario usuario) {
        entityManager.persist(usuario);
    }
}
