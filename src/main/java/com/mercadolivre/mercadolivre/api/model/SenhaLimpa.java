package com.mercadolivre.mercadolivre.api.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    @NotBlank
    @Length(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha,"Senha obrigatória");
        Assert.isTrue(senha.length() >= 6, "Senha deve ter no mínimo 6 caracteres.");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
