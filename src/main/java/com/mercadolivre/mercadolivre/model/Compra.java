package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.GatewayEscolhido;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private int quantidade;

    @Enumerated
    @NotNull
    private GatewayEscolhido gatewayEscolhido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario dona;

    public Compra(@NotNull @Positive int quantidade, @NotNull GatewayEscolhido gatewayEscolhido,
                  @NotNull StatusCompra status, @NotNull Produto produto, @NotNull Usuario dona) {
        this.quantidade = quantidade;
        this.gatewayEscolhido = gatewayEscolhido;
        this.status = status;
        this.produto = produto;
        this.dona = dona;
    }

    public Long getId() {
        return id;
    }
}
