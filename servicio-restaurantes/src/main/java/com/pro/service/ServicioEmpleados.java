package com.pro.service;

import java.util.List;

import com.pro.entity.Empleado;

public interface ServicioEmpleados {
	
	public List<Empleado> listarEmpleados(Long idRest);
	public Empleado buscarEmpleadoPorCorreo(String correo);
	public Empleado buscarEmpleadoPorId(Long idEmpleado);
	//public Cliente buscarClientePorId(Long idCliente);
	public Empleado validarCredenciales(Empleado empleado);
	public Empleado crearEmpleado(Empleado empleado);
	public Empleado actualizarEmpleado(Empleado empleado);
	
}
