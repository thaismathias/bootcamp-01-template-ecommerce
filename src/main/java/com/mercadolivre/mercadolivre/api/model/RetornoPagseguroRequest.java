package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Compra;
import com.mercadolivre.mercadolivre.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest {

    @NotBlank
    private String transacaoId;

    @NotNull
    private StatusPagamento status;

    public RetornoPagseguroRequest(@NotBlank String transacaoId, @NotNull StatusPagamento status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), transacaoId, compra);
    }
}
