package com.algaworks.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.api.domain.model.OrdemServico;

public interface IOrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	@Query("SELECT o FROM OrdemServico o WHERE o.id = :id")
	OrdemServico recuperarOrdem(Long id);

}
