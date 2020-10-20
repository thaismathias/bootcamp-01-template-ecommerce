package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.model.Opiniao;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.Valid;
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

    @NotNull
    @IdValido(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    public OpiniaoRequest(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao,
                          @NotNull Long produtoId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    public Opiniao toModel(EntityManager manager, Usuario dona) {
        Produto produto = manager.find(Produto.class, produtoId);

        return new Opiniao(nota, titulo, descricao, produto, dona);
    }
}
