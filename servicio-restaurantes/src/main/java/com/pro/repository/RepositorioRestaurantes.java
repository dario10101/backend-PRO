package com.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Restaurante;


@Repository
public interface RepositorioRestaurantes extends JpaRepository<Restaurante, Long>{
	
	public List<Restaurante> findByNombreRest(String nombre_restaurante);

}
