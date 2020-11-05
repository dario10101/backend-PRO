package com.restaurantePro.compras.servicio;
import java.util.List;

import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;

public interface IntServicioCompra {
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato);
	public List<ItemFactura> listarTodosLosItems();
	public Factura crearFactura(Factura parFactura);
	public Factura buscarFacturaPorId(Long parIdFactura);
	public List<Factura> listarTodasLasFacturas();
	public Factura EliminarFactura(Factura parFactura);
	public Factura buscarPorNumeroFactura(String parNumFactura);
	public Factura vender(Long parIdCliente);

}
