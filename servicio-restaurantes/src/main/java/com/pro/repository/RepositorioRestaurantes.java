package com.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Restaurante;


@Repository
public interface RepositorioRestaurantes extends JpaRepository<Restaurante, String>{
	
	public List<Restaurante> findByNombreRest(String nombre_restaurante);
	
	public List<Restaurante> findByStatusRest(String status_restaurante);

}
