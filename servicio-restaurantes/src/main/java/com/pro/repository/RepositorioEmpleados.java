package com.pro.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Empleado;
import com.pro.entity.Restaurante;

/**
 * Representa las funciones sobre el medio de almacenamiento de los empleados
 * 
 * @author Ruben
 *
 */
@Repository
public interface RepositorioEmpleados extends JpaRepository<Empleado, Long>{
	
	/**
	 * Buscar un empleado por su correo
	 * @param correo_empleado
	 * @return Empleado encontrado
	 */
	public Empleado findByCorreoEmpleado(String correo_empleado);
	
	/**
	 * Buscar todos loe empleados que pertenecen a un restaurante
	 * @param restaurante Restaurante solicitado
	 * @return Lista de empleados
	 */
	public List<Empleado> findByRestaurante(Restaurante restaurante);
	
}
