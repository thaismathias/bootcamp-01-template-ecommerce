package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Compra;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.StatusCompra;
import com.mercadolivre.mercadolivre.model.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {


    @Positive
    @NotNull
    private int quantidade;

    @NotNull
    private GatewayEscolhido gatewayEscolhido;

    public CompraRequest(@Positive @NotNull int quantidade, @NotNull GatewayEscolhido gatewayEscolhido) {
        this.quantidade = quantidade;
        this.gatewayEscolhido = gatewayEscolhido;
    }

    public Compra toModel(int quantidade, GatewayEscolhido gatewayEscolhido, Produto produto, Usuario dona) {

        return new Compra(quantidade, gatewayEscolhido, StatusCompra.INICIADA, produto, dona);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public GatewayEscolhido getGatewayEscolhido() {
        return gatewayEscolhido;
    }
}
