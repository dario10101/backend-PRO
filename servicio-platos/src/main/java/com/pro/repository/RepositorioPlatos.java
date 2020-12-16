package com.pro.repository;

import com.pro.entity.Plato;
//import com.pro.entity.Restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPlatos extends JpaRepository<Plato, Long> {
	
	//public List<Plato> findByRestaurante(Restaurante restaurante);
	
	/**
	 * Buscar platos por nombre en la base de datos
	 * @param nombre_plato
	 * @return Lista de platos
	 */
	public List<Plato> findByNombrePlato(String nombre_plato);
	
	/**
	 * Buscar platos por estado (ACTIVATED, DELETED)
	 * @param status_plato
	 * @return Lista de platos encontrados
	 */
	public List<Plato> findByStatusPlato(String status_plato);
	
	/**
	 * Buscar por categoria en la base de datos
	 * @param categoria_plato
	 * @return Lista de platos encontrados
	 */
	public List<Plato> findByCategoriaPlato(String categoria_plato);
	
	/**
	 * Buscar platos que pertenezcan a un restaurante en especifico
	 * @param nit_rest nit del restaurante
	 * @return Lista de platos encontrados
	 */
	public List<Plato> findByNitRest(String nit_rest);
	
}
