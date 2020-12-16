package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

import com.pro.model.Restaurante;
import com.pro.model.Semana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad que representa un plato, define como se va a guardar en la base de datos  
 *   
 * @author Ruben 
 * 
 */

@Entity
@Table (name = "Plato")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Plato {	
	
	/**
	 * Identificador de un plato, autoincremental
	 */
    @Id
    @Column(name = "id_plato")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idPlato;    
    
    /**
     * NOmbre que va a tener un plato, no se puede repetir dentro de un mismo restaurante
     */
    @Column(name = "nombre_plato")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombrePlato;
    
    /**
     * Descripcion breve del plato
     */
    @Column(name = "desc_plato")
    private String descPlato;
     
    /**
     * Precio de un plato, mayor o igual a cero
     */
    @Column(name = "precio_plato")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precioPlato; 
    
    /**
     * Imagen del plato, puede almacenar la imagen codificada en un arreglo de bits o una URL
     */
    @Column(name = "img_plato")
    private String imgPlato;    
    
    /**
     * Categoria del plato (principio, ensalada, etc)
     */
    @Column(name = "categoria_plato")
    private String categoriaPlato;  
    
    /**
     * Indica si está activo o no en el sistema (ACTIVATED o DELETED)
     */
    @Column(name = "status_plato")
    private String statusPlato;
    
    /**
     * Cantidad disponible del plato 
     */
    @Column(name = "cantidad_plato")
    private Double cantidadPlato;
    
    /*
    @Column(name = "ingredientes_plato")
    private String ingredientesPlato;
	*/
    
    /**
     * Nit del restaurante al que pertenece
     */
    @NotNull(message = "El nit del restaurante no puede ser nulo")
    @Column(name = "nit_rest")
    private String nitRest;
    
    /**
     * Restaurante al que pertenece, no se almacena en la base de datos, solo el NIT
     */
    @Transient
    private Restaurante restaurante;
    
    /**
     * Indica los dias de la semana en que se ofrece el plato, no se almacena en la base de datos
     */
    @Transient
    private Semana semanario;
    
    /*
    //@NotNull(message = "El restaurante no puede ser vacio")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_rest")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Restaurante restaurante;	
    */
    
	
}