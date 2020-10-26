package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ClienteClient;
import com.example.demo.client.RestauranteClient;
import com.example.demo.entity.Rol;
import com.example.demo.model.Cliente;
import com.example.demo.model.Empleado;
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
	
	//Para cominucarse con el microservicio de restaurantes
	@Autowired
	RestauranteClient refRestaurante;
	
	
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
		
		//Verificamos si es un empleado
		Empleado empleado_prueba = new Empleado();
		empleado_prueba.setCorreoEmpleado(user.getCorreo());
		empleado_prueba.setPasswordEmpleado(user.getPassword());			
		
		//peticion remota al servicio de restaurantes
		Empleado empleado_encontrado = this.buscarEmpleado(empleado_prueba); 
		
		if(empleado_encontrado != null) {
			System.out.println("\nExito, es un empleado!!!\n");
			if(empleado_encontrado.getIdRolEmpleado() == null) {
				System.out.println("\nOjo, id null!!!\n");
			}
			return miRepositorioRoles.findById(empleado_encontrado.getIdRolEmpleado()).orElse(null);
		}
		
		
		
		return null;
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
	
	private Empleado buscarEmpleado(Empleado empleado) {
		Empleado empleado_encontrado = null;
		try {
			empleado_encontrado = refRestaurante.validarEmpleado(empleado).getBody();
		}catch(Exception e) {
			return null;
		}
		return empleado_encontrado;
	}
	
}
