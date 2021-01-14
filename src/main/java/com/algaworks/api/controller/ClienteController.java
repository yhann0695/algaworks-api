package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/clientes")
@ApiOperation(value = "Controller para controle negocial do cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Endpoit para consultar todos os clientes da base de dados")
	public ResponseEntity<List<Cliente>> consutlarTodos() {
		return ResponseEntity.ok(clienteService.consutlarTodos());
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Endpoint para consultar um único cliente pelo ID")
	public ResponseEntity<Cliente> consutlarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.consultarPorId(id));
	}
	
	@ApiOperation(value = "Endpoint para fazer a inserção de um cliente na base de dados")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> inserir(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.inserir(cliente));
	}
	
	@ApiOperation(value = "Endpoint para fazer a atualização do dados de um cliente")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.atualizar(cliente));
	}
	
	@ApiOperation(value = "Endpoint para excluir da base de dados")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> excluir(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.excluir(id));
	}
	
}
