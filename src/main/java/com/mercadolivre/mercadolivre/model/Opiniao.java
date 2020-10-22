package com.mercadolivre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dona;

    @Deprecated
    public Opiniao() {

    }

    public Opiniao(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao,
                   @NotNull @Valid Produto produto, @NotNull @Valid Usuario dona) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.dona = dona;
    }
}
