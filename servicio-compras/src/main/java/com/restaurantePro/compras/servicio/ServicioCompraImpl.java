package com.restaurantePro.compras.servicio;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.restaurantePro.compras.clienteFeign.IntClienteFeign;
import com.restaurantePro.compras.clienteFeign.IntPlatoFeign;
import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;
import com.restaurantePro.compras.modelo.Carrito;
import com.restaurantePro.compras.modelo.Cliente;
import com.restaurantePro.compras.modelo.Plato;
import com.restaurantePro.compras.modelo.ReporteVentas;
import com.restaurantePro.compras.repositorio.IntRepositorioItemFactura;
import com.restaurantePro.compras.repositorio.IntRepositorioFactura;


@Service
public class ServicioCompraImpl implements IntServicioCompra{
	@Autowired
	private IntRepositorioItemFactura objRepositorioItemFactura;
	
	@Autowired
	private IntRepositorioFactura   objRepositorioFactura;
	
	@Autowired
	private IntClienteFeign  objClienteFeing;
	
	@Autowired
	private IntPlatoFeign objPlatoFeing;
		
	private List<ItemFactura> carrito = new ArrayList<ItemFactura>();
	private Carrito objCarrito = new Carrito();

	@Override
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato) {
		
		return objRepositorioItemFactura.findById(parIdPlato).orElse(null);
	}


	@Override
	public Factura crearFactura(Factura parFactura) {
		Factura objFactura = objRepositorioFactura.buscarPorNumeroFactura(parFactura.getAtrNumeroFactura(),parFactura.getAtrIdRestaurante());
		if(objFactura!= null) 
		{
			return objFactura;
		}
		objFactura = objRepositorioFactura.save(parFactura);
		objFactura.getListaItems().forEach(ItemFactura->{
			objPlatoFeing.actualizarCantidadPlato(ItemFactura.getAtrIdPlato(),ItemFactura.getAtrCantidad()*-1);
		});
		return objFactura;
	}

	@Override
	public Factura buscarFacturaPorId(Long parIdFactura,String parIdRestaurante) {
		Factura objFactura = objRepositorioFactura.buscarFactuaPorId(parIdFactura, parIdRestaurante);
		if(objFactura != null) 
		{
			System.out.println("\n\n antes del feing \n\n");
			Cliente objCliente = objClienteFeing.buscarClientePorId(objFactura.getAtrIdCliente()).getBody();
			System.out.println("\n\n despues del feing \n\n");
			objFactura.setObjCliente(objCliente);
			//objFactura = objRepositorioFactura.save(objFactura);
			List<ItemFactura> listaItems = objFactura.getListaItems().stream().map(ItemFactura->{
				Plato objPlato = objPlatoFeing.buscarPlatoPorId(ItemFactura.getAtrIdPlato()).getBody();
				ItemFactura.setObjplato(objPlato);
				return ItemFactura;
			}).collect(Collectors.toList());
			objFactura.setListaItems(listaItems);
		}
		return objFactura;
	}

	@Override
	public List<Factura> listarTodasLasFacturas(String parIdRestaurante) {
		List<Factura> listaFacturas = objRepositorioFactura.ListarFacturasPorIdRestaurante(parIdRestaurante).stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua(),parIdRestaurante);
			return Factura;
		}).collect(Collectors.toList());
		return listaFacturas;
	}


	@Override
	public Factura EliminarFactura(Factura parFactura) {
		Factura objFactura = objRepositorioFactura.buscarPorNumeroFactura(parFactura.getAtrNumeroFactura(),parFactura.getAtrIdRestaurante());
		if(objFactura == null) 
		{
			return null;
		}
		objFactura.setAtrEstado(" ANULADA ");
		return objRepositorioFactura.save(objFactura);
	}

	@Override
	public Factura buscarPorNumeroFactura(String parNumFactura,String parIdRestaurante) {
		Factura objFactura = objRepositorioFactura.buscarPorNumeroFactura(parNumFactura,parIdRestaurante);
		if(objFactura != null) 
		{
			Cliente objCliente = objClienteFeing.buscarClientePorId(objFactura.getAtrIdCliente()).getBody();
			objFactura.setObjCliente(objCliente);
			List<ItemFactura> listaItems = objFactura.getListaItems().stream().map(ItemFactura->{
				Plato objPlato = objPlatoFeing.buscarPlatoPorId(ItemFactura.getAtrIdPlato()).getBody();
				ItemFactura.setObjplato(objPlato);
				return ItemFactura;
			}).collect(Collectors.toList());
			objFactura.setListaItems(listaItems);
		}
		return objFactura;
	}

	@Override
	public Factura vender(Long parIdCliente, String parIdRestaurante) {
		System.out.println("\n\nIngresando a veder\n\n");
		Factura objFactura = Factura.builder().atrEstado(" CREADO ").atrIdCliente(parIdCliente).atrIdRestaurante(parIdRestaurante).build();
		System.out.println("\n\n Factura creada\n\n");
		Carrito objCarrito = obtenerCarrito();
		objFactura.setListaItems(objCarrito.getAtrListaItems());
		objFactura.setAtrTotalVenta(objCarrito.getAtrTotalApagar());
		objFactura = crearFactura(objFactura);
		return objFactura;
	}

	@Override
	public ItemFactura reducirIntanciasDelCarrito(Long parIdPlato) {
		ItemFactura objItem = null;
		for (ItemFactura itemFactura : carrito) 
		{
			if(itemFactura.getAtrIdPlato()== parIdPlato) 
			{
				objItem= itemFactura;
				
				System.out.println("\n\n catidad \n\n"+itemFactura.getAtrCantidad());
				if(itemFactura.getAtrCantidad() > 1) 
				{
					System.out.println("\n\n catidad diferente de cero \n\n"+itemFactura.getAtrCantidad());
					itemFactura.setAtrCantidad(itemFactura.getAtrCantidad()-1);
					break;
				}
				itemFactura.setAtrCantidad(itemFactura.getAtrCantidad()-1);
				carrito.remove(itemFactura);
				break;
			}
			
		}
		return objItem;
	}

	@Override
	public ItemFactura agregarItemCarrito(double parPrecio, Long parIdPlato) {
		ItemFactura objItemFactura = new ItemFactura(parPrecio,(double)1,parIdPlato);
		if(carrito.isEmpty()) 
		{
			carrito.add(objItemFactura);
		}
		else 
		{	
			for (ItemFactura itemFactura : carrito) {
				if(itemFactura.getAtrIdPlato()==parIdPlato) 
				{
					itemFactura.setAtrCantidad(itemFactura.getAtrCantidad()+1);
					return itemFactura;
				}
			}
			carrito.add(objItemFactura);	
		}
		return objItemFactura;
	}

	@Override
	public ItemFactura eliminarItemCarrito(Long parIdPlato) {
		ItemFactura objItemFactura=null;
		for (ItemFactura itemFactura : carrito) {
			if(itemFactura.getAtrIdPlato() == parIdPlato) 
			{
				objItemFactura = itemFactura;
				carrito.remove(itemFactura);
				break;
			}
		}
		
		return objItemFactura;
	}

	@Override
	public Carrito obtenerCarrito() {
		objCarrito.setAtrTotalApagar(0.0);
		List<ItemFactura> listaItems = carrito.stream().map(ItemFactura->{
			Plato objPlato = objPlatoFeing.buscarPlatoPorId(ItemFactura.getAtrIdPlato()).getBody();
			ItemFactura.setObjplato(objPlato);
			return ItemFactura;
		}).collect(Collectors.toList());
		
		objCarrito.setAtrListaItems(listaItems);
		return objCarrito;
	}

	@Override
	public List<ItemFactura> limpiarCarrito() {
		carrito.clear();
		return carrito;
	}

	@Override
	public List<Factura> listarFacturasCliente(Long parIdCliente,String parIdRestaurante) {
		List<Factura> listaFacturas = objRepositorioFactura.buscarPorIdCliente(parIdCliente, parIdRestaurante).stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua(),parIdRestaurante);
			return Factura;
		}).collect(Collectors.toList());
		return listaFacturas;
	
	}

	@Override
	public List<Factura> listarFacturasAnuladas(String parIdRestaurante) {
		List<Factura> listaFacturas = objRepositorioFactura.findByAtrEstado(parIdRestaurante).stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua(),parIdRestaurante);
			return Factura;
		}).collect(Collectors.toList());
		return listaFacturas;
	}

	@Override
	public List<Factura> listarFacturasActivas(String parIdRestaurante) {
		List<Factura> listaFacturas = objRepositorioFactura.findByAtrEstado1(parIdRestaurante).stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua(),parIdRestaurante);
			return Factura;
		}).collect(Collectors.toList());
		return listaFacturas;
	}

	@Override
	public List<Factura> ListarReporteVentas(String parFechaInicio, String parFechaFin,String parIdRestaurante) {
		List<Factura> listaReporteVentas = objRepositorioFactura.findReporte(parFechaInicio,parFechaFin,parIdRestaurante).stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua(),parIdRestaurante);
			return Factura;
		}).collect(Collectors.toList());
		
		return listaReporteVentas;
	}

	@Override
	public ReporteVentas obtenerReporteVentas(String parFechaInicio,String parFechaFin,String parIdRestaurante) {
		ReporteVentas objReporteVentas = new ReporteVentas();
		objReporteVentas.setAtrListaFacturas(ListarReporteVentas(parFechaInicio, parFechaFin,parIdRestaurante));
		return objReporteVentas;
	}

	@Override
	public ReporteVentas obtenerReporteVentasDelDia(String parIdRestaurate) {
    	LocalDate fecha = LocalDate.now();
        String fechaActual = fecha.getYear()+"-"+fecha.getMonthValue()+"-"+fecha.getDayOfMonth();
		return obtenerReporteVentas(fechaActual, fechaActual,parIdRestaurate);
	}

	@Override
	public Double obtenerReporteTotalVentasDelDia(String parIdRestaurante) {
		ReporteVentas objReporteVentas = obtenerReporteVentasDelDia(parIdRestaurante);
		double reporteTotalVentas = objReporteVentas.getAtrTotalVentas();
		return reporteTotalVentas;
	}

	@Override
	public List<ReporteVentas> obtenerReporteVentasPorFechas(String parFechaInicio, String parFechaFin,String parIdRestaurante) {
		List<ReporteVentas> listaDeReportes = new ArrayList<>();
		List<String> listaFechas =objRepositorioFactura.obtenerFechasParaReportes(parFechaInicio, parFechaFin,parIdRestaurante);
		for (String fecha : listaFechas) {
			listaDeReportes.add(obtenerReporteVentas(fecha, fecha,parIdRestaurante));
		}
		return listaDeReportes;
	}


	@Override
	public List<Double> obtenerReporteTotalVentasPorDia(String parFechaInicio, String parFechaFin,String parIdRestaurante) {
		List<Double> reportesPorDia = new ArrayList<>();
		List<ReporteVentas> listaDeReportes = obtenerReporteVentasPorFechas(parFechaInicio, parFechaFin,parIdRestaurante);
		for (ReporteVentas reporteVentas : listaDeReportes) {
			reportesPorDia.add(reporteVentas.getAtrTotalVentas());
		}
		return reportesPorDia;
	}
	

	








	
}
