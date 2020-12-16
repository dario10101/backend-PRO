package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase o entidad que se mapea a la base de datos, contiene la informacion de los roles registrados para los restaurantes
 * 
 * @author Ruben
 *
 */

@Entity
@Table(name = "Rol")
@Data 
@AllArgsConstructor @NoArgsConstructor @Builder
public class Rol {
	
	/**
	 * Identificador del rol
	 */
	@Id
    @Column(name = "id_rol")
    private Long idRol;
	
	/**
	 * Nombre del rol
	 * EJemplo JEDE DE COCINA, ADMINISTRADOR
	 */
	@Column(name = "nombre_rol")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombreRol;

}
