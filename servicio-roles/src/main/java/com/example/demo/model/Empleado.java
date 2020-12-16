package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Representa los datos de un empleado, que se reciven desde el microservicoo de restaurantes. 
 * No tiene funcionalidad especifica, funciona solo con los getters y setters de sus parametros
 * 
 * @author Ruben
 *
 */
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Empleado {
	/**
	 * Identificaion del empleado
	 */
	private Long idEmpleado;
    
    private String nombreEmpleado;
    
    private String correoEmpleado;
    
    private String passwordEmpleado;
    
    /**
     * Opcional
     */
    private String telefonoEmpleado;
    
    /**
     * Opcional
     */
    private String direccionEmpleado;
    
    /**
     * Imagen codificada o URL
     * Opcional
     */
    private String imgEmpleado;
    
    private Long IdRolEmpleado;
    
    /**
     * Auxiliar, para devolver el restaurante al que pertenece un empleado (en caso de que lo sea)
     * opcional
     */
    private String nitRestAux;
}
