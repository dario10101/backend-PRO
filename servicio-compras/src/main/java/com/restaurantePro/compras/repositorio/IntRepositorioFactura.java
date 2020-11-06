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

}
