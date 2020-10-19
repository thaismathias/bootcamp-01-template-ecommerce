package com.mercadolivre.mercadolivre.model;

import com.mercadolivre.mercadolivre.api.model.CaracteristicaProduto;
import com.mercadolivre.mercadolivre.api.model.ListaCaracteristicasRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
}