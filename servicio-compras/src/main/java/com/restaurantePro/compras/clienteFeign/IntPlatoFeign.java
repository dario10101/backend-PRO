package com.restaurantePro.compras.clienteFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurantePro.compras.modelo.Plato;

/**
 * Esta interface permite la integración con el microservicio "servicio-platos"
 * mediante un cliente de servicio REST de manera declarativa, para ello se utilizara 
 * la herramienta desarrollada por Netflix "Feign".
 * 
 * @author Héctor Fabio Meneses
 *
 */

@FeignClient(name = "servicio-platos")
@RequestMapping (value = "/platos")
public interface IntPlatoFeign {
	/**
	 * Este método permite acceder al servicio buscarPlatoPorId expuesto en el controlador
	 * del microservicio "servicio-platos"
	 * @param idPlato. Es el identificador único de un plato en el sistema.
	 * @return. El plato regristrado en el sistema con el identificador ingresado por parámetro.
	 */
	@GetMapping(value = "buscar-por-id/{idplato}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("idplato") Long idPlato);
	
	/**
	 * Este método permite acceder al servicio actualizarCantidadPlato expuesto en el controlador
	 * del microservicio "servicio-platos" y asi actualizar la cantidad de un plato despues de realizar
	 * una venta.
	 * @param id. Es el identificador único de un plato en el sistema.
	 * @param cantidad. Es la nueva cantidad que se dispondrá del plato en el sistema.
	 * @return. El plato con la cantidad de exitencias actualizada
	 */
	@PutMapping (value = "/actualizar-cantidad/{id}")
    public ResponseEntity<Plato> actualizarCantidadPlato(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad);

}
