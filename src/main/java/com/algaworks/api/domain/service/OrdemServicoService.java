package com.algaworks.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.domain.exceptions.NotFoundException;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.OrdemServico;
import com.algaworks.api.domain.model.enums.StatusOrdemServico;
import com.algaworks.api.domain.repository.IClienteRepository;
import com.algaworks.api.domain.repository.IOrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private IOrdemServicoRepository ordemRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Transactional
	public OrdemServico inserir(OrdemServico ordem) {
		this.validarCliente(ordem);
		ordem.setCliente(ordem.getCliente());
		ordem.setStatus(StatusOrdemServico.ABERTA);
		ordem.setDataAbertura(OffsetDateTime.now());
		return ordemRepository.save(ordem);
	}
	
	@Transactional
	public List<OrdemServico> consultarTodos() {
		return ordemRepository.findAll();
	}
	
	@Transactional
	public OrdemServico consultarPorId(Long id) {
		 Optional<OrdemServico> optional =  ordemRepository.findById(id);
		 OrdemServico ordem = optional.get();
		 return ordem;
	}

	// VALIDAÇÃO
	
	private void validarCliente(OrdemServico ordem) {
		Optional<Cliente> cliente = clienteRepository.findById(ordem.getCliente().getId());
		if(!cliente.isPresent()) {
			throw new NotFoundException("Cliente não encontrado");
		}
	}



	
}
