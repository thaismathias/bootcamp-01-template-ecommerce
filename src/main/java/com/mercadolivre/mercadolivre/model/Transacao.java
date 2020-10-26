package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String transacaoId;

    @NotNull
    private LocalDateTime instante;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao () {

    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String transacaoId, @NotNull @Valid Compra compra){
        this.status = status;
        this.transacaoId = transacaoId;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    public boolean concluidaComSucesso(Transacao transacao) {
        return this.status.equals(StatusTransacao.sucesso);
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return transacaoId.equals(transacao.transacaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacaoId);
    }
}
