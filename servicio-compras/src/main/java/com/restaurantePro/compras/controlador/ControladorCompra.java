package com.restaurantePro.compras.controlador;
//import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;
import com.restaurantePro.compras.modelo.Carrito;
import com.restaurantePro.compras.modelo.ReporteVentas;
import com.restaurantePro.compras.servicio.IntServicioCompra;



@RestController
@RequestMapping(value = "/compras")
public class ControladorCompra {
	
	@Autowired
	private IntServicioCompra objServioCompra;
	
	@GetMapping("/items")
	public ResponseEntity<List<ItemFactura>> listarItems()
	{
		List<ItemFactura> listaItem = objServioCompra.listarTodosLosItems();
		if(listaItem.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaItem);
	}
	
	@GetMapping("/item/{idPlato}")
	public ResponseEntity<ItemFactura> obtenerItemPorIdPlato(@PathVariable(name = "idPlato")Long parIdPlato)
	{
		ItemFactura objItemFactura = objServioCompra.buscarItemFacturaPorIdPlato(parIdPlato);
		if(objItemFactura==null) 
		{
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objItemFactura);
	}
	
	@PostMapping("/factura")
	public ResponseEntity<Factura> crearFactura(@RequestBody Factura parFactura)
	{
		Factura objFactura = objServioCompra.crearFactura(parFactura);
		return ResponseEntity.status(HttpStatus.CREATED).body(objFactura);
	}
	
	@GetMapping(value = "facturaPorId/{idFactura}")
	public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable(name = "idFactura")Long parIdFactura)
	{
		Factura objFactura = objServioCompra.buscarFacturaPorId(parIdFactura);
		if(objFactura==null) 
		{
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objFactura);
	}
	
	@GetMapping(value = "facturaPorNumFactura/{numFactura}")
	public ResponseEntity<Factura> obtenerFacturaPorNumFactura(@PathVariable(name = "numFactura")String parNumFactura)
	{
		Factura objFactura = objServioCompra.buscarPorNumeroFactura(parNumFactura);
		if(objFactura==null) 
		{
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objFactura);
	}
	
	@GetMapping("/facturas")
	public ResponseEntity<List<Factura>> listarTodasLasFacturas()
	{
		List<Factura> listaFacturas = objServioCompra.listarTodasLasFacturas();
		if(listaFacturas.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaFacturas);
	}
	
	@DeleteMapping("/factura/{idFactura}")
	public ResponseEntity<Factura> eliminarFactura(@PathVariable(name = "idFactura",required = true) Long parIdFactura)
	{
		Factura facturaEliminada = objServioCompra.EliminarFactura(Factura.builder().atrIdFactua(parIdFactura).build());
		if(facturaEliminada==null) 
		{
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(facturaEliminada);
	}
	
	@PostMapping(value = "carrito/{idPlato}")
	public ResponseEntity<ItemFactura>  reducirIntanciasDelCarrito(@PathVariable(name = "idPlato")Long parIdPlato)
	{
		ItemFactura objItem= objServioCompra.reducirIntanciasDelCarrito(parIdPlato);
		if(objItem==null) 
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(objItem);
		
	}
	
	@PostMapping("carrito/{precio}/{idPlato}")
	public ResponseEntity<ItemFactura> agregarItemAlcarrito(@PathVariable(name = "precio")double parPrecio,@PathVariable(name = "idPlato")Long parIdPlato)
	{
		ItemFactura itemFactura = objServioCompra.agregarItemCarrito(parPrecio,parIdPlato);
		if(itemFactura == null) 
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(itemFactura);
	}
	
	@DeleteMapping(value = "/carrito/{idPlato}")
	public ResponseEntity<ItemFactura> eliminarItemCarrito(@PathVariable(name = "idPlato")Long parIdPlato)
	{
		ItemFactura itemFactura = objServioCompra.eliminarItemCarrito(parIdPlato);
		if(itemFactura==null) 
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemFactura);
	}
	
	@GetMapping(value = "/carrito")
	public ResponseEntity<Carrito> obtenerCarritoCompras()
	{
		Carrito carrito = objServioCompra.obtenerCarrito();
		if(carrito.getAtrListaItems().isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carrito);
	}
	
	@DeleteMapping("/limpiar-carrito")
	public ResponseEntity<List<ItemFactura>> limpiarCarrito()
	{
		List<ItemFactura> carrito = objServioCompra.limpiarCarrito();
		if(carrito.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carrito);
	}
	
	@PostMapping("/factura/{idCliente}/{idRestaurante}")
	public ResponseEntity<Factura> realizarVenta(@PathVariable(name = "idCliente") Long parIdCliente,@PathVariable(name = "idRestaurante") String parIdRestaurante)
	{
		Factura objFactura = objServioCompra.vender(parIdCliente,parIdRestaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(objFactura);
	}
	
	@GetMapping("/facturas/cliente/{idCliente}")
	public ResponseEntity<List<Factura>> listarFacturasPorIdCliente(@PathVariable(name = "idCliente")Long parIdCliente)
	{
		List<Factura> listaFacturas = objServioCompra.listarFacturasCliente(parIdCliente);
		if(listaFacturas.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaFacturas);
	}
	
	@GetMapping("/facturasAnuladas")
	public ResponseEntity<List<Factura>> listarFacturasAnuladas()
	{
		List<Factura> listaFacturasAnuladas = objServioCompra.listarFacturasAnuladas();
		if(listaFacturasAnuladas.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaFacturasAnuladas);
	}
	
	
	@GetMapping("/facturasActivas")
	public ResponseEntity<List<Factura>> listarFacturasActivas()
	{
		List<Factura> listaFacturasAnuladas = objServioCompra.listarFacturasActivas();
		if(listaFacturasAnuladas.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaFacturasAnuladas);
	}
	
	@GetMapping("/reporteVentas")
	public ResponseEntity<List<Factura>> ReporteVentas(@RequestParam(name = "fechaInicio",required = true)String parFechaInicio,@RequestParam(name = "fechaFin",required = true)String parFechaFin,@RequestParam(name = "idRestaurante",required = true)String parIdRestaurante)
	{
		List<Factura> listaReporteVentas = objServioCompra.ListarReporteVentas(parFechaInicio,parFechaFin,parIdRestaurante);
		if(listaReporteVentas.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaReporteVentas);
	}
	
	@GetMapping("/obtenerReporteVentas")
	public ResponseEntity<ReporteVentas> obtenerReporteVentas(@RequestParam(name = "fechaInicio",required = true)String parFechaInicio,@RequestParam(name = "fechaFin",required = true)String parFechaFin,@RequestParam(name = "idRestaurante",required = true)String parIdRestaurante)
	{
		ReporteVentas objReporteVentas = objServioCompra.obtenerReporteVentas(parFechaInicio, parFechaFin,parIdRestaurante);
		if(objReporteVentas.getAtrListaFacturas().isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(objReporteVentas);
	}


	
	


	


	

	
	
	
}
