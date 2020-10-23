package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.CaracteristicaProduto;
import com.mercadolivre.mercadolivre.api.model.ListaCaracteristicasRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private int quantidade;

    @Size(min = 3)
    @Valid
    @ElementCollection
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dona;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @ElementCollection
    private Set<ImagemProduto> imagens = new HashSet<>();

    @ElementCollection
    private Set<Opiniao> opiniao;

    @ElementCollection
    private Set<Pergunta> pergunta;

    @Deprecated
    public Produto(){

    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
                   @NotNull @Min(value = 0) int quantidade,
                   @Size(min = 3) @Valid Collection<ListaCaracteristicasRequest> caracteristicas,
                   @NotBlank @Size(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
                   @NotNull Usuario dona) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.dona = dona;
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(link))
                .collect(Collectors.toSet());
        //Associar com as imagens do produto
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDona) {
        return this.dona.equals(possivelDona);
    }

    public void associaOpiniao(Opiniao opiniao) {
        this.opiniao.add(opiniao);
    }

    public void associaPergunta(Pergunta pergunta) {
        this.pergunta.add(pergunta);
    }

    public int getTotalDeNotas() {
        return opiniao.size();
    }

    public BigDecimal getMediaDeNotas(){
        BigDecimal somaNotas = new BigDecimal(opiniao.stream().mapToDouble(Opiniao::getNota).sum());
        BigDecimal numeroNotas = new BigDecimal(getTotalDeNotas());
        return somaNotas.divide(numeroNotas,1,RoundingMode.UP);
    }

    public boolean verificaEstoque(int quantidade) {
        return this.quantidade >= quantidade;
    }

    public void atualizarEstoque(@Positive int quantidade) {
        this.quantidade -= quantidade;
    }

    public Long getId() {
        return id;
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

    public Set<Pergunta> getPergunta() {
        return pergunta;
    }
}
