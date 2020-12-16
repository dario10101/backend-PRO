package com.pro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.model.Rol;

/**
 * Clase que conecta y recive la informacion del servicio de roles, para conocer 
 * que cargos ocupa cada empelado en el restaurante
 * 
 * @author Ruben
 *
 */
@FeignClient(name = "servicio-roles")
@RequestMapping (value = "/roles")
public interface RolClient {
	
	/**
	 * Dado un identificador del ro, devuelve el rol al que pertenece
	 * @param idRol Identifiacdor del rol
	 * @return Rol del empleado (nombre del rol)
	 */
	@GetMapping(value = "buscar-por-id/{idrol}")
    public ResponseEntity<Rol> buscarRolPorId(@PathVariable("idrol") Long idRol);

}
