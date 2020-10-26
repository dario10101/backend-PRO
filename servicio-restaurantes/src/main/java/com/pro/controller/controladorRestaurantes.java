package com.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.entity.Empleado;
import com.pro.entity.Restaurante;
import com.pro.service.ServicioEmpleados;
import com.pro.service.ServicioRestaurantes;



@RestController
@RequestMapping (value = "/restaurantes")
public class controladorRestaurantes {

	@Autowired
    private ServicioRestaurantes miServicioRestaurantes;
	
	@Autowired
    private ServicioEmpleados miServicioEmpleados;
	
	
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
		System.out.println("\n\nBuscando restaurante ...\n\n");
		Restaurante restaurante =  miServicioRestaurantes.buscarRestaurantePorId(id);
        if (null == restaurante){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);
    }
	
	
	//------------------------EMPLEADOS ----------------------------------
	 
	
	@GetMapping(value = "listar-empleados/{idrest}")
	public ResponseEntity<List<Empleado>> listarEmpleados(@PathVariable("idrest") Long idRest){	
		
		List<Empleado> empleados = miServicioEmpleados.listarEmpleados(idRest);
		
        if(empleados.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empleados);
	}
	
	@PostMapping(value = "/validar-empleado")
    public ResponseEntity<Empleado> validarEmpleado(@RequestBody Empleado empleado) {
		Empleado empleado_encontrado =  miServicioEmpleados.validarCredenciales(empleado);
        if (empleado_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado_encontrado);
    }
	
}
