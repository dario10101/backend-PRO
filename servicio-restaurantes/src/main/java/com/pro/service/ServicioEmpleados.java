package com.pro.service;

import java.util.List;

import com.pro.entity.Empleado;

public interface ServicioEmpleados {
	
	public List<Empleado> listarEmpleados(Long idRest);
	public Empleado buscarEmpleadoPorCorres(String correo);
	public Empleado validarCredenciales(Empleado empleado);
	
}
