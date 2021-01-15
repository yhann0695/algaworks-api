package com.algaworks.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.dto.ClienteDTO;

@Component
public class ClienteConverter {

	public Cliente converterDTOemEntidade(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(dto.getTelefone());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}
	
	public ClienteDTO converterEntidadeEmDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());
		dto.setTelefone(cliente.getTelefone());
		dto.setEmail(cliente.getEmail());
		return dto;
	}
	
	public List<ClienteDTO> converterListClienteParaDTO(List<Cliente> clientes) {
		List<ClienteDTO> listDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteDTO itens = converterEntidadeEmDTO(cliente);
			listDTO.add(itens);
		}
		return listDTO;
	}

}
