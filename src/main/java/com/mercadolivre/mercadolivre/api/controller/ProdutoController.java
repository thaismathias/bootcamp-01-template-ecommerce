package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.Validator.ValidarCaracteristicasIguais;
import com.mercadolivre.mercadolivre.api.model.ImagemRequest;
import com.mercadolivre.mercadolivre.api.model.ProdutoRequest;
import com.mercadolivre.mercadolivre.api.model.Uploader;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;
import com.mercadolivre.mercadolivre.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public ValidarCaracteristicasIguais validarCaracteristicasIguais;

    @Autowired
    public Uploader uploader;


    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder binder) { binder.addValidators(validarCaracteristicasIguais); }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criaProduto(@RequestBody @Valid ProdutoRequest request) {
        //Usuário logado
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        //+1 CDD Produto
        Produto produto = request.toModel(manager,dona);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{produtoId}/imagens")
    @Transactional
    public ResponseEntity<Void> addImagem(@PathVariable @IdValido(domainClass = Produto.class, fieldName = "id") Long produtoId,
                          @Valid ImagemRequest request) {
        //Usuário logado
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        Produto produto = manager.find(Produto.class, produtoId);
        //Cadastro de foto é feita apenas pelo dono do produto
        if (!produto.pertenceAoUsuario(dona)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        //pegar lista de links
        Set<String> links = uploader.envia(request.getImagens());
        //vincular ao produto
        produto.associaImagens(links);
        //Atualizar inf do produto
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }
}
