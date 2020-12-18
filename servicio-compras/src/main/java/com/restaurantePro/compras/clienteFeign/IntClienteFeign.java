package com.restaurantePro.compras.clienteFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantePro.compras.modelo.Cliente;

/**
 * Esta interface permite la integración con el microservicio "servicio-clientes"
 * mediante un cliente de servicio REST de manera declarativa, para ello se utilizara 
 * la herramienta desarrollada por Netflix "Feign".
 * 
 * @author Héctor Fabio Meneses
 *
 */
@FeignClient(name = "servicio-clientes")
@RequestMapping (value = "/clientes")
public interface IntClienteFeign {
	/**
	 * Este método permite acceder al servicio buscarClientePorId expuesto en el controlador
	 * del microservicio "servicio-clientes"
	 * @param idCliente. Es el identificador único del cliente en el sistema.
	 * @return. El cliente regristrado en el sistema con el identificador ingresado por parámetro.
	 */
	@GetMapping(value = "buscar-por-id/{idcliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("idcliente") Long idCliente);

}
