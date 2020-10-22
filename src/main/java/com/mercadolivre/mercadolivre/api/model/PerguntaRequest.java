package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Pergunta;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(EntityManager manager, Produto produto, Usuario dona) {
        return new Pergunta(titulo, dona);
    }
}

