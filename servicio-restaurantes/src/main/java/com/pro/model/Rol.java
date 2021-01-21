package com.pro.model;

import lombok.Builder;
import lombok.Data;

/**
 * Clase que representa un rol, del servici ode roles
 * @author Ruben
 *
 */
@Data
@Builder
public class Rol {
	
	
	private Long idRol;
	
	private String nombreRol;
	
}
