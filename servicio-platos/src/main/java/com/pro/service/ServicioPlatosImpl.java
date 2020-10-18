package com.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.client.RestauranteClient;
import com.pro.entity.Plato;
import com.pro.model.Restaurante;
import com.pro.repository.RepositorioPlatos;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioPlatosImpl implements ServicioPlatos{	

	private final RepositorioPlatos miRepositorioPlatos;
	
	@Autowired
	RestauranteClient clienteRestaurante;
	
	@Override
	public List<Plato> listarPlatos() {
		List<Plato> platosPrueba = miRepositorioPlatos.findAll();		
		
		return platosPrueba;
	}

	@Override
	public Plato buscarPlatoPorId(Long idPlato) {		
		return miRepositorioPlatos.findById(idPlato).orElse(null);
	}
	
	@Override
	public List<Plato> buscarPlatoPorNombre(String nombrePlato) {		
		return miRepositorioPlatos.findByNombrePlato(nombrePlato);
	}
	
	@Override
	public List<Plato> buscarPlatoPorStatus(String statusPlato) {		
		return miRepositorioPlatos.findByStatusPlato(statusPlato);
	}

	@Override
	public List<Plato> buscarPlatoPorCategoria(String categoriaPlato){
		return miRepositorioPlatos.findByCategoriaPlato(categoriaPlato);
	}
	
	@Override
	public Plato crearPlato(Plato plato) {
		//construir plato valido
		this.validarPlato(plato);			
        		
		//verificar si existe
		Plato plato_encontrado = null;
		if(plato.getIdPlato() != null)
			plato_encontrado = buscarPlatoPorId(plato.getIdPlato());
		
		//verificar por nombre
		
		//el plato ya existe
		if (plato_encontrado != null){
			System.out.println("\nPlato exixtente\n");
            return null;
        }			
		
		//crear restaurante
		if(plato.getIdRest() != null) {
			plato.setRestaurante(clienteRestaurante.buscarRestaurantePorId(plato.getIdRest()).getBody());
		}
		
		
		
		//System.out.println("\n\nid del plato: " + plato.getIdPlato() + "\n\\n");
        return miRepositorioPlatos.save(plato);
	}

	@Override
	public Plato actualizarPlato(Plato plato) {
		Plato plato_encontrado = buscarPlatoPorId(plato.getIdPlato());
        if (null == plato_encontrado){
            return null;
        }
        plato_encontrado.setNombrePlato(plato.getNombrePlato());
        plato_encontrado.setDescPlato(plato.getDescPlato());
        plato_encontrado.setPrecioPlato(plato.getPrecioPlato());
        plato_encontrado.setImgPlato(plato.getImgPlato());
        plato_encontrado.setCategoriaPlato(plato.getCategoriaPlato());
        plato_encontrado.setStatusPlato(plato.getStatusPlato());
        plato_encontrado.setCantidadPlato(plato.getCantidadPlato());
        plato_encontrado.setRestaurante(plato.getRestaurante());
        
        return miRepositorioPlatos.save(plato_encontrado);
	}

	@Override
	public Plato eliminarPlato(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        plato_encontrado.setStatusPlato("DELETED");
        return miRepositorioPlatos.save(plato_encontrado);
	}
	
	@Override
	public Plato activarPlato(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        plato_encontrado.setStatusPlato("ACTIVATED");
        return miRepositorioPlatos.save(plato_encontrado);
	}

	
	@Override
	public List<Plato> buscarPlatoPorRestaurante(Restaurante restaurante) {
		// TODO return miRepositorioPlatos.findByRestaurante(restaurante);
		return null;
	}
	

	@Override
	public Plato actualizarStock(Long idPlato, Double cantidad) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        Double stock =  plato_encontrado.getCantidadPlato() + cantidad;
        plato_encontrado.setCantidadPlato(stock);
        return miRepositorioPlatos.save(plato_encontrado);
	}
	
	
	
	
	//TODO ¿este metodo debe ir aqui?
	private Plato validarPlato(Plato platoBase) {
		
		//validar status
		if (platoBase.getStatusPlato() == null) {
			platoBase.setStatusPlato("ACTIVATED");
		}
		if(!(platoBase.getStatusPlato().equals("ACTIVATED") || platoBase.getStatusPlato().equals("DELETED"))) {
			platoBase.setStatusPlato("ACTIVATED");
		}
		
		//validar cantidad
		Double cantidad = platoBase.getCantidadPlato();
		if(cantidad == null){
			platoBase.setCantidadPlato(0.0);
		}
		
		//validar precio
		if(platoBase.getPrecioPlato() == null) {
			platoBase.setPrecioPlato(0.0);
		}
				
		return platoBase;
	}
	
	
	
	
	
	
	
	
}
