package com.dev.webapp.pgs_back.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre de usuario es obligatorio")
    @Column(length = 30, unique = true)
    private String username;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Column(length = 60)
    private String password;

    @NotEmpty(message = "El correo es obligatorio")
    @Email(message = "Correo no válido")
    @Column(unique = true)
    private String email;

    @Column(name = "referral_code", unique = true)
    private String referralCode;

    @Column(name = "level")
    private int level;
}
