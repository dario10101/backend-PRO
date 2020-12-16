package com.pro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import com.pro.model.Semana;
import com.pro.service.ServicioPlatos;


/**
 * Esta clase expone los servicios de platos para ser consumidos por otras aplicaciones
 * 
 * @author Ruben
 *
 */
@RestController
@RequestMapping (value = "/platos")
public class ControladorPlatos {
	
	
	/**
	 * Referencia a los servicios de platos, utilizando inversion de dependencias, automatizado por el framework
	 */
	@Autowired
    private ServicioPlatos miServicioPlatos;
	
	
	/**
	 * Listar todos lo platos
	 * @return platos encontrados
	 */
	@GetMapping
	public ResponseEntity<List<Plato>> listarPlatos(){		
		List<Plato> platos = miServicioPlatos.listarPlatos();
		
        if(platos.size() <= 0){
            return ResponseEntity.noContent().build();
        }        
        
        return ResponseEntity.ok(platos);
	}
	
	/**
	 * Buscar todos los platos de un restaurante
	 * @param nit identificador del restaurante
	 * @return Todos los platos del restaurante
	 */
	@GetMapping(value = "buscar-por-restaurante/{nitrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorRestaurante(@PathVariable("nitrest") String nit) {
        List<Plato> platos =  miServicioPlatos.buscarPlatoPorRestaurante(nit);
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }
	
	/**
	 * Busca un plato por su identificador
	 * @param idPlato identificador del plato
	 * @return Plato encontrado
	 */
	@GetMapping(value = "buscar-por-id/{idplato}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("idplato") Long idPlato) {
        Plato plato =  miServicioPlatos.buscarPlatoPorId(idPlato);
        if (null==plato){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
		

	 
	/**
	 * Todos los platos con un mismo nombre de un restaurante
	 * @param plato Plato enviado, de ahi se saca el nombre
	 * @param nit Identificadore del restaurante
	 * @param result
	 * @return Platos encontrados
	*/
	/* 
	Ejemplo:
	  
 	http://localhost:8080/platos/buscar-por-nombre/1
 	body:
 	{
		"nombrePlato": "Carne asada"
 	}	  
	*/
	@PostMapping(value = "buscar-por-nombre/{nitrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorNombre(@RequestBody Plato plato, @PathVariable("nitrest") String nit, BindingResult result) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorNombre(nit, plato.getNombrePlato());
        
        if (platos == null){
            return ResponseEntity.notFound().build();
        }
        
        if (platos.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(platos);
    }
		
	
	/* Ejemplo:
	  
 	http://localhost:8080/platos/buscar-por-status/1/ACTIVATED	  
	*/
	/**
	 * Todos los platos con un mismo status, de un restaurante en especifico
	 * @param nit Identificadore del restaurante
	 * @param status (ACTIVATED o DELETED)
	 * @return
	 */
	@GetMapping(value = "buscar-por-status/{nitrest}/{status}")
    public ResponseEntity<List<Plato>> buscarPlatoPorStatus(@PathVariable("nitrest") String nit, @PathVariable("status") String status) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorStatus(nit, status);
        
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
	/**
	 * Todos los platos con una misma categoria de un restaurante 
	 * @param plato Clase plato, de donde se saca la categoria
	 * @param nit Identificador del restaurante 
	 * @param result
	 * @return Platos encontrados
	 */
	@PostMapping(value = "buscar-por-categoria/{nitrest}")
    public ResponseEntity<List<Plato>> buscarPlatoPorCategoria(@RequestBody Plato plato, @PathVariable("nitrest") String nit, BindingResult result) {
		List<Plato> platos =  miServicioPlatos.buscarPlatoPorCategoria(nit, plato.getCategoriaPlato());
        
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
	    "nitRest": 1
	}
	*/
	/**
	 * Crear nuevo plato
	 * @param plato Plato nuevo
	 * @param result
	 * @return Plato creado
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
	/**
	 * Actualizar un plato
	 * @param id id del plato que se quiere actualizar
	 * @param plato nuevos datos del plato
	 * @return Plato actualizado
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
		
	/**
	 * Cambiar estado de un plato de activo a eliminado
	 * @param id Identificador del plato
	 * @return Plato encontrado
	 */
	@DeleteMapping(value = "/eliminar-plato/{id}")
    public ResponseEntity<Plato> eliminarPlato(@PathVariable("id") Long id){
		Plato plato_encontrado = miServicioPlatos.eliminarPlato(id);
        if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato_encontrado);
    }
		
	/**
	 * Cambiar estado de un plato de eliminado a activo
	 * @param id
	 * @return
	 */
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
	/**
	 * Actualizar el stock de un plato
	 * @param id Identificador del plato
	 * @param cantidad nueva cantidad
	 * @return Plato encontrado
	 */
	@PutMapping (value = "/actualizar-cantidad/{id}")
    public ResponseEntity<Plato> actualizarCantidadPlato(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad){
        Plato plato = miServicioPlatos.actualizarStock(id, cantidad);
        if (plato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plato);
    }
		
	/**
	 * Buscar las categorias de un restaurante
	 * @param nit Identificador del restaurante
	 * @return Categorias disponibles
	 */
	@GetMapping(value = "buscar-categorias/{nitrest}")
    public ResponseEntity<List<String>> buscarCategoriasPorRestaurante(@PathVariable("nitrest") String nit) {
        List<String> categorias =  miServicioPlatos.buscarCategoriasPorRestaurante(nit);
        
        if (categorias == null){
            return ResponseEntity.notFound().build();
        }
        
        if (categorias.size() <= 0){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(categorias);
    }
	
	
	//----------------------------------------------------------------------------------------------
	//---------- METODOS DEL SEMANARIO--------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
	
	/**
	 * BUscar los platos que se ofrecen en un dia especifico en un restaurante
	 * @param nit Identificador del restaurante
	 * @param dia Dia solicitado, si no se pasa, toma el dia actual del sistema
	 * @param categoria no obligatorio, si no se pasa, busca en todas las categorias
	 * @return Lista de platos del dia
	 */
	// http://localhost:8091/platos/semanario/buscar-por-dia/1?dia=1&categoria=principio
	@GetMapping(value = "/semanario/buscar-por-dia/{nitrest}")
	public ResponseEntity<List<Plato>> buscarPlatosPorDia(@PathVariable("nitrest") String nit, 
														  @RequestParam(name = "dia", required = false) String  dia,
														  @RequestParam(name = "categoria", required = false) String  categoria){
		
		//si dia es nulo, devuelve del dia de hoy
		//si categiria es nulo, devuelve todas las categorias
		
		List<Plato> platos = miServicioPlatos.buscarPlatosPorDia(nit, dia, categoria);
		
		System.out.println("\n" + platos + "\n");
		
		//No se encontraron platos
		if(platos == null){
            return ResponseEntity.noContent().build();
        } 		
		if(platos.size() <= 0){
            return ResponseEntity.noContent().build();
        } 
		
				
		return ResponseEntity.ok(platos);
	}
	
	/**
	 * BUscar los dias en que se ofrece un plato
	 * @param idPlato identificador del plato
	 * @return Dias en que se ofrece (1-7)
	 */
	// http://localhost:8091/platos/semanario/buscar-por-plato/1
	@GetMapping(value = "/semanario/buscar-por-plato/{idplato}")
	public ResponseEntity<List<String>> buscarSemanarioPlato(@PathVariable("idplato") Long idPlato){
				
		//id de plato no valido
		if(idPlato == null){
            return ResponseEntity.noContent().build();
        } 
				
		String dias = this.miServicioPlatos.buscarSemanarioPlato(idPlato);
		
		//para devolver como una lista
		String[] vectorDias = dias.split(",");
		List<String> arregloDias = new ArrayList<String>();
		for(int i = 0; i < vectorDias.length; i++) {
			arregloDias.add(vectorDias[i]);
		}
		
		return ResponseEntity.ok(arregloDias);
		//return ResponseEntity.ok(dias);
	}
		
	
	// http://localhost:8091/platos/semanario/modificar/11
	/*
	{
	    "lunes": true,
	    "martes": true,
	    "miercoles": false,
	    "jueves": false,
	    "viernes": true,
	    "sabado": true,
	    "domingo": false
	}
	*/
	/**
	 * Modificar los dias en que se ofrece un plato
	 * @param idPlato Identificador del plato
	 * @param semana Contiene los dias en que se ofrece y los dias en que nó
	 * @param result
	 * @return Plato encontrado
	 */
	@PostMapping(value = "/semanario/modificar/{idplato}")
	public ResponseEntity<Plato> modificarSemanario(@PathVariable("idplato") Long idPlato, 
													@Valid @RequestBody Semana semana, BindingResult result){
		
		//PARAMETROS:
		//reiniciar por defecto queda en 1
		//separador por defecto: "," 
		
		
		
		//validar parametro "reiniciar"
		boolean reiniciarDias = true;		
		
		String separador = ",";		
		Plato plato_encontrado = null;		
		if(semana == null) {
			return ResponseEntity.unprocessableEntity().build(); 
		}	
		
		String dias = "";
		dias = configurarDias(semana);
		
		plato_encontrado = this.miServicioPlatos.agregarPlatoSemanario(idPlato, dias, separador, reiniciarDias);
				
		
		//id plato incorrecto
		if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
		
		return ResponseEntity.status(HttpStatus.CREATED).body(plato_encontrado);
	}

	/**
	 * Eliminar todos los registros de semanario de un plato
	 * @param idPlato Identificador del plato
	 * @return Plato encontrado
	 */
	@DeleteMapping(value = "/semanario/eliminar/{idplato}")
	public ResponseEntity<Plato> eliminarPlatoSemanario(@PathVariable("idplato") Long idPlato){
		if(idPlato != null) {
			Plato plato_encontrado = this.miServicioPlatos.eliminarPlatoSemanario(idPlato);
			
			if(plato_encontrado == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(plato_encontrado);
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------
	private String configurarDias(Semana semana) {
		String dias = "";
		
		if(semana.isDomingo()) 		{	dias += "1,";	}
		if(semana.isLunes()) 		{	dias += "2,";	}
		if(semana.isMartes()) 		{	dias += "3,";	}
		if(semana.isMiercoles()) 	{	dias += "4,";	}
		if(semana.isJueves()) 		{	dias += "5,";	}
		if(semana.isViernes()) 		{	dias += "6,";	}
		if(semana.isSabado()) 		{	dias += "7,";	}
		
		return dias;
	}
		
	//----------------------------------------------------------------------------------------------	
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


	/*
	// http://localhost:8091/platos/semanario/modificar/1?dias=1,2,3
	@PutMapping(value = "/semanario/modificar/{idplato}")
	public ResponseEntity<Plato> modificarSemanario(@PathVariable("idplato") Long idPlato, 
														@RequestParam(name = "dias", required = false) String dias,
														@RequestParam(name = "reiniciar", required = false) String reiniciar){
		
		//PARAMETROS:
		//reiniciar por defecto queda en 1
		//si el parametro dias es (nulo, vacio o cero) y reiniciar es 1, se elimina ese plato de todos los dias del semanario
		//separador por defecto: ","
		//si reiniciar es 1, borra lo que tenga asignado y asigna los dias especificados en "dias"
		//si reiniciar es 0, solo agrega los dias nuevos especificados en "dias" 
		
		//validar parametro "reiniciar"
		boolean reiniciarDias = true;
		if(reiniciar != null) {
			if(reiniciar.equals("0")) {
				reiniciarDias = false;
			}
			else if(reiniciar.equals("1")) {
				reiniciarDias = true;
			}else {
				return ResponseEntity.badRequest().build();
			}
		}		
		
		String separador = ",";		
		Plato plato_encontrado = null;		
		if(dias == null) {
			dias = "0";  
		}
		
		//Si no se especifica el dia, y reiniciar es 1, se borra los datos del semanario de ese plato		
		if( (dias.equals("0") || dias.equals("") || dias.equals(",")) && reiniciarDias) { 
			plato_encontrado = this.miServicioPlatos.eliminarPlatoSemanario(idPlato);
		}
		else {
			plato_encontrado = this.miServicioPlatos.agregarPlatoSemanario(idPlato, dias, separador, reiniciarDias);
		}		
		
		//id plato incorrecto
		if (plato_encontrado == null){
            return ResponseEntity.notFound().build();
        }
		
		return ResponseEntity.status(HttpStatus.CREATED).body(plato_encontrado);
	}
	*/
	
	
	/*
	@GetMapping(value = "semanario")
	public ResponseEntity<List<Semanario>> listarSemanario(){		
		List<Semanario> sem = miServicioPlatos.listarSemanario();   
		
		//TODO Quitar esto
		for(Semanario s: sem) {
			s.setDias(s.getDias() + "   plato ---> "  + s.getPlato().getIdPlato().toString());
			
			s.setPlato(null);
		}
        
        return ResponseEntity.ok(sem);
	}
	*/
}	
