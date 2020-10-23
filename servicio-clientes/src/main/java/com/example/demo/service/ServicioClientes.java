package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface ServicioClientes {

	public List<Cliente> listarClientes();	
	public Cliente buscarClientePorId(Long idCliente);	
	public Cliente crearCliente(Cliente cliente);	
    public Cliente actualizarCliente(Cliente cliente);    
    public Cliente eliminarCliente(Long idCliente);
    public Cliente activarCliente(Long idCliente);
	public Cliente validarCredenciales(Cliente cliente);
	
}
