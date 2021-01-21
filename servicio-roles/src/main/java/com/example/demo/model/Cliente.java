package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Representa los datos de uncliente, que se reciven desde el microservicoo de clientes. 
 * No tiene funcionalidad especifica, funciona solo con los getters y setters de sus parametros
 * 
 * @author Ruben
 *
 */
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {	
	
	/**
	 * Identificaion del cliente
	 */
	private Long idCliente;    
    
    private String nombresCliente;
    
    /**
     * Opcional
     * @
     */
    private String apellidosCliente;
    
    private String correoCliente;
    
    private String passwordCliente;
    
    /**
     * Opcional
     */
    private String telefonoCliente;
    
    /**
     * Opcional
     */
    private String celurarCliente;
    
    /**
     * ACTIVATED o DELETED
     */
    private String statusCliente;
    
    /**
     * Imagen codificada o una URL+
     * opcional
     */
    private String imgCliente;
	
	
}
