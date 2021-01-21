package com.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Restaurante;

/**
 * Repositorio de restaurantes, abstraccion de la base de datos
 * 
 * @author Ruben
 *
 */
@Repository
public interface RepositorioRestaurantes extends JpaRepository<Restaurante, String>{
	
	/**
	 * Buscar un restaurante por su nombre en la base de datos
	 * @param nombre_restaurante
	 * @return Restaurantes encontrados
	 */
	public List<Restaurante> findByNombreRest(String nombre_restaurante);
	
	/**
	 * Buscar restaurante por su status
	 * @param status_restaurante (ACTIVATED o DELETED)
	 * @return Restaurantes encontrados
	 */
	public List<Restaurante> findByStatusRest(String status_restaurante);

}
