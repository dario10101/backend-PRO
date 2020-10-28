package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Cliente")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {
	
	@Id
    @Column(name = "id_cliente")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idCliente;    
    
    @Column(name = "nombres_cliente")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombresCliente;
    
    
    @Column(name = "apellidos_cliente")
    //@NotEmpty (message = "El apellido no debe ser vacío")
    private String apellidosCliente;
    
    
    @Column(name = "correo_cliente")
    @NotEmpty (message = "El correo no debe ser vacío")
    private String correoCliente;
    
    @Column(name = "password_cliente")
    @NotEmpty (message = "Error en la contraseña")
    private String passwordCliente;
    
    @Column(name = "telefono_cliente")
    private String telefonoCliente;
    
    @Column(name = "celular_cliente")
    private String celularCliente;
    
    @Column(name = "status_cliente")
    private String statusCliente;
    
    @Column(name = "img_cliente")
    private String imgCliente; 
    
    @Column(name = "direccion_cliente")
    @NotEmpty (message = "Error en la direccion")
    private String direccionCliente;
    
    //direccion
    //Pendiente fecha registro
    //pendiente edad
    
    
}
