package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListaCaracteristicasRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String valor;

    public ListaCaracteristicasRequest(@NotBlank String nome, @NotBlank String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(nome, valor);

    }
}
