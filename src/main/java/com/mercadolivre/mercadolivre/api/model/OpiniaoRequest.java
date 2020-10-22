package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Opiniao;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)

    private String descricao;

    public OpiniaoRequest(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(EntityManager manager, Produto produto, Usuario dona) {
        return new Opiniao(nota, titulo, descricao, dona);
    }
}
