package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {

    }

    // Senha em plain text
    //+1 CDD SenhaLimpa
    public Usuario(@Email @NotBlank String email, @NotBlank @Length(min = 6) SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "E-mail obrigatório.");
        Assert.notNull(senhaLimpa, "Objeto senhaLimpa é obrigatório.");
        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    public String getEmail() {
        return email;
    }
}
