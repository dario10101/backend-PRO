package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ServicioImagenes;

@RestController
@RequestMapping (value = "/imagenes")
public class ControladorImagenes {

	@Autowired
    private ServicioImagenes miServicioImagenes;
	
	
	@PostMapping(value = "/agregar-imagen-plato/{idplato}")
    public ResponseEntity<String> agregarImagenPlato(@RequestBody String imagenCodificada) throws Exception{
		miServicioImagenes.agregarImagenPlato(1L, "");
		
		return null;
	}
	
	@GetMapping(value = "/obtener-imagen-plato/{idplato}")
    public ResponseEntity<String> obtenerImagenPlato(@PathVariable("idplato") Long idPlato){
		
		
		return null;
	}
	
	
	
	@PostMapping(value = "/agregar-imagen-restaurante/{nitrest}")
    public ResponseEntity<String> agregarImagenRestaurante(@RequestBody String imagenCodificada){
		
		
		return null;
	}
	
	
	@GetMapping(value = "/obtener-imagen-restaurante/{nitrest}")
    public ResponseEntity<String> obtenerImagenRestaurante(@PathVariable("nitrest") String nitRest){
		
		
		return null;
	}
	
	
	
	
}
