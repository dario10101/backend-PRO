package com.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.entity.Restaurante;
import com.pro.repository.RepositorioRestaurantes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioRestaurantesImpl implements ServicioRestaurantes{
	
	private final RepositorioRestaurantes miRepositorioRestaurantes;

	@Override
	public List<Restaurante> listarRestaurantes() {
		List<Restaurante> restaurantePrueba = miRepositorioRestaurantes.findAll();		
		
		return restaurantePrueba;
	}

	@Override
	public Restaurante buscarRestaurantePorId(Long idRestaurante) {
		return miRepositorioRestaurantes.findById(idRestaurante).orElse(null);
	}

}
