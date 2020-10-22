package com.mercadolivre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
public class Pergunta {

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dona;

    @Deprecated
    public Pergunta() {

    }

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario dona) {
        this.titulo = titulo;
        this.dona = dona;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getDona() {
        return dona;
    }

}
