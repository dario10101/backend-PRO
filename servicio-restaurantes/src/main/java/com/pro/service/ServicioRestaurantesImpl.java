package com.pro.service;

import java.util.ArrayList;
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
		System.out.println("\n\nBuscando restaurante " + idRestaurante + "\n\n");
		return miRepositorioRestaurantes.findById(idRestaurante).orElse(null);
	}

	@Override
	public List<Restaurante> buscarRestaurantePorNombre(String nombreRest) {
		//Datos no validos
		if(nombreRest == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		List<Restaurante> restaurantes = miRepositorioRestaurantes.findByNombreRest(nombreRest);
		
		return restaurantes;
	}

	@Override
	public Restaurante crearRestaurante(Restaurante rest) {
		if(rest == null)
			return null;		
		
		//verificar si existe
		Restaurante restaurante_encontrado = null;
		if(rest.getIdRest() != null)
			restaurante_encontrado = buscarRestaurantePorId(rest.getIdRest());
				
		//ya existe
		if (restaurante_encontrado != null){
			System.out.println("\nRestaurante exixtente\n");
            return null;
        }
		
		if(rest.getStatusRest() == null) {
			rest.setStatusRest("ACTIVATED");
		}
		
		return miRepositorioRestaurantes.save(rest);
	}

	@Override
	public Restaurante actualizarRestaurante(Restaurante rest) {
		if(rest == null)
			return null;		
		Long idRestaurante = rest.getIdRest();
		
		// El cliente no tiene el id
		if(idRestaurante == null)
			return null;
		
		Restaurante restaurante_encontrado = buscarRestaurantePorId(idRestaurante);
        
		// NO existe el Restaurante
		if (restaurante_encontrado == null)
            return null;
		
		restaurante_encontrado.setNombreRest(rest.getNombreRest());
		restaurante_encontrado.setDescRest(rest.getDescRest());
		restaurante_encontrado.setTelefonoRest(rest.getTelefonoRest());
		restaurante_encontrado.setCategoriaRest(rest.getCategoriaRest());
		restaurante_encontrado.setStatusRest(rest.getStatusRest());
		restaurante_encontrado.setImgRest(rest.getImgRest()); 
		
		if(restaurante_encontrado.getStatusRest() == null) {
			restaurante_encontrado.setStatusRest("ACTIVATED");
		}
		
		return miRepositorioRestaurantes.save(restaurante_encontrado);
	}

}
