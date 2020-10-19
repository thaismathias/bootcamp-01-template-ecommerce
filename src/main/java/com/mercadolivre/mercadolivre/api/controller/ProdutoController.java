package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.api.model.ProdutoRequest;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;
import com.mercadolivre.mercadolivre.repository.UsuarioRepository;
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
@RequestMapping("/produtos")
//Usuário logado
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criaProduto(@RequestBody @Valid ProdutoRequest request) {
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        //+1 CDD Produto
        Produto produto = request.toModel(manager,dona);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }


}
