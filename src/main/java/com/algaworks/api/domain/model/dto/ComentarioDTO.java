package com.algaworks.api.domain.model.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO implements Serializable{

	private static final long serialVersionUID = 3859777491247417183L;

	private Long id;
	
	private OrdemServicoDTO ordemServico;
	
	private String descricao;
	
	private OffsetDateTime dataEnvio;
}
