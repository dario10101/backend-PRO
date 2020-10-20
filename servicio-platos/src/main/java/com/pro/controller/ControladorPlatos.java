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
import com.pro.client.RestauranteClient;
import com.pro.entity.Plato;
import com.pro.model.Restaurante;
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
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
	
	@GetMapping(value = "buscar-por-id/{idrest}/{idplato}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("idrest") Long idRest, @PathVariable("idplato") Long idPlato) {
        Plato plato =  miServicioPlatos.buscarPlatoPorId(idRest);
        if (null==plato){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
	
	@GetMapping(value = "buscar-por-nombre/{idrest}/{nombre}")
    public ResponseEntity<List<Plato>> buscarPlatoPorNombre(@PathVariable("idrest") Long idPlato, @PathVariable("nombre") String nombrePlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorNombre(nombrePlato);
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
	
	@GetMapping(value = "buscar-por-status/{idrest}/{status}")
    public ResponseEntity<List<Plato>> buscarPlatoPorStatus(@PathVariable("idrest") Long idRest, @PathVariable("status") String statusPlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorStatus(statusPlato);
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }
		
	@GetMapping(value = "buscar-por-categoria/{idrest}/{categoria}")
    public ResponseEntity<List<Plato>> buscarPlatoPorCategoria(@PathVariable("idrest") Long idRest, @PathVariable("categoria") String categoriaPlato) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorCategoria(categoriaPlato);
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(platos);
    }    
	
	@PostMapping
	public ResponseEntity<Plato> crearPlato(@Valid @RequestBody Plato plato, BindingResult result){
        //System.out.println("\nLlega, id: \n" + plato.getIdPlato() + " desc: " + plato.getDescPlato() + "\n\n");
		
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
        //TODO enviar mensaje diferente si ya existe el plato
        
        Plato plato_creado =  miServicioPlatos.crearPlato(plato);        
        return ResponseEntity.status(HttpStatus.CREATED).body(plato_creado);        
    }
	
	@PutMapping(value = "/actualizar-plato/{id}")
    public ResponseEntity<Plato> actualizarPlato(@PathVariable("id") Long id, @RequestBody Plato plato){
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
