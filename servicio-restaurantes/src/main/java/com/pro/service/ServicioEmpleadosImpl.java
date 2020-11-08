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
	public List<Empleado> listarEmpleados(String nit) {		
		Restaurante rest = Restaurante.builder().nitRest(nit).build();
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		if (rest != null) {
			System.out.println("\n id del restaurante: " + rest.getNitRest() + "\n");
			empleados = miRepositorioEmpleados.findByRestaurante(rest);
		}
		
		return empleados;
	}

	@Override
	public Empleado buscarEmpleadoPorCorreo(String correo) {	
		if(correo == null)
			return null;
					
		return miRepositorioEmpleados.findByCorreoEmpleado(correo);
	}
	
	@Override
	public Empleado buscarEmpleadoPorId(Long idEmpleado) {
		if(idEmpleado == null)
			return null;
		
		return miRepositorioEmpleados.findById(idEmpleado).orElse(null);
	}
	
	@Override
	public List<Empleado> buscarPorStatus(String nit, String status) {
		//Datos no validos
		System.out.println("\n entrando... \n");
		if(nit == null || status == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		Restaurante rest = Restaurante.builder().nitRest(nit).build();
		if(rest == null) {
			System.out.println("\n Rest es null \n");
			return null;
		}
		
		List<Empleado> empleados_encontrados = miRepositorioEmpleados.findByRestaurante(rest); 
		
		if(empleados_encontrados == null) {
			return null;
		}
		
		if(empleados_encontrados.size() <= 0) {
			return null;
		}		
		
		empleados_encontrados = this.filtrarPorStatus(empleados_encontrados, status);
		
		return empleados_encontrados;
	}

	@Override
	public Empleado validarCredenciales(Empleado empleado) {
		Empleado empleado_encontrado = miRepositorioEmpleados.findByCorreoEmpleado(empleado.getCorreoEmpleado());
		
		//TODO ver como se debe hacer esta parte
		if(empleado_encontrado != null) {
			//Machete del bueno
			if(empleado_encontrado.getRestaurante() != null) {
				empleado_encontrado.setNitRestAux(empleado_encontrado.getRestaurante().getNitRest());
			}
			
			if(!empleado_encontrado.getPasswordEmpleado().equals(empleado.getPasswordEmpleado())) {
				empleado_encontrado = null;
			}
			
			if(!empleado_encontrado.getStatusEmpleado().equals("ACTIVATED")) {
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
		
		if(empleado.getStatusEmpleado() == null) {
			empleado.setStatusEmpleado("ACTIVATED");
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
		
		//Machete del bueno
		if(empleado_encontrado.getRestaurante() != null) {
			empleado_encontrado.setNitRestAux(empleado_encontrado.getRestaurante().getNitRest());
		}
		
		return miRepositorioEmpleados.save(empleado_encontrado);
	}

	@Override
	public Empleado eliminarEmpleado(Long idEmpleado) {
		Empleado empleado_encontrado = buscarEmpleadoPorId(idEmpleado);
        if (null == empleado_encontrado){
            return null;
        }
        
        empleado_encontrado.setStatusEmpleado("DELETED");
        return miRepositorioEmpleados.save(empleado_encontrado);
	}

	@Override
	public Empleado activarEmpleado(Long idEmpleado) {
		Empleado empleado_encontrado = buscarEmpleadoPorId(idEmpleado);
        if (null == empleado_encontrado){
            return null;
        }
        
        empleado_encontrado.setStatusEmpleado("ACTIVATED");
        return miRepositorioEmpleados.save(empleado_encontrado);
	}
	
	
	
	private List<Empleado> filtrarPorStatus(List<Empleado> empleados, String status) {
		List<Empleado> nuevos = new ArrayList<Empleado>();
		if(status != null && empleados != null) {
			for(int i = 0; i < empleados.size(); i++) {
				if(empleados.get(i).getStatusEmpleado().equals(status)) {
					nuevos.add(empleados.get(i));
				}
			}
		}
		return nuevos;
	}

	

	
	
}
