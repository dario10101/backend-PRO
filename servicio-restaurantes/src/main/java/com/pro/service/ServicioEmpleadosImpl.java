package com.pro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.entity.Empleado;
import com.pro.entity.Restaurante;
import com.pro.repository.RepositorioEmpleados;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioEmpleadosImpl implements ServicioEmpleados{
	
	private final RepositorioEmpleados miRepositorioEmpleados;

	@Override
	public List<Empleado> listarEmpleados(Long idRest) {		
		Restaurante rest = Restaurante.builder().idRest(idRest).build();
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		if (rest != null) {
			System.out.println("\n id del restaurante: " + rest.getIdRest() + "\n");
			empleados = miRepositorioEmpleados.findByRestaurante(rest);
		}
		
		return empleados;
	}

	@Override
	public Empleado buscarEmpleadoPorCorres(String correo) {
		
		return miRepositorioEmpleados.findByCorreoEmpleado(correo);
	}

	@Override
	public Empleado validarCredenciales(Empleado empleado) {
		Empleado empleado_encontrado = miRepositorioEmpleados.findByCorreoEmpleado(empleado.getCorreoEmpleado());
		
		//TODO ver como se debe hacer esta parte
		if(empleado_encontrado != null) {
			if(!empleado_encontrado.getPasswordEmpleado().equals(empleado.getPasswordEmpleado())) {
				empleado_encontrado = null;
			}
		}
		
		return empleado_encontrado;
	}
	
}
