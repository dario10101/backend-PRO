package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pro.model.Restaurante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Plato")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Plato {	
	
    @Id
    @Column(name = "id_plato")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idPlato;    
    
    @Column(name = "nombre_plato")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombrePlato;
    
    @Column(name = "desc_plato")
    private String descPlato;
     
    @Column(name = "precio_plato")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precioPlato; 
    
    @Column(name = "img_plato")
    private String imgPlato;    
    
    @Column(name = "categoria_plato")
    private String categoriaPlato;  
    
    @Column(name = "status_plato")
    private String statusPlato;
    
    @Column(name = "cantidad_plato")
    private Double cantidadPlato;
    
    @Column(name = "ingredientes_plato")
    private String ingredientesPlato;

    @NotNull(message = "El id del restaurante no puede ser nulo")
    @Column(name = "id_rest")
    private Long idRest;
    
    @Transient
    private Restaurante restaurante;
    
    /*
    //@NotNull(message = "El restaurante no puede ser vacio")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_rest")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Restaurante restaurante;	
    */
    
	
}