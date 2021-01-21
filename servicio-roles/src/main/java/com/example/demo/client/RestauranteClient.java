package com.example.demo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Empleado;

/**
 * Esta clase permite conectar con el servicio de restaurantes para verificar credenciales
 * de un empleado
 * 
 * @author Ruben
 *
 */
@FeignClient(name = "servicio-restaurantes")
@RequestMapping (value = "/restaurantes")
public interface RestauranteClient {
	
	/**
	 * Verificar credenciales de un empleado
	 * @param empleado Credenciales de un empleado
	 * @return Empleado encontrado, solo si las credenciales son correctas
	 */
	@PostMapping(value = "/validar-empleado")
    public ResponseEntity<Empleado> validarEmpleado(@RequestBody Empleado empleado);
	
	
}
