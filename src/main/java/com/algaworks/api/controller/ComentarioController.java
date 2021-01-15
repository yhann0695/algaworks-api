package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.model.dto.ComentarioDTO;
import com.algaworks.api.domain.service.OrdemServicoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "ordem-servico/{ordemServicoId}/comentarios")
@ApiOperation(value = "Controller criado para contole negocial dos comentarios")
public class ComentarioController {
	
	@Autowired
	private OrdemServicoService ordemServico;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Endpoint para adicionar um comentario a ordem de serviço")
	public ResponseEntity<ComentarioDTO> inserir(@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioDTO comentarioDTO) {
		
		return ResponseEntity.ok(ordemServico.adicionarComentario(ordemServicoId, comentarioDTO.getDescricao()));
	}
	
	@GetMapping
	@ApiOperation(value = "Endpoint para consultar todos os comentarios")
	public ResponseEntity <List<ComentarioDTO>> consultarTodos(@PathVariable Long ordemServicoId) {
		return ResponseEntity.ok(ordemServico.consultarComentarios());
	}
}
