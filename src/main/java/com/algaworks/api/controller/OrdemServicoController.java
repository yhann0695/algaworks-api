package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.model.OrdemServico;
import com.algaworks.api.domain.model.dto.OrdemServicoDTO;
import com.algaworks.api.domain.service.OrdemServicoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/ordem-servico")
@ApiOperation(value = "Controller para controle negocial da ordem de servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServico;
	
	@ApiOperation(value = "Endpoint para inserir uma ordem de servico no banco de dados")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdemServicoDTO> inserir(@Valid @RequestBody OrdemServicoDTO ordemDTO) {
		return ResponseEntity.ok(ordemServico.inserir(ordemDTO));
	}
	
	@ApiOperation(value = "Endpoint para consultar todas as ordens")
	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> consultarTodos() {
		return ResponseEntity.ok(ordemServico.consultarTodos());
	}
	
	@ApiOperation(value = "Endpoint para consultar  uma ordem por ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ordemServico.consultarPorId(id));
	}
	
	@PutMapping(value = "/{id}/finalizacao")
	@ApiOperation(value = "Endpoint para finalizar uma ordem de serviço")
	public ResponseEntity<Void> finalizarOrdem(@PathVariable Long id) {
		ordemServico.finalizar(id);
		return ResponseEntity.ok().build();
	}
}
