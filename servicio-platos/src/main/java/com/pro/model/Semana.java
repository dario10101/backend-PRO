package com.pro.model;

import com.pro.entity.Plato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Semana {

	/**
	 * Indican si un plato de va a ofrecer en el dia especifico 
	 * EJ si se ofrece el martes, se coloca en el atributo martes true
	 */
	
	private boolean lunes;
	private boolean martes;
	private boolean miercoles;
	private boolean jueves;
	private boolean viernes;
	private boolean sabado;
	private boolean domingo;
}
