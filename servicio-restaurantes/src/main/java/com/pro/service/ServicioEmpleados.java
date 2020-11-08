package com.pro.service;

import java.util.List;

import com.pro.entity.Empleado;

public interface ServicioEmpleados {
	
	public List<Empleado> listarEmpleados(String nit);
	public List<Empleado> buscarPorStatus(String nit, String status);
	public Empleado buscarEmpleadoPorCorreo(String correo);
	public Empleado buscarEmpleadoPorId(Long idEmpleado);
	
	public Empleado validarCredenciales(Empleado empleado);
	public Empleado crearEmpleado(Empleado empleado);
	public Empleado actualizarEmpleado(Empleado empleado);
	
	public Empleado eliminarEmpleado(Long idEmpleado);
    public Empleado activarEmpleado(Long idEmpleado);
	
}
