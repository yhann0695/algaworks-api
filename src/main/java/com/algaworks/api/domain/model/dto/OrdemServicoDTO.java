package com.algaworks.api.domain.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

import javax.validation.constraints.NotNull;

import com.algaworks.api.domain.model.enums.StatusOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoDTO implements Serializable{

	private static final long serialVersionUID = 4561070074554918109L;

	private Long id;
	
	@NotNull
	private ClienteDTO cliente;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private StatusOrdemServico status;

	private OffsetDateTime dataAbertura;
	
	private OffsetDateTime dataFinalizacao;
	
}
