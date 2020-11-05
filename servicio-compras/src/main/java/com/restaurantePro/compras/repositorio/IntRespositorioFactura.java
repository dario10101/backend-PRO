package com.restaurantePro.compras.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantePro.compras.entidad.Factura;

@Repository
public interface IntRespositorioFactura extends JpaRepository<Factura,Long>{
	public List<Factura> findByAtrIdCliente(Long atrIdCliente);
	public Factura findByAtrNumeroFactura(String atrNumeroFactura);

}
