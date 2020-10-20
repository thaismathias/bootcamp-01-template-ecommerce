package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.api.model.OpiniaoRequest;
import com.mercadolivre.mercadolivre.model.Opiniao;
import com.mercadolivre.mercadolivre.model.Usuario;
import com.mercadolivre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/{produtoId}/opinioes")
public class OpiniaoController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criarOpiniao(@RequestBody @Valid OpiniaoRequest request, @PathVariable Long produtoId) {
        //Usuário logado
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        Opiniao opiniao = request.toModel(manager, dona);
        manager.persist(opiniao);
        return ResponseEntity.ok().build();
    }
}
