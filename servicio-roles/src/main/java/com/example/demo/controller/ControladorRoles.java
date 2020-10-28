package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping (value = "/roles")
public class ControladorRoles {
	
	@Autowired
    private ServicioRoles miServicioRoles;
	
	@GetMapping
	public ResponseEntity<List<Rol>> listarRoles(){		
		List<Rol> roles = miServicioRoles.listarRoles();
		
        if(roles.size() <= 0){
            return ResponseEntity.noContent().build();
        }        

        return ResponseEntity.ok(roles);
	}
	
	
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
