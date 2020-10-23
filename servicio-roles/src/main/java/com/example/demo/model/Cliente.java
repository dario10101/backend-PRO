package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {	

	private Long idCliente;    
    
    private String nombresCliente;
    
    private String apellidosCliente;
    
    private String correoCliente;
    
    private String passwordCliente;
    
    private String telefonoCliente;
    
    private String statusCliente;
    
    private String imgCliente;
	
	
}
