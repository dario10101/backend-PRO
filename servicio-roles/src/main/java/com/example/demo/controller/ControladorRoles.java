package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.service.ServicioRoles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servicio REST que expone los servicios de roles, que sirven para el login de usuarios, sean clientes, empleados 
 * u otros stakeholders que se requieran en un futuro
 * 
 * @author Ruben
 *
 */
@RestController
@RequestMapping (value = "/roles")
public class ControladorRoles {
	
	/**
	 * Referencia al servicio de roles que vamos a utilizar 
	 */
	@Autowired
    private ServicioRoles miServicioRoles;
	
	/**
	 * Listar todos los roles disponibles
	 * @return Lista de roles
	 * @deprecated
	 */
	@GetMapping
	public ResponseEntity<List<Rol>> listarRoles(){		
		List<Rol> roles = miServicioRoles.listarRoles();
		
        if(roles.size() <= 0){
            return ResponseEntity.noContent().build();
        }        

        return ResponseEntity.ok(roles);
	}
			
	/**
	 * Obtener informacion de un unuario, dados sus datos de correo y contraseña, 
	 * que puede ser empleado o cliente
	 * @param user Datos de credenciales
	 * @param result
	 * @return Datos del usuario, sea cliente o empleado, solo si las credenciales son correctar
	 */
	/* Ejemplo
	{
	    "correo": "nacho@unicauca.edu.co",
	    "password": "123"
	}
	*/
	@PostMapping
	public ResponseEntity<Usuario> obtenerRol(@RequestBody Usuario user, BindingResult result){		
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
		Usuario rol_usuario_creado = miServicioRoles.validarRol(user);        
        
        if (rol_usuario_creado == null){
            return ResponseEntity.unprocessableEntity().build();
        }        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(rol_usuario_creado);        
    }
	
	/**
	 * Buscar un rol por su id
	 * @param idRol Identificador del rol
	 * 
	 * @return Rol encontrado
	 */
	@GetMapping(value = "buscar-por-id/{idrol}")
    public ResponseEntity<Rol> buscarRolPorId(@PathVariable("idrol") Long idRol) {
		System.out.println("Buscando restaurante ...");
		Rol rol =  miServicioRoles.buscarRolPorId(idRol);
        if (null == rol){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
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
