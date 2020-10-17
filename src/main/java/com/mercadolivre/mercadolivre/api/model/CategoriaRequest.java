package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.Validator.EntradaUnica;
import com.mercadolivre.mercadolivre.model.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    //+1 CDD EntradaUnica
    @NotBlank
    @EntradaUnica(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    private Long categoriaMaeId;

    //+1 CDD Categoria
    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (categoriaMaeId != null){
            Categoria categoriaMae = manager.find(Categoria.class, categoriaMaeId);
            Assert.notNull(categoriaMae, "Id da categoria mãe deve existir.");
            categoria.setMae(categoriaMae);
        }
        return new Categoria(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }
}
