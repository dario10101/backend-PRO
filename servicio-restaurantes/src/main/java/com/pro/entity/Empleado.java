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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado")
@Data 
@AllArgsConstructor @NoArgsConstructor @Builder
public class Empleado {

	@Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idEmpleado;
    
    @Column(name = "nombre_empleado")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombreEmpleado;
    
    @Column(name = "correo_empleado")
    @NotEmpty (message = "El correo no debe ser vacío")
    private String correoEmpleado;
    
    @Column(name = "password_empleado")
    @NotEmpty (message = "Error en la contraseña")
    private String passwordEmpleado;
    
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;
    
    @Column(name = "direccion_empleado")
    private String direccionEmpleado;
    
    @Column(name = "img_empleado")
    private String imgEmpleado;
    
    @Column(name = "status_empleado")
    private String statusEmpleado;
    
    @Column(name = "id_rol")
    private Long IdRolEmpleado;
    
    @NotNull(message = "El restaurante no puede ser vacio")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "nit_rest")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Restaurante restaurante;
	
    
    // alerta de machetazo
	//@Transient
	//private Long idRestauranteAux;
}
