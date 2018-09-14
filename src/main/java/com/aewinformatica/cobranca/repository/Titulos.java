package com.aewinformatica.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aewinformatica.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {

	List<Titulo> findByDescricaoContaining(String descricao);

}
