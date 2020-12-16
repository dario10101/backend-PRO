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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que indica como se guardan los datos del semanario, los dias que se ofrece un plato, en la base de datos
 * 
 * @author Ruben
 *
 */
@Entity
@Table (name = "Semanario")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Semanario {
	
	@Id
    @Column(name = "id_semanario")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idSemanario;    
	
	@NotNull(message = "El Plato no puede ser vacio")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_plato")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Plato plato;
	
	@Column(name = "nit_rest")
    private String nitRest;
	
	
	/**
	 * Dias que se ofrece el plato
	 */
	@Column(name = "dias")
    private String dias;
	
}
