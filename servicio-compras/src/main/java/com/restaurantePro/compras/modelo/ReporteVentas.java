package com.restaurantePro.compras.modelo;

import java.util.ArrayList;
import java.util.List;

import com.restaurantePro.compras.entidad.Factura;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * La decoración @Data: permite crear los getters, setters, el constructor y algún otro método como equals, canEquals,
 * hashCode y toString de forma automática.
 * 
 * La decoración @AllArgsContructor, brinda un constructor con todos los argumentos.
 * 
 * Esta clase representa el reporte de ventas en un rango de tiempo específico.
 * 
 * @author Héctor Fabio Meneses
 *
 */

@Data
@AllArgsConstructor
public class ReporteVentas {
	
	/**
	 * Lista de facturas expedidas en un rango de tiempo específico
	 */
	private List<Factura> atrListaFacturas;
	
	/**
	 * Total de ingresos obtenidos por las ventas realizadas en un rango de tiempo específico.
	 */
	private double atrTotalVentas;
	
	public ReporteVentas() 
	{
		this.atrListaFacturas= new ArrayList<>();
		this.atrTotalVentas=0.0;
	}
	
	/**
	 * Este metodo calcula el total de ingresos obtenidos en un rango de tiempo específico, sumando 
	 * cada uno de los totales de las facturas obtenidas.
	 * @return. Total de ingresos obtenidos por ventas en un rago de tiempo específico.
	 */
	public double getAtrTotalVentas() 
	{
		for (Factura factura : atrListaFacturas) {
			this.atrTotalVentas = factura.getAtrTotalVenta()+this.atrTotalVentas;
		}
		return atrTotalVentas;
	}

}
