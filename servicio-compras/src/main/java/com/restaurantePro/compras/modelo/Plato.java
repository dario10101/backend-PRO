package com.restaurantePro.compras.modelo;

import lombok.Data;

@Data
public class Plato {
	private Long atrIdPlato;
	private String atrNombre;
	private String atrDescripcion;
	private Double atrPrecio;
	private String atrUrlImagen;
	private String atrCategoria;
	private String atrEstado;
	private Double atrCantidad;
	private String atrNitRestaurante;

}
