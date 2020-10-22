package com.mercadolivre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dona;

    @Deprecated
    public Pergunta() {

    }

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Produto produto,
                    @NotNull @Valid Usuario dona) {
        this.titulo = titulo;
        this.produto = produto;
        this.dona = dona;
    }

    public String getTitulo() {
        return titulo;
    }
}
