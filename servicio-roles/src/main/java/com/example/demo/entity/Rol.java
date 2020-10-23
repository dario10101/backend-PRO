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

@Entity
@Table(name = "Rol")
@Data 
@AllArgsConstructor @NoArgsConstructor @Builder
public class Rol {
	
	@Id
    @Column(name = "id_rol")
    //private Long id;
    private Long idRol;
	
	@Column(name = "nombre_rol")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombreRol;

}
