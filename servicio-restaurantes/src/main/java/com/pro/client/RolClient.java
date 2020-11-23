package com.pro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.model.Rol;


@FeignClient(name = "servicio-roles")
@RequestMapping (value = "/roles")
public interface RolClient {
	
	@GetMapping(value = "buscar-por-id/{idrol}")
    public ResponseEntity<Rol> buscarRolPorId(@PathVariable("idrol") Long idRol);

}
