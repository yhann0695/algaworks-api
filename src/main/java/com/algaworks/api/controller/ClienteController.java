package com.algaworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.model.Cliente;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

	@GetMapping
	public List<Cliente> testando() {
		Cliente c1 = new Cliente();
		c1.setId(1L);
		c1.setNome("Yhann");
		c1.setEmail("yhann0695@hotmail.com");
		c1.setTelefone("995614282");
		Cliente c2 = new Cliente();
		c2.setId(2L);
		c2.setNome("Gomes");
		c2.setEmail("gomes0695@hotmail.com");
		c2.setTelefone("99561-4282");
		return Arrays.asList(c1,c2);
		
		
	}
	
}
