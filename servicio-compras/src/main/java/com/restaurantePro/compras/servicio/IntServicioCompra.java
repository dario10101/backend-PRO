package com.restaurantePro.compras.servicio;
import java.util.List;


import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;
import com.restaurantePro.compras.modelo.Carrito;
import com.restaurantePro.compras.modelo.ReporteVentas;

public interface IntServicioCompra {
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato);
	public List<ItemFactura> listarTodosLosItems();
	public Factura crearFactura(Factura parFactura);
	public Factura buscarFacturaPorId(Long parIdFactura);
	public List<Factura> listarTodasLasFacturas();
	public Factura EliminarFactura(Factura parFactura);
	public Factura buscarPorNumeroFactura(String parNumFactura);
	public Factura vender(Long parIdCliente,String parIdRestaurante);
	public List<Factura> listarFacturasCliente(Long parIdCliente);
	public List<Factura> listarFacturasAnuladas();
	public List<Factura> listarFacturasActivas();
	public ItemFactura reducirIntanciasDelCarrito(Long parIdPlato);
	public ItemFactura agregarItemCarrito(double parPrecio,Long parIdPlato);
	public ItemFactura eliminarItemCarrito(Long parIdPlato);
	public Carrito obtenerCarrito();
	public List<ItemFactura> limpiarCarrito();
	public List<Factura> ListarReporteVentas(String parFechaInicio,String parFechaFin, String parIdRestaurante);
	public ReporteVentas obtenerReporteVentas(String parFechaInicio,String parFechaFin,String parIdRestaurante);
	public ReporteVentas obtenerReporteVentasDelDia(String parIdRestaurante);
	public Double obtenerReporteTotalVentasDelDia(String parIdRestaurante);
	


}
