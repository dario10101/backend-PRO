package com.restaurantePro.compras.servicio;
import java.util.List;


import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;
import com.restaurantePro.compras.modelo.Carrito;
import com.restaurantePro.compras.modelo.ReporteVentas;

/**
 * Esta interface define los métodos que va a consumir el controlador REST
 * @author Héctor Fabio Meneses
 *
 */
public interface IntServicioCompra {
	/**
	 * Este metodo permite realizar la búsqueda de un item de factura especifico por medio del identicador único de un plato registrado en el sistema.
	 * @param parIdPlato. Es identificador único de un plato registrado en el sistema.
	 * @return. devuelve el Item de factura registrado en la BD con el identificador del plato ingresado por parámetro.
	 */
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato);
	
	/**
	 * Este método crea y registra una nueva factura en la BD de forma Automática cuando el cliente termina de gestionar su pedido.
	 * @param parFactura. Es la nueva factura que se registrará en el sistema
	 * @return. La nueva factura registrada en la BD
	 */
	public Factura crearFactura(Factura parFactura);
	
	/**
	 * Este método permite asociar todos lo ítem gestionados por el cliente en el carrito de compras, El total a pagar y el cliente que realiza la compra con la nueva factura que será registrada en la BD
	 * @param parIdCliente. Identificador único del cliente que será asociado a la nueva factura que será registrada en el sistema
	 * @param parIdRestaurante.Identificador único del restaurante al cual está asociada la factura
	 * @return. La nueva factura registrada en el sistema
	 */
	public Factura vender(Long parIdCliente,String parIdRestaurante);
	
	/**
	 * Este método permite cambiar el estado de una factura a "anulada" si el usuario así lo dispone.
	 * @param parFactura. Representa la factura a la cual se desea cambiar su estado.
	 * @return. La factura con su nuevo estado.
	 */
	public Factura EliminarFactura(Factura parFactura);
	
	/**
	 * Este método realiza la búsqueda de una factura especifica asociada a un único restaurante.
	 * @param parIdFactura. Identificador único de la factura registrada en el sistema.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @return.La factura correspondiente al identificador especificado en el parámetro
	 */
	public Factura buscarFacturaPorId(Long parIdFactura,String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de una factura especifica asociada a un único restaurante.
	 * @param atrNumeroFactura. Número de factura mediante el cual se realizará la búsqueda
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @return. La factura correspondiente al número de factura especificado en el parámetro
	 */
	public Factura buscarPorNumeroFactura(String parNumFactura,String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas asociadas a cliente en un restaurante epecifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @param parIdCliente. Identificador único del cliente registrado en el sistema.
	 * @return. lista de facturas expedidas al cliente cuyo identifador coincide con especificado en 
	 * el Parámetro
	 */
	public List<Factura> listarFacturasCliente(Long parIdCliente,String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas asociadas a un restaurante especifico.
	 * @param parIdRestaurante.  Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturaas asociadas al restaurante cuyo identificador coincide con el 
	 * especificado en el parámetro.
	 */
	public List<Factura> listarTodasLasFacturas(String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas ANULADAS asociadas a un restaurante especifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ANULADAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro.
	 */
	public List<Factura> listarFacturasAnuladas(String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas ACTIVAS asociadas a un restaurante especifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ACTIVAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro.
	 */
	public List<Factura> listarFacturasActivas(String parIdRestaurante);
	
	/**
	 * Este método permite reducir el número de instancias de un plato gestionado por el cliente en el carrito
	 * de compras, si se dispone de una sola instancia se eliminará por completo el plato del pedido.
	 * @param parIdPlato. Indentificador único del plato al cual se le desea reducir el número de instancias
	 * @return. El item de factura con el atributo "atrCantidad" actualizado.
	 */
	public ItemFactura reducirIntanciasDelCarrito(Long parIdPlato);
	
	/**
	 * Este método permite agregar un nuevo plato al carrito de compras, en caso de ya exitir aumenta el número de instancias de este.
	 * @param parPrecio. Precio del plato que se está gestionando por el cliente.
	 * @param parIdPlato. Indentificador único del plato que se agregará al carrito de compras
	 * @return. El item de factura agregado al pedido gestionado por el cliente.
	 */
	public ItemFactura agregarItemCarrito(double parPrecio,Long parIdPlato);
	
	/**
	 * Este método permite eliminar un item del carrito de compras de forma directa.
	 * @param parIdPlato. Identificador único del plato que será dado de baja del pedio.
	 * @return. el item de factura eliminado
	 */
	public ItemFactura eliminarItemCarrito(Long parIdPlato);
	
	/**
	 * Este método permite obtener el carrito de compras con su lista de items actualizado.
	 * @return. El carrito de compras gestionado por el cliente.
	 */
	public Carrito obtenerCarrito();
	
	/**
	 * Este método permite limpiar el carrito de compras ya sea porque se realizo la venta o por Decisión del cliente.
	 * @return. lista de items del carrito de compras vacía.
	 */
	public List<ItemFactura> limpiarCarrito();
	
	/**
	 * Este método realiza la búsqueda de todas facturas ACTIVAS asociadas a un restaurante especifico, comprendidas en un rango de fechas definidas por el usuario.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ACTIVAS asociadas al restaurante cuyo identificador coincide con el especificado en el parámetro y correspoden al rango de fechas establecido.
	 */	
	public List<Factura> ListarReporteVentas(String parFechaInicio,String parFechaFin, String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas ACTIVAS asociadas a un restaurante especifico, comprendidas en un rango de fechas definidas por el usuario.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. El reporte de ventas, el cual esta conformado por la lista de facturas activas correspodiente al rango de fechas establecido y el total de dinero
	 * recaudado.
	 */	
	public ReporteVentas obtenerReporteVentas(String parFechaInicio,String parFechaFin,String parIdRestaurante);
	
	/**
	 * Este método muestra la información completa de cada una de las facturas que hacen parte del reporte de ventas en el dia. 
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. El reporte de ventas con información completa de cada una de las facturas y el total de dinero recaudado.
	 */
	public ReporteVentas obtenerReporteVentasDelDia(String parIdRestaurante);
	
	/**
	 * Este método muestra la información del total de dinero recaudado en el dia. 
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. El total de dinero recaudado en el dia.
	 */
	public Double obtenerReporteTotalVentasDelDia(String parIdRestaurante);
	
	/**
	 * Este método muestra la información completa de cada una de las facturas que hacen parte del reporte de ventas 
	 * en un rango de fechas establecido por el usuario.
	 * @param parIdRestaurante.Identificador único del restaurante al cual está asociada la factura.
	 * @return. la lista de reportes de ventas con información completa de cada una de las facturas y el total de dinero recaudado. 
	 */
	public List<ReporteVentas> obtenerReporteVentasPorFechas(String parFechaInicio,String parFechaFin,String parIdRestaurante);
	
	/**
	 * Este método muestra la información del total de dinero recaudado por dia en el rango de fechas establecido. 
	 * @param parIdRestaurante.Identificador único del restaurante al cual está asociada la factura.
	 * @return. La lista de todos totales recaudados en el rango de fechas establecido.
	 */
	public List<Double> obtenerReporteTotalVentasPorDia(String parFechaInicio,String parFechaFin, String parIdRestaurante);

}
