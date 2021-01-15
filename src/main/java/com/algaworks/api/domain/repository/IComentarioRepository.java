package com.algaworks.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.api.domain.model.Comentario;

public interface IComentarioRepository extends JpaRepository<Comentario, Long>{

}
