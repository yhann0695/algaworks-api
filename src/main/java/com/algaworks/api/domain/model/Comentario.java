package com.algaworks.api.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.algaworks.api.domain.model.dto.OrdemServicoDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_COMENTARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1401053762422726017L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "CO_COMENTARIO")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CO_ORDEMSERVICO")
	private OrdemServico ordemServico;
	
	@Column(name = "DS_COMENTARIO")
	private String descricao;
	
	@Column(name = "DT_COMENTARIO")
	private OffsetDateTime dataEnvio;
}
