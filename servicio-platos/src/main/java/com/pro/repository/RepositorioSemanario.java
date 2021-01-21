package com.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Plato;
import com.pro.entity.Semanario;

@Repository
public interface RepositorioSemanario extends JpaRepository<Semanario, Long>{
	
	/**
	 * Busca los semanarios de un plato segun los dias en que se ofrezca
	 * @param dias dias que se ofrece
	 * @return Lista de semanarios
	 */
	public List<Semanario> findByDias(String dias);
	
	/**
	 * Buscar la informacion de semanarios de un restaurante en la base de datos
	 * @param nit_rest Identificador del restauranre
	 * @return Lista de semanarios
	 */
	public List<Semanario> findByNitRest(String nit_rest);
	
	/**
	 * Buscar los dias que se ofrece un plato (semanario)
	 * @param plato Plato a buscar el semanario
	 * @return Semanario encontrado
	 */
	public Semanario findByPlato(Plato plato);
}
