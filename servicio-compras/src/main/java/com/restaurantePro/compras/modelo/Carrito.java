package com.restaurantePro.compras.modelo;

import java.util.ArrayList;

import java.util.List;

import com.restaurantePro.compras.entidad.ItemFactura;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * La decoración @Data: permite crear los getters, setters, el constructor y algún otro método como equals, canEquals,
 * hashCode y toString de forma automática.
 * 
 * La decoración @AllArgsContructor, brinda un constructor con todos los argumentos.
 * 
 * Esta clase representa el carrito de compras que gestionará el cliente en cada uno 
 * de los pedidos que este realice.
 * 
 * @author Héctor Fabio Meneses
 *
 */
@AllArgsConstructor
@Data
public class Carrito 
{
	/**
	 * Lista de productos gestionados por el cliente con sus respectivas cantidades y precios.
	 */
	private List<ItemFactura> atrListaItems;
	
	/**
	 * Total a pagar por cliente al terminar de gestionar su pedido. 
	 */
	private double atrTotalApagar;
	
	public Carrito()
	{
		this.atrListaItems = new ArrayList<>(); 
		this.atrTotalApagar=0.0;
	}
	
	/**
	 * Este método calcula el total a pagar por cada cliente cuando terminada de gestionar su pedido, 
	 * sumando los subtotales de cada ítem de factura gestionado en el pedido.
	 * @return. Total a pagar por el cliente al terminar de gestionar su pedido.
	 */
	public double getAtrTotalApagar() 
	{
		for (ItemFactura itemFactura : atrListaItems) {
			atrTotalApagar = itemFactura.getAtrSubtotal()+atrTotalApagar;
		}
		return atrTotalApagar;
	}
}
