package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.api.model.PerguntaRequest;
import com.mercadolivre.mercadolivre.model.Pergunta;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;
import com.mercadolivre.mercadolivre.repository.PerguntaRepository;
import com.mercadolivre.mercadolivre.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/produtos/{produtoId}/perguntas")
public class PerguntaController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public PerguntaRepository perguntaRepository;

    @Autowired
    public EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity <List<Pergunta>> criarPergunta(@RequestBody @Valid PerguntaRequest request,
                                                         @PathVariable @IdValido(domainClass = Produto.class, fieldName = "id") Long produtoId) {
        //Usuário logado
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        Produto produto = manager.find(Produto.class, produtoId);
        Pergunta pergunta = request.toModel(manager,produto,dona);
        manager.persist(pergunta);

        //email Service para enviar email com um system out println("pergunta + link detalhes do produto")
        //System.out.println(produto.Email());

        return ResponseEntity.ok(perguntaRepository.findByProdutoId(produtoId));
    }
}
