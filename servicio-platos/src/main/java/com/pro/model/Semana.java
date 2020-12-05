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

	private boolean lunes;
	private boolean martes;
	private boolean miercoles;
	private boolean jueves;
	private boolean viernes;
	private boolean sabado;
	private boolean domingo;
}
