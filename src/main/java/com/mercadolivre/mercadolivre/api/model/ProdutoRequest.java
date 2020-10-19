package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Categoria;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private int quantidade;

    @Size(min = 3)
    @Valid
    private List<ListaCaracteristicasRequest> caracteristicas = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    //Id é valido?
    private Long categoriaId;

    public ProdutoRequest(@NotBlank String nome, @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
                          @NotNull @Min(value = 0) int quantidade,
                          @Size(min = 3) @Valid List<ListaCaracteristicasRequest> caracteristicas,
                          @NotBlank @Size(max = 1000) String descricao, @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(EntityManager manager, Usuario dona) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);

        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria, dona);
    }

    public List<ListaCaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
    }
}