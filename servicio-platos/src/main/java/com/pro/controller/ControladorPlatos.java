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
import com.pro.entity.Restaurante;
import com.pro.service.ServicioPlatos;

@RestController
@RequestMapping (value = "/platos")
public class ControladorPlatos {
	
	// Servicio encargado de gestionar los platos
	@Autowired	
    private ServicioPlatos miServicioPlatos;
    	
	//Lista todos los platos 
	@GetMapping
	public ResponseEntity<List<Plato>> listarPlatos(){	
		
		List<Plato> platos = miServicioPlatos.listarPlatos();
		
		// No hay platos
        if(platos.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(platos);
	}
	
	// Buscar plato por el id en la url
	@GetMapping(value = "buscar-por-id/{id}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("id") Long id) {
        Plato plato =  miServicioPlatos.buscarPlatoPorId(id);
        
        // No existe el plato
        if (null==plato){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	// Buscar plato por el nombre en la url
	@GetMapping(value = "buscar-por-nombre/{nombre}")
    public ResponseEntity<List<Plato>> buscarPlatoPorNombre(@PathVariable("nombre") String nombrePlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorNombre(nombrePlato);
        
        // No hay platos con ese nombre
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
	
	// Buscar los platos activos y eliminados (ACTIVATED o DELETED) en la url
	@GetMapping(value = "buscar-por-status/{status}")
    public ResponseEntity<List<Plato>> buscarPlatoPorStatus(@PathVariable("status") String statusPlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorStatus(statusPlato);
        
        // No hay platos con ese status
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
		
	// Buscar plato por la categoria en la url
	@GetMapping(value = "buscar-por-categoria/{categoria}")
    public ResponseEntity<List<Plato>> buscarPlatoPorCategoria(@PathVariable("categoria") String categoriaPlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorCategoria(categoriaPlato);
        
        // No hay platos con esa categoria
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
	
	// Buscar todos lo platos con el id de un restaurante
	@GetMapping(value = "/buscar-por-restaurante/{id}")
    public ResponseEntity<List<Plato>> buscarPlatosPorRestaurante(@PathVariable("id") Long id) {
		Restaurante restaurante  = Restaurante.builder().idRest(id).build();
		
		List<Plato> platos = miServicioPlatos.buscarPlatoPorRestaurante(restaurante);
		
		// No hay platos en el restaurante
		if(platos.size() <= 0){
            return ResponseEntity.noContent().build();
        }
		
        return ResponseEntity.ok(platos);
    }
	
	// Recive y guarda un nuevo plato, debe ser valido	
	@PostMapping
	public ResponseEntity<Plato> crearPlato(@Valid @RequestBody Plato plato, BindingResult result){
        //System.out.println("\nLlega, id: \n" + plato.getIdPlato() + " desc: " + plato.getDescPlato() + "\n\n");
		
		// La peticion o el plato no son validos
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
        //TODO enviar mensaje diferente si ya existe el plato
        		
        Plato plato_creado =  miServicioPlatos.crearPlato(plato);        
        return ResponseEntity.status(HttpStatus.CREATED).body(plato_creado);        
    }

	// Actualiza un plato
	@PutMapping(value = "/actualizar-plato/{id}")
    public ResponseEntity<Plato> actualizarPlato(@PathVariable("id") Long id, @RequestBody Plato plato){
        plato.setIdPlato(id);
        Plato plato_encontrado =  miServicioPlatos.actualizarPlato(plato);
        
        // El plato que se quiere actualizar no existe
        if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato_encontrado);
    }

	// Cambia el estado de un metodo a DELETED
	@DeleteMapping(value = "/eliminar-plato/{id}")
    public ResponseEntity<Plato> eliminarPlato(@PathVariable("id") Long id){
		Plato plato_encontrado = miServicioPlatos.eliminarPlato(id);
        
		// No existe el plato
		if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato_encontrado);
    }

	// Cambia el estado de un metodo a ACTIVATED
	@PutMapping (value = "/activar-plato/{id}")
    public ResponseEntity<Plato> activarPlato(@PathVariable  Long id){
        Plato plato = miServicioPlatos.activarPlato(id);
        
        // No existe el plato
        if (plato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	// Suma el valor en cantidad al atock del plato, si se desea restar, pasar un numero negativo
	@PutMapping (value = "/actualizar-cantidad/{id}")	
    public ResponseEntity<Plato> actualizarCantidadPlato(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad){
        Plato plato = miServicioPlatos.actualizarStock(id, cantidad);
        
        // No existe el plato
        if (plato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	
	
	//Construir mensaje de error
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
