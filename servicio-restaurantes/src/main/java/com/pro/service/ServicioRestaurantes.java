package com.pro.service;

import java.util.List;
import com.pro.entity.Restaurante;


public interface ServicioRestaurantes {

	public List<Restaurante> listarRestaurantes();
    public Restaurante buscarRestaurantePorId(Long idRestaurante); 
	
	
}
