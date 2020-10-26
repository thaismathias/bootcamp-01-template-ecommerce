package com.mercadolivre.mercadolivre.api.model;

public enum StatusPagamento {

    ERRO(0),
    SUCESSO(1);

    private Integer codigo;

    StatusPagamento(Integer codigo) {
        this.codigo = codigo;
    }

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
