package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.Validator.EntradaUnica;
import com.mercadolivre.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @Email
    @NotBlank
    @EntradaUnica(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;


    public UsuarioRequest(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    //+1 CDD Usuario
    public Usuario toModel() {
        return new Usuario(email,new SenhaLimpa(senha));
    }
}
