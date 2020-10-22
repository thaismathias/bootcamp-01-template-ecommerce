package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.api.model.DetalhesProdutoResponse;
import com.mercadolivre.mercadolivre.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Validated
@RestController
@RequestMapping("/produtos/{produtoId}")
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    @Transactional
    public DetalhesProdutoResponse detalhesProduto(@PathVariable @IdValido(domainClass = Produto.class, fieldName = "id") Long produtoId ) {
        Produto produto = manager.find(Produto.class, produtoId);

        return new DetalhesProdutoResponse(produto);
    }
}
