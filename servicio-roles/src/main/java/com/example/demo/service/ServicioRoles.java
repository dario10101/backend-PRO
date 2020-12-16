package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Rol;
import com.example.demo.model.Usuario;

/**
 * Esta clase contiene todos los servicios relacionados con los roles de los stakeholders en el sistema, 
 * para realizar el registro en la aplicacion, el login, debido a que se presupone una pantalla de login donde 
 * una persona coloca su correo y contraseña y el sistema debera mostrar una pantalla especial para el, sea un cliente
 * o un empleado de un restaurante
 * 
 * @author Ruben
 *
 */
public interface ServicioRoles {
	
	/**
	 * Listar todos lo roles disponibles
	 * @return Lista de roles
	 * @deprecated
	 */
	public List<Rol> listarRoles();
	
	/**
	 * Valida credenciales de un usuario (sea cliente o empleado), util para el login en el sistema 
	 * @param user Datos del usuario a validar (correo, identificacion, contraseña)
	 * @return Datos del usuario si es valido, ademas de informacion sobre el tipo de usuario (cliente o empleado)
	 */
	public Usuario validarRol(Usuario user);
	
	/**
	 * Buscar la informacion de un rol por su id
	 * @param idRol Identificador del rol
	 * @return Rol con sus datos
	 */
	public Rol buscarRolPorId(Long idRol);
	
	
}
