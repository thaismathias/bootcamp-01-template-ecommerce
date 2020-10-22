package com.mercadolivre.mercadolivre.api.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class CaracteristicaProduto {

    @NotBlank
    private String nome;

    @NotBlank
    private String valor;

    @Deprecated
    public CaracteristicaProduto() {

    }

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
}
