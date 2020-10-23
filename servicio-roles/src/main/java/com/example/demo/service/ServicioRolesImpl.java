package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ClienteClient;
import com.example.demo.entity.Rol;
import com.example.demo.model.Cliente;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RepositorioRoles;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioRolesImpl implements ServicioRoles {

	private final RepositorioRoles miRepositorioRoles;
	
	//Para cominucarse con el microservicio de clientes
	@Autowired
	ClienteClient refCliente;
	
	
	@Override
	public List<Rol> listarRoles() {
		return miRepositorioRoles.findAll();
	}

	
	@Override
	public Rol validarRol(Usuario user) {	
	
		//Verificamos si es un cliente
		Cliente cliente_prueba = new Cliente();
		cliente_prueba.setCorreoCliente(user.getCorreo());
		cliente_prueba.setPasswordCliente(user.getPassword());			
		
		//peticion remota al servicio de clientes
		Cliente cliente_encontrado = this.buscarCliente(cliente_prueba); 
		
		if(cliente_encontrado != null) {
			System.out.println("\nExito!!!\n");
			return miRepositorioRoles.findById(1L).orElse(null);
		}
		return miRepositorioRoles.findById(2L).orElse(null);
	}
	
	
	private Cliente buscarCliente(Cliente cliente) {
		Cliente cliente_encontrado = null;
		try {
			cliente_encontrado = refCliente.validarCliente(cliente).getBody();
		}catch(Exception e) {
			return null;
		}
		return cliente_encontrado;
	}
	
}
