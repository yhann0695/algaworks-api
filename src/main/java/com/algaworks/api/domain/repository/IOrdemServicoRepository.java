package com.algaworks.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.api.domain.model.OrdemServico;

public interface IOrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

}
