package com.dev.webapp.pgs_back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    @NotEmpty(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    @NotEmpty(message = "El correo es obligatorio")
    @Email(message = "Correo no válido")
    private String email;

    @NotEmpty(message = "El código de referido es obligatorio")
    private String referralCode;

    private Boolean terms;
}
