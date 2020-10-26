package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.api.model.RetornoPagseguroRequest;
import com.mercadolivre.mercadolivre.model.Compra;
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
@RequestMapping("/retorno-pagseguro/{compraId}")
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> processamentoPagseguro(@PathVariable @IdValido(domainClass = Compra.class, fieldName = "id") Long compraId,
                                                       @Valid RetornoPagseguroRequest request){
        Compra compra = manager.find(Compra.class, compraId);
        compra.adicionaTransacao(request);
        manager.merge(compra);

        return ResponseEntity.ok().build();
    }

}
