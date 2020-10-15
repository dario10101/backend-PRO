package com.pro.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.model.Restaurante;


@FeignClient(name = "servicio-restaurantes")
@RequestMapping (value = "/restaurantes")
public interface RestauranteClient {
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listarRestaurantes();
	
	
	@GetMapping(value = "buscar-por-id/{id}")
	public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable("id") Long id);

}
