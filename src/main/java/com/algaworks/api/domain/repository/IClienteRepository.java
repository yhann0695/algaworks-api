package com.algaworks.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.api.domain.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
