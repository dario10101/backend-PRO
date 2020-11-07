package com.example.demo.service;

public interface ServicioImagenes {

	public String agregarImagenPlato(Long idPlato, String imagenCodificada);
	public String obtenerImagenPlato(String idPlato);
	
	public String agregarImagenRestaurante(String nitRest, String imagenCodificada);	
	public String obtenerImagenRestaurante(String nitRest);
}
