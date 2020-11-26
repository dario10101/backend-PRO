package com.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Plato;
import com.pro.entity.Semanario;

@Repository
public interface RepositorioSemanario extends JpaRepository<Semanario, Long>{
	
	//-
	public List<Semanario> findByDias(String dias);
	//public List<Plato> findByDias(String nitRest, String dia);
	
	public List<Semanario> findByNitRest(String nit_rest);
	
	public Semanario findByPlato(Plato plato);
}
