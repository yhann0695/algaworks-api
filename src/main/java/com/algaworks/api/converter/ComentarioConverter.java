package com.algaworks.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.api.domain.model.Comentario;
import com.algaworks.api.domain.model.dto.ComentarioDTO;

@Component
public class ComentarioConverter {
	
	@Autowired
	private OrdemServicoConverter ordemConverter;

	public Comentario converterDTOemEntidade(ComentarioDTO dto) {
		Comentario coment = new Comentario();
		coment.setId(dto.getId());
		coment.setOrdemServico(ordemConverter.converterDTOemEntidade(dto.getOrdemServico()));
		coment.setDescricao(dto.getDescricao());
		coment.setDataEnvio(dto.getDataEnvio());
		return coment;
	}
	
	public ComentarioDTO converterEntidadeEmDTO(Comentario comentario) {
		ComentarioDTO dto = new ComentarioDTO();
		dto.setId(comentario.getId());
		dto.setOrdemServico(ordemConverter.converterEntidadeEmDTO(comentario.getOrdemServico()));
		dto.setDescricao(comentario.getDescricao());
		dto.setDataEnvio(comentario.getDataEnvio());
		return dto;
	}
	
	public List<ComentarioDTO> converterListEntidadeEmDTO(List<Comentario> comentarios) {
		List<ComentarioDTO> listDTO = new ArrayList<>();
		for (Comentario comentario : comentarios) {
			ComentarioDTO item = converterEntidadeEmDTO(comentario);
			listDTO.add(item);
		}
		return listDTO;
	}
}
