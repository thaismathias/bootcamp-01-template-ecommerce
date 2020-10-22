package com.mercadolivre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Embeddable
public class Opiniao {

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
    private Usuario dona;

    @Deprecated
    public Opiniao() {

    }

    public Opiniao(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao, @NotNull @Valid Usuario dona) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dona = dona;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}
