package com.restaurantePro.compras.modelo;

import lombok.Data;

@Data
public class Plato {
	/*private Long atrIdPlato;
	private String atrNombre;
	private String atrDescripcion;
	private Double atrPrecio;
	private String atrUrlImagen;
	private String atrCategoria;
	private String atrEstado;
	private Double atrCantidad;
	private String atrNitRestaurante;*/

    private Long idPlato;    
    private String nombrePlato;
    private String descPlato;
    private Double precioPlato; 
    private String imgPlato;    
    private String categoriaPlato;  
    private String statusPlato;
    private Double cantidadPlato;
    private String nitRest;
    
}
