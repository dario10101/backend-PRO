package com.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.entity.Plato;
//import com.pro.entity.Restaurante;
import com.pro.service.ServicioPlatos;

@RestController
@RequestMapping (value = "/platos")
public class ControladorPlatos {
	
	
	@Autowired
    private ServicioPlatos miServicioPlatos;
	
	
	
	@GetMapping
	public ResponseEntity<List<Plato>> listarPlatos(){		
		List<Plato> platos = miServicioPlatos.listarPlatos();
		
        if(platos.size() <= 0){
            return ResponseEntity.noContent().build();
        }        

        return ResponseEntity.ok(platos);
	}
	
	
	
	@GetMapping(value = "buscar-por-restaurante/{idrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorRestaurante(@PathVariable("idrest") Long idRest) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorRestaurante(idRest);
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }
	
	
	
	@GetMapping(value = "buscar-por-id/{idplato}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("idplato") Long idPlato) {
        Plato plato =  miServicioPlatos.buscarPlatoPorId(idPlato);
        if (null==plato){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	

	 /* Ejemplo:
	  
	 	http://localhost:8080/platos/buscar-por-nombre/1
	 	body:
	 	{
    		"nombrePlato": "Carne asada"
	 	}	  
	 */
	@PostMapping(value = "buscar-por-nombre/{idrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorNombre(@RequestBody Plato plato, @PathVariable("idrest") Long idRest, BindingResult result) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorNombre(idRest, plato.getNombrePlato());
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }
	
	
	
	/* Ejemplo:
	  
 	http://localhost:8080/platos/buscar-por-status/1
 	body:
 	{
		"statusPlato": "ACTIVATED"
 	}	  
	*/
	@PostMapping(value = "buscar-por-status/{idrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorStatus(@RequestBody Plato plato, @PathVariable("idrest") Long idRest, BindingResult result) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorStatus(idRest, plato.getStatusPlato());
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }
		
	
	
	/* Ejemplo:
	  
 	http://localhost:8080/platos/buscar-por-categoria/1
 	body:
 	{
		"categoriaPlato": "plato-especial"
 	}	  
    */
	@PostMapping(value = "buscar-por-categoria/{idrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorCategoria(@RequestBody Plato plato, @PathVariable("idrest") Long idRest, BindingResult result) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorCategoria(idRest, plato.getCategoriaPlato());
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }  
	
	
	
	/* Ejemplo:
	  
 	http://localhost:8080/platos/crear-plato
 	body:
	{
        "nombrePlato": "arroz con pollo",
        "descPlato": "descripcion",
        "precioPlato": 20000.0,
        "imgPlato": "vacio",
        "categoriaPlato": "plato-especial",
        "statusPlato": "ACTIVATED",
        "cantidadPlato": 11.0,
        "ingredientesPlato": "vacio",
        "idRest": 1
	}
	*/
	@PostMapping(value = "crear-plato")
	public ResponseEntity<Plato> crearPlato(@Valid @RequestBody Plato plato, BindingResult result){		
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
        Plato plato_creado =  miServicioPlatos.crearPlato(plato);        
        
        if (plato_creado == null){
            return ResponseEntity.unprocessableEntity().build();
        }        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(plato_creado);        
    }
	
	/* EJEMPLO
	http://localhost:8080/platos/actualizar-plato/32 
	 
	{
	    "nombrePlato": "arroz con pollo",
	    "descPlato": "Arroz roa",
	    "precioPlato": 20000.0,
	    "imgPlato": "vacio",
	    "categoriaPlato": "plato-especial",
	    "statusPlato": "ACTIVATED",
	    "cantidadPlato": 11.0,
	    "ingredientesPlato": "vacio",
	    "idRest": 1
	}
	*/
	@PutMapping(value = "/actualizar-plato/{id}")
    public ResponseEntity<Plato> actualizarPlato(@PathVariable("id") Long id, @Valid @RequestBody Plato plato){
        plato.setIdPlato(id);
        Plato plato_encontrado =  miServicioPlatos.actualizarPlato(plato);
        if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato_encontrado);
    }
	
	
	
	@DeleteMapping(value = "/eliminar-plato/{id}")
    public ResponseEntity<Plato> eliminarPlato(@PathVariable("id") Long id){
		Plato plato_encontrado = miServicioPlatos.eliminarPlato(id);
        if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato_encontrado);
    }
	
	
	
	@PutMapping (value = "/activar-plato/{id}")
    public ResponseEntity<Plato> activarPlato(@PathVariable  Long id){
        Plato plato = miServicioPlatos.activarPlato(id);
        if (plato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	
	/* Ejemplo
	http://localhost:8080/platos/actualizar-cantidad/31?cantidad=-1 
	
	resta 1 unidad al stock, al plato con id 31
	*/
	@PutMapping (value = "/actualizar-cantidad/{id}")
    public ResponseEntity<Plato> actualizarCantidadPlato(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad){
        Plato plato = miServicioPlatos.actualizarStock(id, cantidad);
        if (plato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	
	
	private String formatMessage( BindingResult result){
		List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
	
	
	/* test
    {	    
	    "nombrePlato": "afrroz con pollo",
	    "descPlato": "arroz, pollo, salchicha",
	    "imgPlato": "sin imagen",
	    "categoriaPlato": "especial",
	    "statusPlato": "ACTIVATED",
	    "cantidadPlato": 12,
	    "idRest": 1 
	}
    */
}	
