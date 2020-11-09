package com.restaurantePro.compras.clienteFeign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.restaurantePro.compras.modelo.Cliente;

@Component
public class ClienteHystrixFallbackFactory implements IntClienteFeign {

	@Override
	public ResponseEntity<Cliente> buscarClientePorId(Long parIdCliente) {
		Cliente objCliente = Cliente.builder()
				                    .nombresCliente("none")
				                    .apellidosCliente("none")
				                    .celularCliente("none")
				                    .telefonoCliente("none")
				                    .direccionCliente("none")
				                    .correoCliente("none")
				                    .imgCliente("none")
				                    .telefonoCliente("none").build();
				
		return ResponseEntity.ok(objCliente);
	}
	

}
