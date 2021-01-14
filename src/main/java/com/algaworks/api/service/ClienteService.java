package com.algaworks.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.repository.IClienteRepository;
import com.algaworks.api.exceptions.NegocioExpcetion;

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
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente atualizar(Cliente cliente) {
		boolean isExists = clienteRepository.existsById(cliente.getId());
		if(!isExists) {
			throw new NegocioExpcetion("Cliente não encontrado");
		}
		return clienteRepository.save(cliente);
	}

	public Long excluir(Long id) {
		boolean isExists = clienteRepository.existsById(id);
		if(!isExists) {
			throw new NegocioExpcetion("Cliente não encontrado");
		}
		clienteRepository.deleteById(id);
		return id;
	}
	
	
}
