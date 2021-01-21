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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ServicioClientes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Esta clase representa un controlador REST que consume los métodos 
 * expuestos en la fachada (Interface ServicioClientes)
 * @author Héctor Fabio Meneses
 *
 */
@RestController
@RequestMapping (value = "/clientes")
public class ControladorClientes {
	
	/**
	 * Atributo que permite acceder a los servicios expuestos en la fachada(Interface ServicioClientes),
	 * por medio de la inyección de dependencias 
	 */
	@Autowired
    private ServicioClientes miServicioClientes;
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){		
		List<Cliente> clientes = miServicioClientes.listarClientes();
		
        if(clientes.size() <= 0){
            return ResponseEntity.noContent().build();
        }        

        return ResponseEntity.ok(clientes);
	}
	
	
	/* EJEMPLO
	{
	    "correoCliente" : "ruben@unicauca.edu.co",
	    "passwordCliente": "123"
	}
	*/
	@PostMapping(value = "/validar-cliente")
    public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente) {
		Cliente cliente_encontrado =  miServicioClientes.validarCredenciales(cliente);
        if (cliente_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente_encontrado);
    }
		
	
	@GetMapping(value = "/buscar-por-id/{idcliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("idcliente") Long idCliente) {
		Cliente cliente =  miServicioClientes.buscarClientePorId(idCliente);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
	
	
	/* EJEMPLO
	{
	    "correoCliente" : "rupert@unicauca.edu.co",
	    "passwordCliente": "123",
	    "nombresCliente": "Rupert",
	    "apellidosCliente": "Apellido de Rupert",
	    "telefonoCliente": "123445",
	    "direccionCliente": "La Sierra Cauca"
	}
	*/
	@PostMapping(value = "/crear-cliente")
	public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result){		
		if (result.hasErrors()){     
			System.out.println("\n\nTiene errores.\n\n");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        
		Cliente cliente_creado =  miServicioClientes.crearCliente(cliente);        
        
        if (cliente_creado == null){
            return ResponseEntity.unprocessableEntity().build();
        }        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente_creado);        
    }
	
	
	/* EJEMPLO
	{
	    "correoCliente" : "rupert@unicauca.edu.co",
	    "passwordCliente": "123",
	    "nombresCliente": "Rupert",
	    "apellidosCliente": "Apellido de Rupert",
	    "telefonoCliente": "123445",
	    "statusCliente": "DELETED",
	    "direccionCliente": "La Sierra Cauca"
	}
	*/	
	@PutMapping(value = "/actualizar-cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente){
		cliente.setIdCliente(id);
		Cliente cliente_encontrado =  miServicioClientes.actualizarCliente(cliente);
        
		if (cliente_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente_encontrado);
    }
	
	
	@DeleteMapping(value = "/eliminar-cliente/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable("id") Long id){
		Cliente cliente_encontrado = miServicioClientes.eliminarCliente(id);
        if (cliente_encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente_encontrado);
    }
	
	
	@PutMapping (value = "/activar-cliente/{id}")
    public ResponseEntity<Cliente> activarCliente(@PathVariable  Long id){
		Cliente cliente = miServicioClientes.activarCliente(id);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
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
