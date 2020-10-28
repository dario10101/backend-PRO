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
		List<Restaurante> restaurantesPrueba = miRepositorioRestaurantes.findAll();		
		
		this.eliminarPorStatus("DELETED", restaurantesPrueba);
		
		return restaurantesPrueba;
	}

	@Override
	public Restaurante buscarRestaurantePorNit(String nit) {
		System.out.println("\n\nBuscando restaurante " + nit + "\n\n");
		return miRepositorioRestaurantes.findById(nit).orElse(null);
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
		if(rest.getNitRest() != null) {
			restaurante_encontrado = buscarRestaurantePorNit(rest.getNitRest());
		}else {
			// id nulo
			return null;
		}
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
		String nitRestaurante = rest.getNitRest();
		
		// El cliente no tiene el id
		if(nitRestaurante == null)
			return null;
		
		Restaurante restaurante_encontrado = buscarRestaurantePorNit(nitRestaurante);
        
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

	@Override
	public Restaurante eliminarRestaurante(String nitRestaurante) {
		Restaurante restaurante_encontrado = buscarRestaurantePorNit(nitRestaurante);
        if (null == restaurante_encontrado){
            return null;
        }
        
        restaurante_encontrado.setStatusRest("DELETED");
        return miRepositorioRestaurantes.save(restaurante_encontrado);
	}

	@Override
	public Restaurante activarRestaurante(String nitRestaurante) {
		Restaurante restaurante_encontrado = buscarRestaurantePorNit(nitRestaurante);
        if (null == restaurante_encontrado){
            return null;
        }
        
        restaurante_encontrado.setStatusRest("ACTIVATED");
        return miRepositorioRestaurantes.save(restaurante_encontrado);
	}
	
	
	
	
	
	private void eliminarPorStatus(String status, List<Restaurante> restaurantes){
		if(restaurantes != null) {
			for(int i = 0; i < restaurantes.size(); i++) {
				if(restaurantes.get(i).getStatusRest().equals(status)) {
					restaurantes.remove(i);
				}
			}
		}
	}

}
