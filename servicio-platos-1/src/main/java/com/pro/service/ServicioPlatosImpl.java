package com.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.entity.Plato;
import com.pro.entity.Restaurante;
import com.pro.repository.RepositorioPlatos;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioPlatosImpl implements ServicioPlatos{	

	private final RepositorioPlatos miRepositorioPlatos;
	
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
		Plato platoNuevo = this.construirNuevoPlato(plato);			
        
		//verificar si existe
		Plato plato_encontrado = buscarPlatoPorId(platoNuevo.getIdPlato());
		
		//el plato ya existe
		if (plato_encontrado != null){
			System.out.println("\nPlato exixtente\n");
            return null;
        }				
		
		//System.out.println("\n\nid del plato: " + plato.getIdPlato() + "\n\\n");
        return miRepositorioPlatos.save(platoNuevo);
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
		return miRepositorioPlatos.findByRestaurante(restaurante);
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
	private Plato construirNuevoPlato(Plato platoBase) {
		
		//validar status
		String status = platoBase.getStatusPlato();
		if (platoBase.getStatusPlato() == null) {
			status = "ACTIVATED";
		}
		if(!status.equals("ACTIVATED") && status.equals("DELETED")) {
			status = "ACTIVATED";
		}
		
		//validar cantidad
		Double cantidad = platoBase.getCantidadPlato();
		if(cantidad == null){
			cantidad = 0.0;
		}
		
		//crear el plato valido
		Plato plNuevo;
		plNuevo = Plato.builder()
				.idPlato(platoBase.getIdPlato())
                .nombrePlato("Sancocho")
                .descPlato("De gallina muerta")
                .precioPlato(15000.0)
                .imgPlato("sin imagen")
                .categoriaPlato("Especial")
                .statusPlato(status)
                .cantidadPlato(15.0)
                .ingredientesPlato("ENTRADAS INGREDIENTES POSTRES")
                .restaurante(Restaurante.builder().idRest(2L).build())
                .build();
		
		return plNuevo;
	}
	
	
	
	
	
	
	
	
}
