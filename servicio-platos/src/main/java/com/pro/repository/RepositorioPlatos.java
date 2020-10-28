package com.pro.repository;

import com.pro.entity.Plato;
//import com.pro.entity.Restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPlatos extends JpaRepository<Plato, Long> {
	
	//public List<Plato> findByRestaurante(Restaurante restaurante);
	
	public List<Plato> findByNombrePlato(String nombre_plato);
	
	public List<Plato> findByStatusPlato(String status_plato);
	
	public List<Plato> findByCategoriaPlato(String categoria_plato);
	
	public List<Plato> findByNitRest(String nit_rest);
	
}
