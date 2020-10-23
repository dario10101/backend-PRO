package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.RepositorioClientes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioClientesImpl implements ServicioClientes {
	
	private final RepositorioClientes miRepositorioClientes;
	
	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> clientesPrueba = miRepositorioClientes.findAll();
		
		return clientesPrueba;
	}
	@Override
	public Cliente buscarClientePorId(Long idCliente) {
		Cliente clienteEncontrado = miRepositorioClientes.findById(idCliente).orElse(null);
		
		return clienteEncontrado;
	}

	@Override
	public Cliente crearCliente(Cliente cliente) {
		if(cliente == null)
			return null;		
		
		//verificar si existe
		Cliente cliente_encontrado = null;
		if(cliente.getIdCliente() != null)
			cliente_encontrado = buscarClientePorId(cliente.getIdCliente());
				
		//el cliente ya existe
		if (cliente_encontrado != null){
			System.out.println("\nCliente exixtente\n");
            return null;
        }
		return miRepositorioClientes.save(cliente);
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		if(cliente == null)
			return null;		
		Long idCliente = cliente.getIdCliente();
		
		// El cliente no tiene el id
		if(idCliente == null)
			return null;		
		Cliente cliente_encontrado = buscarClientePorId(idCliente);
        
		// NO existe el cliente
		if (cliente_encontrado == null)
            return null;
		
		cliente_encontrado.setNombresCliente(cliente.getNombresCliente());
		cliente_encontrado.setApellidosCliente(cliente.getApellidosCliente());
		cliente_encontrado.setCorreoCliente(cliente.getCorreoCliente());
		cliente_encontrado.setPasswordCliente(cliente.getPasswordCliente());
		cliente_encontrado.setStatusCliente(cliente.getStatusCliente());
		cliente_encontrado.setTelefonoCliente(cliente.getTelefonoCliente());
		cliente_encontrado.setImgCliente(cliente.getImgCliente());        
		
		return miRepositorioClientes.save(cliente_encontrado);
	}

	@Override
	public Cliente eliminarCliente(Long idCliente) {
		Cliente cliente_encontrado = buscarClientePorId(idCliente);
        if (null == cliente_encontrado){
            return null;
        }
        
        cliente_encontrado.setStatusCliente("DELETED");
        return miRepositorioClientes.save(cliente_encontrado);
	}

	@Override
	public Cliente activarCliente(Long idCliente) {
		Cliente cliente_encontrado = buscarClientePorId(idCliente);
        if (null == cliente_encontrado){
            return null;
        }
        
        cliente_encontrado.setStatusCliente("ACTIVATED");
        return miRepositorioClientes.save(cliente_encontrado);
	}
	
	
	@Override
	public Cliente validarCredenciales(Cliente cliente) {
		Cliente cliente_encontrado = miRepositorioClientes.findByCorreoCliente(cliente.getCorreoCliente());
		
		//TODO ver como se debe hacer esta parte
		if(cliente_encontrado != null) {
			if(!cliente_encontrado.getPasswordCliente().equals(cliente.getPasswordCliente())) {
				cliente_encontrado = null;
			}
		}
		
		return cliente_encontrado;
	}

	
	
	
	
}
