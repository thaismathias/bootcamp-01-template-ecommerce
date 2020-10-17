package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.api.model.CategoriaRequest;
import com.mercadolivre.mercadolivre.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> ciarCategoria(@RequestBody @Valid CategoriaRequest request) {

        //+1 CDD Categoria
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);


        return ResponseEntity.ok().build();
    }
}
