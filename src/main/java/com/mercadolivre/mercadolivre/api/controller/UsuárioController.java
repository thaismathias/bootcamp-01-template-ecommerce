package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.api.model.UsuarioRequest;
import com.mercadolivre.mercadolivre.model.Usuario;
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
@RequestMapping("/usuarios")
public class UsuárioController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    //+1 CDD UsuarioRequest
    public ResponseEntity<Void> criarUsuario(@RequestBody @Valid UsuarioRequest request) {

        //+1 CDD Usuario
        Usuario usuario = request.toModel();
        manager.persist(usuario);

        return ResponseEntity.ok().build();
    }
}
