package com.pro.model;

import lombok.Builder;
import lombok.Data;


/**
 * 
 * Clase que recive los datos del restaurante, proveniente de otro microservicio
 * 
 * @author Ruben
 *
 */
@Data
@Builder
public class Restaurante {
	
	/**
	 * Identificador unico del restaurante (nit)
	 */
    private String nitRest;
    
    /**
     * Nombre del restaurante
     */
    private String nombreRest;
    
    /**
     * Descripcion del restaurante
     */
    private String descRest;
    
    /**
     * Imagen del restaurante, puede almacenar la imagen codificada en un arreglo de bits o una URL
     */
    private String imgRest;
    
    /**
     * Categoria del restaurante
     */
    private String categoriaRest;
    
    /**
     * Indica si está activo o no en el sistema (ACTIVATED o DELETED)
     */
    private String statusRest;
}
