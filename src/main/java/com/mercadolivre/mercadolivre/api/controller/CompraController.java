package com.mercadolivre.mercadolivre.api.controller;

import com.mercadolivre.mercadolivre.Validator.IdValido;
import com.mercadolivre.mercadolivre.api.model.CompraRequest;
import com.mercadolivre.mercadolivre.api.model.GatewayEscolhido;
import com.mercadolivre.mercadolivre.model.Compra;
import com.mercadolivre.mercadolivre.model.Produto;
import com.mercadolivre.mercadolivre.model.Usuario;
import com.mercadolivre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/comprar/produto")
public class CompraController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{produtoId}")
    @Transactional
    public ResponseEntity<?> criarCompra(@PathVariable @IdValido(domainClass = Produto.class, fieldName = "id") Long produtoId,
                                            @RequestBody @Valid CompraRequest request, UriComponentsBuilder uriComponentsBuilder) {
        //Usuário logado
        Usuario dona = usuarioRepository.findByEmail("tha@zup.com").get();

        Produto produto = manager.find(Produto.class, produtoId);
        if (!produto.verificaEstoque(request.getQuantidade())) {
            return ResponseEntity.badRequest().body("Não há estoque suficiente.");
        }
        produto.atualizarEstoque(request.getQuantidade());
        Compra compra = request.toModel(request.getQuantidade(), request.getGatewayEscolhido(), produto, dona);
        manager.persist(compra);

        if ((request.getGatewayEscolhido()) == GatewayEscolhido.pagseguro) {
            String urlRetornoAppPosPagamento = uriComponentsBuilder.path("retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            String urlPagseguro = "pagseguro.com/returnId="+ compra.getId() + "&redirectUrl=" + urlRetornoAppPosPagamento;
            return ResponseEntity.ok(URI.create(urlPagseguro));
        } else {
            String urlPaypal = "paypal.com/" + compra.getId() + "/redirectUrl={urlRetornoAppPosPagamento}";
            return ResponseEntity.ok(URI.create(urlPaypal));
        }
    }
}
