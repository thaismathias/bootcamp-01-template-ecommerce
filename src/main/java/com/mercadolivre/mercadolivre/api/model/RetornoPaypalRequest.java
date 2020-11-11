package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Compra;
import com.mercadolivre.mercadolivre.model.RetornoGatewayPagamento;
import com.mercadolivre.mercadolivre.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;

    @NotBlank
    private String transacaoId;

    public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotBlank String transacaoId) {
        this.status = status;
        this.transacaoId = transacaoId;
    }

    public Transacao toTransacao(Compra compra) {
        @NotNull
        StatusTransacao statusCalculado = this.status == 0? StatusTransacao.erro
                : StatusTransacao.sucesso;
        return new Transacao(statusCalculado , transacaoId, compra);
    }
}
