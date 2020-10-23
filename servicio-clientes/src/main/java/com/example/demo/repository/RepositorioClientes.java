package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;

@Repository
public interface RepositorioClientes extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByNombresCliente(String nombre_cliente);
	
	public List<Cliente> findByApellidosCliente(String apellidos_cliente);
	
	public Cliente findByCorreoCliente(String nombre_cliente);
	
}
