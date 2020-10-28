package com.pro.service;

import java.util.ArrayList;
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
		
		//TODO optimizar
		Restaurante rest;
		String nitTemp;
		if(platosPrueba.size() > 0) {				
			for(Plato plato: platosPrueba) {
				nitTemp = plato.getNitRest();
				if(nitTemp != null) {					
					rest = this.buscarRestaurante(nitTemp);
					plato.setRestaurante(rest);
				}
			}
		}		
		
		return platosPrueba;
	}	
	
	@Override
	public List<Plato> buscarPlatoPorRestaurante(String nit) {  
		//Datos no validos
		if(nit == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}			
		
		// Buscar los platos de un restaurante
		List<Plato> platosEncontrados =  miRepositorioPlatos.findByNitRest(nit);
		
		// No tener en cuenta los platos eliminados
		this.eliminarPorStatus("DELETED", platosEncontrados);
		
		if(platosEncontrados.size() > 0) {				
			for(Plato plato: platosEncontrados) {
				plato.setRestaurante(rest);
			}
		}
		
		return platosEncontrados;
	}
	
	@Override
	public Plato buscarPlatoPorId(Long idPlato) {	
		Plato platoEncontrado = miRepositorioPlatos.findById(idPlato).orElse(null);
		
		//encontrar el restaurante
		if(platoEncontrado != null) {
			String nitRestaurante = platoEncontrado.getNitRest(); 
			
			//el restaurante existe
			if(nitRestaurante != null){
				Restaurante rest = this.buscarRestaurante(nitRestaurante);
				platoEncontrado.setRestaurante(rest);
			}
		}
		
		return platoEncontrado;
	}
		
	@Override
	public List<Plato> buscarPlatoPorNombre(String nit, String nombrePlato) {	
		//Datos no validos
		if(nit == null || nombrePlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getNombrePlato().equals(nombrePlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		return platosEncontrados;
	}
	
	@Override
	public List<Plato> buscarPlatoPorStatus(String nit, String statusPlato) {
		//Datos no validos
		if(nit == null || statusPlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getStatusPlato().equals(statusPlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		return platosEncontrados;
	}

	@Override
	public List<Plato> buscarPlatoPorCategoria(String nit, String categoriaPlato){
		//Datos no validos
		if(nit == null || categoriaPlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getCategoriaPlato().equals(categoriaPlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		// No tener en cuenta los platos eliminados
		this.eliminarPorStatus("DELETED", platosEncontrados);
		
		return platosEncontrados;
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
		
		//Validacion del restaurante
		if(plato.getNitRest() != null) {
			Restaurante rest = this.buscarRestaurante(plato.getNitRest());
			
			//El restaurante no existe
			if(rest == null) {
				System.out.println("\nEl restaurante no existe\n");
				return null;
			}
			
			plato.setRestaurante(rest);
		}		
		
		//System.out.println("\n\nid del plato: " + plato.getIdPlato() + "\n\\n");
        return miRepositorioPlatos.save(plato);
	}

	@Override
	public Plato actualizarPlato(Plato plato) {
		Plato plato_encontrado = buscarPlatoPorId(plato.getIdPlato());
        
		if (plato_encontrado == null){
            return null;
        }
		
		//validar el restaurante
		if(plato.getNitRest() != null) {
			Restaurante rest = this.buscarRestaurante(plato.getNitRest());
			
			//El restaurante no existe
			if(rest == null) {
				System.out.println("\nEl restaurante no existe\n");
				return null;
			}
			
			plato_encontrado.setRestaurante(rest);
        }
		
        plato_encontrado.setNombrePlato(plato.getNombrePlato());
        plato_encontrado.setDescPlato(plato.getDescPlato());
        plato_encontrado.setPrecioPlato(plato.getPrecioPlato());
        plato_encontrado.setImgPlato(plato.getImgPlato());
        plato_encontrado.setCategoriaPlato(plato.getCategoriaPlato());
        plato_encontrado.setStatusPlato(plato.getStatusPlato());
        plato_encontrado.setCantidadPlato(plato.getCantidadPlato());
        
        if(plato.getNitRest() != null)
        	plato_encontrado.setNitRest(plato.getNitRest());
                
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
	
	private Restaurante buscarRestaurante(String nit) {
		Restaurante rest = null;
		try {
			rest = clienteRestaurante.buscarRestaurantePorId(nit).getBody();
		}catch(Exception e) {
			return null;
		}
		return rest;
	}
	
	//Eliminar los platos de una lista con un status
	private void eliminarPorStatus(String status, List<Plato> platos){
		if(platos != null) {
			for(int i = 0; i < platos.size(); i++) {
				if(platos.get(i).getStatusPlato().equals(status)) {
					platos.remove(i);
				}
			}
		}
	}

	@Override
	public List<String> buscarCategoriasPorRestaurante(String nit) {		
		// Buscar los platos de un restaurante
		List<Plato> platosEncontrados = this.buscarPlatoPorRestaurante(nit);
		// Categorias encontradas
		List<String> categorias = new ArrayList<String>();
		
		String categoria;
		
		// Datos incorrectos
		if(platosEncontrados != null) {
			for(Plato plato: platosEncontrados) {
				// Tiene categoria:
				categoria = plato.getCategoriaPlato(); 
				if(categoria != null) {
					//Si no está en la lista, la agrega
					if(!categorias.contains(categoria)) {
						categorias.add(categoria);
					}
				}
			}
		}
		
		return categorias;
	}
	
	
	
	
	
}
