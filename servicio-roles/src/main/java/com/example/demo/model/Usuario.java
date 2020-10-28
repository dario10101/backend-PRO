package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Usuario {
	
	private Long idUsuario;
	
	private String correo;
	
	private String password;
	
	private Long idRol;
	
	private String nombreRol;
	
	//private Long idRestauranteAux;
}
