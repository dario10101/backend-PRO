package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Empleado {
	private Long idEmpleado;
    
    private String nombreEmpleado;
    
    private String correoEmpleado;
    
    private String passwordEmpleado;
    
    private String telefonoEmpleado;
    
    private String direccionEmpleado;
    
    private String imgEmpleado;
    
    private Long IdRolEmpleado;
    
    private String nitRestAux;
}
