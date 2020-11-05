package com.restaurantePro.compras.modelo;

import java.util.ArrayList;
import java.util.List;

import com.restaurantePro.compras.entidad.ItemFactura;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Carrito 
{
	private List<ItemFactura> atrListaItems;
	private double atrTotalApagar;
	
	public Carrito()
	{
		this.atrListaItems = new ArrayList<>(); 
		this.atrTotalApagar=0.0;
	}
	
	public double getAtrTotalApagar() 
	{
		for (ItemFactura itemFactura : atrListaItems) {
			atrTotalApagar = itemFactura.getAtrSubtotal()+atrTotalApagar;
		}
		return atrTotalApagar;
	}
}
