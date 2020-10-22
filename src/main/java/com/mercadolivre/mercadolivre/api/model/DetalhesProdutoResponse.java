package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.Set;

public class DetalhesProdutoResponse {

    private String nome;

    private BigDecimal valor;

    private int quantidade;

    private Set<CaracteristicaProduto> caracteristicas;

    private String descricao;

    private Categoria categoria;

    private Set<ImagemProduto> imagens;

    private Set<Opiniao> opiniao;

    private int opiniaoTotal;

    private BigDecimal mediaNotas;

    private Set<Pergunta> pergunta;

    public DetalhesProdutoResponse(Produto produto) {

        nome = produto.getNome();
        valor = produto.getValor();
        quantidade = produto.getQuantidade();
        caracteristicas = produto.getCaracteristicas();
        descricao = produto.getDescricao();
        categoria = produto.getCategoria();
        imagens = produto.getImagens();
        opiniao = produto.getOpiniao();
        opiniaoTotal = produto.getTotalDeNotas();
        mediaNotas = produto.getMediaDeNotas();
        pergunta = produto.getPergunta();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<Opiniao> getOpiniao() {
        return opiniao;
    }

    public int getOpiniaoTotal() {
        return opiniaoTotal;
    }

    public BigDecimal getMediaNotas() {
        return mediaNotas;
    }

    public Set<Pergunta> getPergunta() {
        return pergunta;
    }
}
