package com.mercadolivre.mercadolivre.repository;

import com.mercadolivre.mercadolivre.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByProdutoId (Long produtoId);
}
