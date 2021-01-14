package com.algaworks.api.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_CLIENTE_ALGA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 6249984961880920933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "CO_CLIENTE")
	private Long id;
	
	@Column(name = "NO_CLIENTE")
	@NotNull
	private String nome;
	
	@Column(name = "EML_CLIENTE")
	@NotNull
	private String email;
	
	@Column(name = "TEL_CLIENTE")
	@NotNull
	private String telefone;
	
	
}
