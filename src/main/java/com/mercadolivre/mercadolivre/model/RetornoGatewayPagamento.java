package com.mercadolivre.mercadolivre.model;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
