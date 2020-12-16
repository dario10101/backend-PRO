package com.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.entity.Empleado;
import com.pro.entity.Restaurante;
import com.pro.service.ServicioEmpleados;
import com.pro.service.ServicioRestaurantes;


/**
 * Controlador REST que sirve como puerta de entrada a los servicios de los restaurantes
 * 
 * @author Ruben
 *
 */
@RestController
@RequestMapping (value = "/restaurantes")
public class controladorRestaurantes {
	
	/**
	 * Referencia a los servicio de restaurantes
	 */
	@Autowired
    private ServicioRestaurantes miServicioRestaurantes;
		
	/**
	 * Referencia a los servicio de elmpleados, usando inyeccion de dependencias
	 */
	@Autowired
    private ServicioEmpleados miServicioEmpleados;
	
	
	//------------------------RESTAURANTES -------------------------------
	//--------------------------------------------------------------------
	
	/**
	 * Listar todos los restaurantes
	 * @return Lista de restaurantes
	 */
	@GetMapping
	public ResponseEntity<List<Restaurante>> listarRestaurantes(){	
		
		List<Restaurante> restaurantes = miServicioRestaurantes.listarRestaurantes();
		
		if(restaurantes== null){
            return ResponseEntity.noContent().build();
        }		
		
        if(restaurantes.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(restaurantes);
	}
	
	/**
	 * Listar los restaurantes activos, osea, con el status en ACTIVATED
	 * @return Lista de restaurantes
	 */
	//http://localhost:8092/restaurantes/activos
	@GetMapping(value = "activos")
	public ResponseEntity<List<Restaurante>> listarRestaurantesActivos(){	
		
		List<Restaurante> restaurantes = miServicioRestaurantes.buscarRestaurantePorStatus("ACTIVATED");
		
		if(restaurantes== null){
            return ResponseEntity.noContent().build();
        }
		
        if(restaurantes.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(restaurantes);
	}	
	
	/**
	 * Listar los restaurantes inactivos, osea, con el status en DELETED
	 * @return Lista de restaurantes
	 */
	//http://localhost:8092/restaurantes/inactivos
	@GetMapping(value = "inactivos")
	public ResponseEntity<List<Restaurante>> listarRestaurantesInactivos(){	
		
		List<Restaurante> restaurantes = miServicioRestaurantes.buscarRestaurantePorStatus("DELETED");
		
		if(restaurantes == null){
            return ResponseEntity.noContent().build();
        }
		
        if(restaurantes.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(restaurantes);
	}	
	
	/**
	 * buSCAR UN RESTAURANTE POR SU IDENTIFICADOR, EL nit
	 * @param nit Identificador del restaurante
	 * @return Restaurante encontrado
	 */
	
	@GetMapping(value = "buscar-por-nit/{nit}")
    public ResponseEntity<Restaurante> buscarRestaurantePorNit(@PathVariable("nit") String nit) {
		System.out.println("Buscando restaurante ...");
		Restaurante restaurante =  miServicioRestaurantes.buscarRestaurantePorNit(nit);
        if (null == restaurante){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);
    }
	
	/**
	 * Buscar los restaurantes con el mismo nombre
	 * @param rest Debe contener el nombre del restaurante como atributo
	 * @param result
	 * @return Restaurantes encontrados
	 */
	
	/* Ejemplo:	  
 	http://localhost:8080/restaurantes/buscar-por-nombre
 	body:
 	{
		"nombreRest": "Pio Pio"
 	}	  
	*/
	@PostMapping(value = "buscar-por-nombre")
	public ResponseEntity<List<Restaurante>> buscarRestaurantePorNombre(@RequestBody Restaurante rest, BindingResult result) {
		//System.out.println("\nnombre: " + rest.getNombreRest() + "\n");
		
		List<Restaurante> restaurantes =  miServicioRestaurantes.buscarRestaurantePorNombre(rest.getNombreRest());
	    
	    if (restaurantes == null){
	        return ResponseEntity.notFound().build();
	    }
	    
	    if (restaurantes.size() <= 0){
	        return ResponseEntity.notFound().build();
	    }
	    
	    return ResponseEntity.ok(restaurantes);
	}
	
	/**
	 * Agregar un nuevo restaurante
	 * @param restaurante Restaurante nuevo
	 * @param result
	 * @return Restaurante creado, si fue exitoso el registro
	 */
	
	/* EJEMPLO
	{
	    "nitRest": "100",
	    "nombreRest": "La española",
	    "descRest": "Cajete",
	    "telefonoRest": "12345",
	    "categoriaRest": "Almuerzos caseros"
	}
	*/
	@PostMapping(value = "crear-restaurante")
	public ResponseEntity<Restaurante> crearRestaurante(@Valid @RequestBody Restaurante restaurante, BindingResult result){		
		if (result.hasErrors()){     
			System.out.println("\nTiene errores.\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
		Restaurante restaurante_creado =  miServicioRestaurantes.crearRestaurante(restaurante);        
        
        if (restaurante_creado == null){
            return ResponseEntity.unprocessableEntity().build();
        }        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante_creado);        
    }
	
	/**
	 * Actualizar la informacion de un restaurante
	 * @param nit Identificador del restaurante
	 * @param restaurante Restaurante con los datos a modificar
	 * @return Restaurante modificado
	 */
	
	/* Ejemplo:
	{
	    "nombreRest": "La española cajete",
	    "descRest": "Cajete",
	    "telefonoRest": "12345",
	    "categoriaRest": "Almuerzos caseros"
	}
	*/
	@PutMapping(value = "/actualizar-restaurante/{nit}")
    public ResponseEntity<Restaurante> actualizarRestaurante(@PathVariable("nit") String nit, @Valid @RequestBody Restaurante restaurante){
		if(restaurante.getNitRest() == null)
			restaurante.setNitRest(nit);
		
		Restaurante restaurante_encontrado =  miServicioRestaurantes.actualizarRestaurante(restaurante);
        if (restaurante_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante_encontrado);
    }
	
	/**
	 * Cambiar el estado de un restaurante a eliminado
	 * @param nit Identificador del restaurante
	 * @return Restaurante con su status modificado
	 */
	
	@DeleteMapping(value = "/eliminar-restaurante/{nit}")
    public ResponseEntity<Restaurante> eliminarRestaurante(@PathVariable("nit") String nit){
		Restaurante restaurante_encontrado = miServicioRestaurantes.eliminarRestaurante(nit);
        if (restaurante_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante_encontrado);
    }
	
	/**
	 * Cambiar el estado de un restaurante a activo
	 * @param nit Identificador del restaurante
	 * @return Restaurante con su status modificado
	 */
	
	@PutMapping(value = "/activar-restaurante/{nit}")
    public ResponseEntity<Restaurante> activarRestaurante(@PathVariable("nit") String nit){
		Restaurante restaurante_encontrado = miServicioRestaurantes.activarRestaurante(nit);
        if (restaurante_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante_encontrado);
    }
	
	
	
	
	//------------------------EMPLEADOS ----------------------------------
	//--------------------------------------------------------------------
	
	/**
	 * Listar todos los empleados de un restaurante
	 * @param nit Identificador del restaurante
	 * @return Lista de empleados encontrados
	 */
	
	@GetMapping(value = "listar-empleados/{nit}")
	public ResponseEntity<List<Empleado>> listarEmpleados(@PathVariable("nit") String nit){	
		
		List<Empleado> empleados = miServicioEmpleados.listarEmpleados(nit);
		
		if(empleados == null){
            return ResponseEntity.noContent().build();
        }
		
        if(empleados.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empleados);
	}
	
	/**
	 * Listar solo empleados activos
	 * @param nit Identificador del restaurante
	 * @return Empleados encontrados
	 */
	//http://localhost:8092/restaurantes/listar-empleados/activos/1
	@GetMapping(value = "listar-empleados/activos/{nit}")
	public ResponseEntity<List<Empleado>> listarEmpleadosActivos(@PathVariable("nit") String nit){	
		System.out.println("\n Hola... \n");
		List<Empleado> empleados = miServicioEmpleados.buscarPorStatus(nit, "ACTIVATED");
		
		if(empleados == null){
            return ResponseEntity.noContent().build();
        }
		
        if(empleados.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empleados);
	}
	
	/**
	 * Listar solo empleados inactivos
	 * @param nit Identificador del restaurante
	 * @return Empleados encontrados
	 */
	//http://localhost:8092/restaurantes/listar-empleados/inactivos/1
	@GetMapping(value = "listar-empleados/inactivos/{nit}")
	public ResponseEntity<List<Empleado>> listarEmpleadosInactivos(@PathVariable("nit") String nit){	
		System.out.println("\n Hola 2... \n");
		List<Empleado> empleados = miServicioEmpleados.buscarPorStatus(nit, "DELETED");
		
		if(empleados == null){
            return ResponseEntity.noContent().build();
        }
		
        if(empleados.size() <= 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empleados);
	}
	
	/**
	 * BUscar un empleado por su identificador
	 * @param id Identificacion del empleado
	 * @return Empleado encontrado
	 */
	@GetMapping(value = "/buscar-empleado-por-id/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable("id") Long id) {
		Empleado emp =  miServicioEmpleados.buscarEmpleadoPorId(id);
        if (null == emp){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }
	
	/**
	 * Buscar empleado por su correo
	 * @param correo Direccion de correo del empleado
	 * @return Empleado encontrado
	 */
	@GetMapping(value = "buscar-empleado-por-correo/{correo}")
    public ResponseEntity<Empleado> buscarEmpleadoPorCorreo(@PathVariable("correo") String correo) {
		System.out.println("Buscando restaurante ...");
		Empleado emp =  miServicioEmpleados.buscarEmpleadoPorCorreo(correo);
        if (null == emp){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }
	
	/**
	 * Validar informacion de acceso de un empleado
	 * @param empleado Debe contener los campor correo y contraseña
	 * @return Enpleado encontrado, solo si son validos correo y contraseña
	 */
	/* Ejemplo
	{
	    "correoEmpleado" : "ana@unicauca.edu.co",
	    "passwordEmpleado": "123"
	}
	*/
	@PostMapping(value = "/validar-empleado")
    public ResponseEntity<Empleado> validarEmpleado(@RequestBody Empleado empleado) {
		Empleado empleado_encontrado =  miServicioEmpleados.validarCredenciales(empleado);
        if (empleado_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado_encontrado);
    }
		
	/**
	 * Agregar un nuevo empleado a un restaurante	
	 * @param empleado Empleado nuevo
	 * @param result 
	 * @return Empleado creado, si se crea correctamente
	 */
	/* CREAR EMPLEADO, EJEMPLO:
	{
	    "nombreEmpleado": "Pepe",
	    "correoEmpleado": "pepe@unicauca.edu.co", 
	    "passwordEmpleado": "123",
	    "telefonoEmpleado": "12345",
	    "direccionEmpleado": "calle 1 carrera 1",
	    "imgEmpleado": "vacio",
	    "idRolEmpleado": "2",
	    "restaurante": 
	    {
	        "nitRest": 1
	    }
	}
	*/	
	@PostMapping(value = "/crear-empleado")
	public ResponseEntity<Empleado> crearEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result){		
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
		Empleado empleado_creado =  miServicioEmpleados.crearEmpleado(empleado);        
        
        if (empleado_creado == null){
            return ResponseEntity.unprocessableEntity().build();
        }        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(empleado_creado);        
    }
		
	/**
	 * Modificar informacion de un empleado
	 * @param id Identificacion del empleado
	 * @param empleado Datos a modificar del dempleado
	 * @return Empleado modificado
	 */
	/* Ejemplo
	{
	    "nombreEmpleado": "Nelson",
	    "correoEmpleado": "nelson@unicauca.edu.co", 
	    "passwordEmpleado": "123",
	    "telefonoEmpleado": "12345",
	    "direccionEmpleado": "calle 2 carrera 1",
	    "idRolEmpleado": "2",
	    "restaurante": 
	    {
	        "nitRest": 1
	    }
	}
	*/
	@PutMapping(value = "/actualizar-empleado/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") Long id, @Valid @RequestBody Empleado empleado){		
		empleado.setIdEmpleado(id);
		Empleado empleado_encontrado =  miServicioEmpleados.actualizarEmpleado(empleado);
        
		if (empleado_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado_encontrado);
    }
	
	/**
	 * Cambiar estado de un empleado a eliminado
	 * @param id Identificacion del empleado
	 * @return Empleado modificado
	 */
	@DeleteMapping(value = "/eliminar-empleado/{id}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable("id") Long id){
		Empleado empleado_encontrado = miServicioEmpleados.eliminarEmpleado(id);
        if (empleado_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado_encontrado);
    }
	
	/**
	 * Cambiar estado de un empleado a activo	
	 * @param id Identificacion del empleado
	 * @return Empleado modificado
	 */
	@PutMapping (value = "/activar-empleado/{id}")
    public ResponseEntity<Empleado> activarEmpleado(@PathVariable  Long id){
		Empleado empleado = miServicioEmpleados.activarEmpleado(id);
        if (empleado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
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
	
	
}
