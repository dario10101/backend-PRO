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
	
	private Long rolCliente = 1L;
	private Long rolSuperadmin = 4L;
	private Long idSuperadmin = 0L;
	
	
	@Override
	public List<Rol> listarRoles() {
		return miRepositorioRoles.findAll();
	}

	
	@Override
	public Usuario validarRol(Usuario user) {
		Usuario rol_usuario = new Usuario();
		Rol rol_encontrado = new Rol();
		
		//Verificamos si es un cliente
		Cliente cliente_prueba = new Cliente();
		cliente_prueba.setCorreoCliente(user.getCorreo());
		cliente_prueba.setPasswordCliente(user.getPassword());			
		
		//peticion remota al servicio de clientes
		Cliente cliente_encontrado = this.buscarCliente(cliente_prueba); 
		
		// Es un cliente
		if(cliente_encontrado != null) {
			rol_encontrado = miRepositorioRoles.findById(rolCliente).orElse(null);
			if(rol_encontrado != null) {
				rol_usuario.setIdUsuario(cliente_encontrado.getIdCliente());
				rol_usuario.setCorreo(cliente_encontrado.getCorreoCliente());
				rol_usuario.setIdRol(rol_encontrado.getIdRol());
				rol_usuario.setNombreRol(rol_encontrado.getNombreRol());
			}
			
			//retornar cliente
			return rol_usuario;
		}
		
		//Verificamos si es un empleado
		Empleado empleado_prueba = new Empleado();
		empleado_prueba.setCorreoEmpleado(user.getCorreo());
		empleado_prueba.setPasswordEmpleado(user.getPassword());			
		
		//peticion remota al servicio de restaurantes
		Empleado empleado_encontrado = this.buscarEmpleado(empleado_prueba); 
		
		// es un empleado
		if(empleado_encontrado != null) {
			rol_encontrado = miRepositorioRoles.findById(empleado_encontrado.getIdRolEmpleado()).orElse(null);
			if(rol_encontrado != null) {
				rol_usuario.setIdUsuario(empleado_encontrado.getIdEmpleado());
				rol_usuario.setCorreo(empleado_encontrado.getCorreoEmpleado());
				rol_usuario.setIdRol(rol_encontrado.getIdRol());
				rol_usuario.setNombreRol(rol_encontrado.getNombreRol());
				//rol_usuario.setIdRestauranteAux(empleado_encontrado.getIdRestauranteAux());
			}
			
			// retornar empleado
			return rol_usuario;
		}
		
		//validar superadmin
		if(this.validarSuperadmin(user.getCorreo(), user.getPassword())) {
			rol_encontrado = miRepositorioRoles.findById(rolSuperadmin).orElse(null);
			if(rol_encontrado != null) {
				rol_usuario.setIdUsuario(idSuperadmin);
				rol_usuario.setCorreo(user.getCorreo());
				rol_usuario.setIdRol(rol_encontrado.getIdRol());
				rol_usuario.setNombreRol(rol_encontrado.getNombreRol());
			}
			
			//retornar administrador
			return rol_usuario;
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
	
	//metodo temporal
	private boolean validarSuperadmin(String correo, String password) {
		String correoSuperadmin = "admin@unicauca.edu.co";
		String passwordSuperadmin = "123";
		
		if(correo != null && password != null) {
			if(correo.equals(correoSuperadmin) && password.equals(passwordSuperadmin)) {
				return true;
			}
		}
		
		return false;
	}
	
}
