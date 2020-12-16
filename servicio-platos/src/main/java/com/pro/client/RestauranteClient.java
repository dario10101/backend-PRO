package com.pro.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.model.Restaurante;

/**
 * Cliente feign (herramienta proporcionada por el framework de spring para 
 * facilitar la cominucacion entre microservicios, en este caso con el servicio de restaurantes.
 * 
 * @author Ruben
 *
 */
@FeignClient(name = "servicio-restaurantes")
@RequestMapping (value = "/restaurantes")
public interface RestauranteClient {
	
	/**
	 * Se conecta con el microservicio de restaurantes y trae todos los restaurantes
	 * @deprecated no deberia usarse
	 * @return Todos los restaurantes
	 */
	@GetMapping
	public ResponseEntity<List<Restaurante>> listarRestaurantes();
	
	/**
	 * Buscar un restaurante por su nit, en el microservicio de restaurantes
	 * @param nit Identificador del restaurante
	 * @return Restaurante encontrado
	 */
	@GetMapping(value = "/buscar-por-nit/{nit}")
	public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable("nit") String nit);

}
