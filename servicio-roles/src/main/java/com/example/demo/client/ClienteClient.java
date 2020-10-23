package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;



@FeignClient(name = "servicio-clientes")
@RequestMapping (value = "/clientes")
public interface ClienteClient {
	
	@PostMapping(value = "/validar-cliente")
    public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente);
}
