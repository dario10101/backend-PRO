package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/imagenes")
public class ControladorImagenes {

	
	@PostMapping(value = "/agregar-imagen-plato/{idplato}")
    public ResponseEntity<String> agregarImagenPlato(@RequestBody String imagenCodificada){
		
		
		return null;
	}
	
	@PostMapping(value = "/agregar-imagen-restaurante/{nitrest}")
    public ResponseEntity<String> agregarImagenRestaurante(@RequestBody String imagenCodificada){
		
		
		return null;
	}
	
	@PostMapping(value = "/obtener-imagen-plato/{idplato}")
    public ResponseEntity<String> obtenerImagenPlato(@RequestBody String imagenCodificada){
		
		
		return null;
	}
	
	@PostMapping(value = "/obtener-imagen-restaurante/{nitrest}")
    public ResponseEntity<String> obtenerImagenRestaurante(@RequestBody String imagenCodificada){
		
		
		return null;
	}
	
	
	
	
}
