package com.algaworks.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.converter.ComentarioConverter;
import com.algaworks.api.converter.OrdemServicoConverter;
import com.algaworks.api.domain.exceptions.NegocioException;
import com.algaworks.api.domain.exceptions.NotFoundException;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.Comentario;
import com.algaworks.api.domain.model.OrdemServico;
import com.algaworks.api.domain.model.dto.ComentarioDTO;
import com.algaworks.api.domain.model.dto.OrdemServicoDTO;
import com.algaworks.api.domain.model.enums.StatusOrdemServico;
import com.algaworks.api.domain.repository.IClienteRepository;
import com.algaworks.api.domain.repository.IComentarioRepository;
import com.algaworks.api.domain.repository.IOrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private IOrdemServicoRepository ordemRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoConverter ordemConverter;
	
	@Autowired
	private IComentarioRepository comentarioRepository;
	
	@Autowired
	private ComentarioConverter comentarioConverter;

	@Transactional
	public OrdemServicoDTO inserir(OrdemServicoDTO ordemDTO) {
		this.validarCliente(ordemDTO);
		this.inserirDadosObrigatorio(ordemDTO);
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
	
	@Transactional
	public ComentarioDTO adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = this.buscarOrdem(ordemServicoId);	
		Comentario comentario = this.setarDadosComentarios(descricao, ordemServico);			
		comentarioRepository.save(comentario);		
		return comentarioConverter.converterEntidadeEmDTO(comentario);
	}
	
	@Transactional
	public List<ComentarioDTO> consultarComentarios() {
		List<Comentario> comentarios = comentarioRepository.findAll();
		this.verificarListaVaziaComents(comentarios);
		return comentarioConverter.converterListEntidadeEmDTO(comentarios);
	}
	
	@Transactional
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = this.buscarOrdem(ordemServicoId);		
		this.finalizarOrdem(ordemServico);
		ordemRepository.save(ordemServico);		
	}


	// MÉTODOS DE APOIO
	
	private void finalizarOrdem(OrdemServico ordemServico) {
		boolean podeSerFinalizada = StatusOrdemServico.ABERTA.equals(ordemServico.getStatus());
		boolean naoPodeSerFinalizada = !podeSerFinalizada;
		if (naoPodeSerFinalizada) {
			throw new NegocioException("Ordem de serviço não pode ser finalizada");
		}
		
		ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
		ordemServico.setDataFinalizacao(OffsetDateTime.now());
	}
	
	private void inserirDadosObrigatorio(OrdemServicoDTO ordemDTO) {
		ordemDTO.setCliente(ordemDTO.getCliente());
		ordemDTO.setStatus(StatusOrdemServico.ABERTA);
		ordemDTO.setDataAbertura(OffsetDateTime.now());
	}
	
	private Comentario setarDadosComentarios(String descricao, OrdemServico ordemServico) {
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		return comentario;
	}
	
	private OrdemServico buscarOrdem(Long ordemServicoId) {
		return ordemRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NotFoundException("Ordem de serviço não encontrada"));
	}
	
	private void verificarListaVaziaComents(List<Comentario> comentarios) {
		if(comentarios.isEmpty()) {
			throw new NotFoundException("Nada encontrado");
		}
		
	}

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
