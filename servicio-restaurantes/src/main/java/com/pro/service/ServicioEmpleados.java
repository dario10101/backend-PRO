package com.pro.service;

import java.util.List;

import com.pro.entity.Empleado;

/**
 * Servicios disponibles para gestionar los empleados de un restaurante
 * 
 * @author Ruben
 *
 */
public interface ServicioEmpleados {
	
	/**
	 * Listar todos los empleados de un restaurante
	 * @param nit Identificador del restaurante
	 * @return Lista de empleados
	 */
	public List<Empleado> listarEmpleados(String nit);
	
	/**
	 * Buscar los empleados segun su status, activos o inactivos
	 * @param nit Identificador del restaurante
	 * @param status (ACTIVATED o DELETED)
	 * @return Lista de empleados
	 */
	public List<Empleado> buscarPorStatus(String nit, String status);
	
	/**
	 * Buscar un empleado por su correo
	 * @param correo
	 * @return Empleado encontrado
	 */
	public Empleado buscarEmpleadoPorCorreo(String correo);
	
	/**
	 * Buscar empleado por su identificacion
	 * @param idEmpleado Identificacion
	 * @return Empleado encontrado
	 */
	public Empleado buscarEmpleadoPorId(Long idEmpleado);
	
	
	/**
	 * Validar correo y contraseña de un empleado
	 * @param empleado Objeto empleado con su correo y contraseña
	 * @return Empleado encontrado, si son validas las credenciales
	 */
	public Empleado validarCredenciales(Empleado empleado);
	
	/**
	 * Agregar un nuevo empleado al registro de un restaurante
	 * @param empleado empleado valido
	 * @return Empleado creado, si se crea exitosamente
	 */
	public Empleado crearEmpleado(Empleado empleado);
	
	/**
	 * Actualizar campos de un emppleado
	 * @param empleado Empleado con los cambios realizados
	 * @return Empleado modificado
	 */
	public Empleado actualizarEmpleado(Empleado empleado);
	
	/**
	 * Cambiar estado de un empleado a DELETED
	 * @param idEmpleado Identificaciondel empleado
	 * @return Empleado modificado
	 */
	public Empleado eliminarEmpleado(Long idEmpleado);
	
	/**
	 * Cambiar estado de un empleado a ACTIVATED
	 * @param idEmpleado Identificaciondel empleado
	 * @return Empleado modificado
	 */
    public Empleado activarEmpleado(Long idEmpleado);
	
}
