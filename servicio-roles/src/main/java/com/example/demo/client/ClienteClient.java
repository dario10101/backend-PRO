package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;


/**
 * Esta clase permite conectar con el servicio de clientes para verificar credenciales
 * de un cliente
 * 
 * @author Ruben
 *
 */
@FeignClient(name = "servicio-clientes")
@RequestMapping (value = "/clientes")
public interface ClienteClient {
	
	/**
	 * Verificar credenciales de un cliente
	 * @param cliente Credenciales de un cliente
	 * @return Cliente encontrado, solo si las credenciales son correctas
	 */
	@PostMapping(value = "/validar-cliente")
    public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente);
}
