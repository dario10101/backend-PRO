package com.restaurantePro.compras.modelo;

import java.util.ArrayList;
import java.util.List;

import com.restaurantePro.compras.entidad.Factura;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ReporteVentas {
	
	private List<Factura> atrListaFacturas;
	private double atrTotalVentas;
	
	public ReporteVentas() 
	{
		this.atrListaFacturas= new ArrayList<>();
		this.atrTotalVentas=0.0;
	}
	
	public double getAtrTotalVentas() 
	{
		for (Factura factura : atrListaFacturas) {
			this.atrTotalVentas = factura.getAtrTotalVenta()+this.atrTotalVentas;
		}
		return atrTotalVentas;
	}

}
