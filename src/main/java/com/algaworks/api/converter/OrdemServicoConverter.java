package com.algaworks.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.api.domain.model.OrdemServico;
import com.algaworks.api.domain.model.dto.OrdemServicoDTO;

@Component
public class OrdemServicoConverter {
	
	@Autowired
	private ClienteConverter clienteConverter;

	public OrdemServico converterDTOemEntidade(OrdemServicoDTO dto) {
		OrdemServico ordem = new OrdemServico();
		ordem.setId(dto.getId());
		ordem.setDescricao(dto.getDescricao());
		ordem.setCliente(clienteConverter.converterDTOemEntidade(dto.getCliente()));
		ordem.setPreco(dto.getPreco());
		ordem.setDataAbertura(dto.getDataAbertura());
		ordem.setDataFinalizacao(dto.getDataFinalizacao());
		ordem.setStatus(dto.getStatus());
		return ordem;
	}
	
	public OrdemServicoDTO converterEntidadeEmDTO(OrdemServico ordem) {
		OrdemServicoDTO dto = new OrdemServicoDTO();
		dto.setId(ordem.getId());
		dto.setCliente(clienteConverter.converterEntidadeEmDTO(ordem.getCliente()));
		dto.setDescricao(ordem.getDescricao());
		dto.setPreco(ordem.getPreco());
		dto.setDataAbertura(ordem.getDataAbertura());
		dto.setDataFinalizacao(dto.getDataFinalizacao());
		dto.setStatus(dto.getStatus());
		return dto;
	}
	
	public List<OrdemServicoDTO> converterListEntidadeEmDTO(List<OrdemServico> ordens) {
		List<OrdemServicoDTO> listDTO = new ArrayList<>();
		for (OrdemServico ordemServico : ordens) {
			OrdemServicoDTO itens = converterEntidadeEmDTO(ordemServico);
			listDTO.add(itens);
		}
		return listDTO;
	}
	
	
}
