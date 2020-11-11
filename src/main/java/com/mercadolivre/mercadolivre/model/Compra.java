package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.GatewayEscolhido;
import com.mercadolivre.mercadolivre.api.model.RetornoPagseguroRequest;
import com.mercadolivre.mercadolivre.api.model.StatusCompra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {

    }

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

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao transacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(transacao), "Transação duplicada. Id da transação: "
                + transacao.getTransacaoId());//ToDo Retorna 500

        Set<Transacao> transacoesConcluidasSucesso = this.transacoes.stream()
                .filter(transacao::concluidaComSucesso)
                .collect(Collectors.toSet()); //Deve estar vazio, não pode ter transação com sucesso
        Assert.isTrue(transacoesConcluidasSucesso.isEmpty(), "Compra já foi concluída.");

        this.transacoes.add(transacao);
    }

    private Set<Transacao> transacoesConcluidasSucesso() {
        return true;
    }

    public boolean processadaComSucesso() {
        return true;
    }
}
