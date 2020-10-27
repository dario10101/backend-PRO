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
	public Empleado buscarEmpleadoPorCorreo(String correo) {		
		return miRepositorioEmpleados.findByCorreoEmpleado(correo);
	}
	
	@Override
	public Empleado buscarEmpleadoPorId(Long idEmpleado) {
		return miRepositorioEmpleados.findById(idEmpleado).orElse(null);
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

	@Override
	public Empleado crearEmpleado(Empleado empleado) {
		if(empleado == null)
			return null;		
		
		//verificar si existe
		Empleado empleado_encontrado = null;
		if(empleado.getIdEmpleado() != null)
			empleado_encontrado = miRepositorioEmpleados.findById(empleado.getIdEmpleado()).orElse(null);
				
		//el cliente ya existe
		if (empleado_encontrado != null){
			System.out.println("\nCliente exixtente\n");
            return null;
        }
		return miRepositorioEmpleados.save(empleado);
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado) {
		if(empleado == null)
			return null;		
		Long idEmpleado = empleado.getIdEmpleado();
		
		// El Empleado no tiene el id
		if(idEmpleado == null)
			return null;		
		
		Empleado empleado_encontrado = buscarEmpleadoPorId(idEmpleado);
        
		// NO existe el cliente
		if (empleado_encontrado == null)
            return null;
		
		empleado_encontrado.setNombreEmpleado(empleado.getNombreEmpleado());
		empleado_encontrado.setCorreoEmpleado(empleado.getCorreoEmpleado());
		empleado_encontrado.setPasswordEmpleado(empleado.getPasswordEmpleado());
		empleado_encontrado.setStatusEmpleado(empleado.getStatusEmpleado());
		empleado_encontrado.setTelefonoEmpleado(empleado.getTelefonoEmpleado());
		empleado_encontrado.setImgEmpleado(empleado.getImgEmpleado()); 
		empleado_encontrado.setDireccionEmpleado(empleado.getDireccionEmpleado()); 
		empleado_encontrado.setIdRolEmpleado(empleado.getIdRolEmpleado());
		
		if(empleado_encontrado.getStatusEmpleado() == null) {
			empleado_encontrado.setStatusEmpleado("ACTIVATED");
		}
		
		return miRepositorioEmpleados.save(empleado_encontrado);
	}

	
	
}
