package com.pro.service;

import java.util.List;

import com.pro.entity.Restaurante;


public interface ServicioRestaurantes {

	public List<Restaurante> listarRestaurantes();
    public Restaurante buscarRestaurantePorNit(String nit); 
    
    public List<Restaurante> buscarRestaurantePorNombre(String nombreRest);
    public List<Restaurante> buscarRestaurantePorStatus(String status);
    public Restaurante crearRestaurante(Restaurante rest);
    public Restaurante actualizarRestaurante(Restaurante rest);
    
    public Restaurante eliminarRestaurante(String nitRestaurante);
    public Restaurante activarRestaurante(String nitRestaurante);
    
	
	
}
