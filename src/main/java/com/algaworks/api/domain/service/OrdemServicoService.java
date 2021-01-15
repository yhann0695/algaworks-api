package com.algaworks.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.converter.OrdemServicoConverter;
import com.algaworks.api.domain.exceptions.NotFoundException;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.OrdemServico;
import com.algaworks.api.domain.model.dto.OrdemServicoDTO;
import com.algaworks.api.domain.model.enums.StatusOrdemServico;
import com.algaworks.api.domain.repository.IClienteRepository;
import com.algaworks.api.domain.repository.IOrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private IOrdemServicoRepository ordemRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoConverter ordemConverter;

	@Transactional
	public OrdemServicoDTO inserir(OrdemServicoDTO ordemDTO) {
		this.validarCliente(ordemDTO);
		ordemDTO.setCliente(ordemDTO.getCliente());
		ordemDTO.setStatus(StatusOrdemServico.ABERTA);
		ordemDTO.setDataAbertura(OffsetDateTime.now());
		OrdemServico ordem = ordemConverter.converterDTOemEntidade(ordemDTO);
		ordemRepository.save(ordem);
		return ordemConverter.converterEntidadeEmDTO(ordem);
	}
	
	@Transactional
	public List<OrdemServicoDTO> consultarTodos() {
		List<OrdemServico> ordens = ordemRepository.findAll();
		this.verificarListaVazia(ordens);
		return ordemConverter.converterListEntidadeEmDTO(ordens);
	}
	
	

	@Transactional
	public OrdemServicoDTO consultarPorId(Long id) {
		 OrdemServico ordem = ordemRepository.recuperarOrdem(id);
		 return ordemConverter.converterEntidadeEmDTO(ordem);
	}

	// VALIDAÇÃO
	
	private void validarCliente(OrdemServicoDTO ordemDTO) {
		Optional<Cliente> cliente = clienteRepository.findById(ordemDTO.getCliente().getId());
		if(!cliente.isPresent()) {
			throw new NotFoundException("Cliente não encontrado");
		}
	}
	
	private void verificarListaVazia(List<OrdemServico> ordens) {
		if(ordens.isEmpty()) {
			throw new NotFoundException("Nada encontrado");
		}
		
	}



	
}
