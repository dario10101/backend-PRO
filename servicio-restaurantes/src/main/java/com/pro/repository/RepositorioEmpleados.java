package com.pro.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pro.entity.Empleado;
import com.pro.entity.Restaurante;

@Repository
public interface RepositorioEmpleados extends JpaRepository<Empleado, Long>{
	
	public Empleado findByCorreoEmpleado(String correo_empleado);
	
	public List<Empleado> findByRestaurante(Restaurante restaurante);
	
}
