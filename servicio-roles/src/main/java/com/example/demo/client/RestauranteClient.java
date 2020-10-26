package com.example.demo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Empleado;


@FeignClient(name = "servicio-restaurantes")
@RequestMapping (value = "/restaurantes")
public interface RestauranteClient {
	
	@PostMapping(value = "/validar-empleado")
    public ResponseEntity<Empleado> validarEmpleado(@RequestBody Empleado empleado);
	
	
}
