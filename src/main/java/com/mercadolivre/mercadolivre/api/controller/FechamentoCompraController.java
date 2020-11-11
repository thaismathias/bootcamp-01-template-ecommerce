package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.api.model.RetornoPagseguroRequest;
import com.mercadolivre.mercadolivre.api.model.RetornoPaypalRequest;
import com.mercadolivre.mercadolivre.model.Compra;
import com.mercadolivre.mercadolivre.model.RetornoGatewayPagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Validated
@RestController
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/retorno-pagseguro/{compraId}")
    @Transactional
    public ResponseEntity<Void> processamentoPagseguro(@PathVariable @IdValido(domainClass = Compra.class, fieldName = "id") Long compraId,
                                                       @Valid RetornoPagseguroRequest request) {
        return processa(compraId, request);
    }

    @PostMapping("/retorno-paypal/{compraId}")
    @Transactional
    public ResponseEntity<Void> processamentoPaypal(@PathVariable @IdValido(domainClass = Compra.class, fieldName = "id") Long compraId,
                                                    @Valid RetornoPaypalRequest request) {
        return processa(compraId, request);
    }

    private ResponseEntity<Void> processa(@PathVariable @IdValido(domainClass = Compra.class, fieldName = "id") Long compraId,
                                          RetornoGatewayPagamento request) {
        Compra compra = manager.find(Compra.class, compraId);
        compra.adicionaTransacao(request);
        manager.merge(compra);

        if(compra.processadaComSucesso()) {

        }

        return ResponseEntity.ok().build();
    }
}
