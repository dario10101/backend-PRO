package com.restaurantePro.compras.clienteFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantePro.compras.modelo.Cliente;

@FeignClient(name = "servicio-clientes")
@RequestMapping (value = "/clientes")
public interface IntClienteFeign {
	@GetMapping(value = "/buscar-por-id/{idcliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("idcliente") Long idCliente);

}
