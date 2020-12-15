package com.restaurantePro.compras.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurantePro.compras.entidad.Factura;

@Repository
public interface IntRepositorioFactura extends JpaRepository<Factura,Long>{
	public List<Factura> findByAtrIdCliente(Long atrIdCliente);
	public Factura findByAtrNumeroFactura(String atrNumeroFactura);
	@Query("SELECT f FROM Factura f WHERE f.atrEstado = ' ANULADA '")
	public List<Factura> findByAtrEstado();
	@Query("SELECT f FROM Factura f WHERE f.atrEstado = ' CREADO '")
	public List<Factura> findByAtrEstado1();
	@Query("SELECT f FROM Factura f WHERE f.atrFecha BETWEEN ?1 AND ?2 AND f.atrIdRestaurante = ?3 AND f.atrEstado = ' CREADO '")
	public List<Factura> findReporte(String parFechaInicio, String parFechaFin, String parIdRestaurante);
	@Query("SELECT DISTINCT f.atrFecha FROM Factura f WHERE f.atrFecha BETWEEN ?1 AND ?2 AND f.atrIdRestaurante = ?3 AND f.atrEstado = ' CREADO '")
	public List<String> obtenerFechasParaReportes(String parFechaInicio, String parFechaFin, String parIdRestaurante);
	
}
