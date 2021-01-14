package com.algaworks.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.domain.exceptions.NegocioException;
import com.algaworks.api.domain.exceptions.NotFoundException;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Transactional
	public List<Cliente> consutlarTodos() {
		return clienteRepository.findAll();
	}

	@Transactional
	public Cliente consultarPorId(Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = optional.get();
		return cliente;
	}

	@Transactional
	public Cliente inserir(Cliente cliente) {
		this.validarEmail(cliente);
		return clienteRepository.save(cliente);
	}


	@Transactional
	public Cliente atualizar(Cliente cliente) {
		this.verificarExistenciaCliente(cliente.getId());
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Long excluir(Long id) {
		this.verificarExistenciaCliente(id);
		clienteRepository.deleteById(id);
		return id;
	}

	// VALIDAÇÕES
	
	private void verificarExistenciaCliente(Long id) {
		boolean isExists = clienteRepository.existsById(id);
		if(!isExists) {
			throw new NotFoundException("Cliente não encontrado");
		}
	}
	
	private void validarEmail(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		}
	}
	
	
}
