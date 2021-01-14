package com.algaworks.api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.algaworks.api.domain.model.enums.StatusOrdemServico;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdemServico implements Serializable{

	private static final long serialVersionUID = -4542592845349516937L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "CO_ORDEMSERVICO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CO_CLIENTE")
	@NotNull
	private Cliente cliente;
	
	@Column(name = "DS_ORDEMSERVICO")
	@NotNull
	private String descricao;
	
	@Column(name = "VL_ORDEMSERVICO")
	@NotNull
	private BigDecimal preco;
	
	@Column(name = "IC_STATUS")
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private StatusOrdemServico status;
	
	@Column(name = "DT_ABERTURA")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	@Column(name = "DT_FINALIZACAO")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
}
