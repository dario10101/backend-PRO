package com.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.entity.Restaurante;
import com.pro.service.ServicioRestaurantes;



@RestController
@RequestMapping (value = "/restaurantes")
public class controladorRestaurantes {

	@Autowired
    private ServicioRestaurantes miServicioRestaurantes;
	
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listarRestaurantes(){	
		
		List<Restaurante> restaurantes = miServicioRestaurantes.listarRestaurantes();
		
        if(restaurantes.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping(value = "buscar-por-id/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable("id") Long id) {
		Restaurante restaurante =  miServicioRestaurantes.buscarRestaurantePorId(id);
        if (null == restaurante){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);
    }
	
}
