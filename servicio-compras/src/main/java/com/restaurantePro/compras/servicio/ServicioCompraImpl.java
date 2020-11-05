package com.restaurantePro.compras.servicio;
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
	public List<ItemFactura> listarTodosLosItems() {
		// TODO Auto-generated method stub
		return objRepositorioItemFactura.findAll();
	}

	@Override
	public Factura crearFactura(Factura parFactura) {
		Factura objFactura = objRepositorioFactura.findByAtrNumeroFactura(parFactura.getAtrNumeroFactura());
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
	public Factura buscarFacturaPorId(Long parIdFactura) {
		Factura objFactura = objRepositorioFactura.findById(parIdFactura).orElse(null);
		if(objFactura != null) 
		{
			Cliente objCliente = objClienteFeing.buscarClientePorId(objFactura.getAtrIdCliente()).getBody();
			objFactura.setObjCliente(objCliente);
			objFactura = objRepositorioFactura.save(objFactura);
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
	public List<Factura> listarTodasLasFacturas() {
		List<Factura> listaFacturas = objRepositorioFactura.findAll().stream().map(Factura->{
			buscarFacturaPorId(Factura.getAtrIdFactua());
			return Factura;
		}).collect(Collectors.toList());
		return listaFacturas;
	}


	@Override
	public Factura EliminarFactura(Factura parFactura) {
		Factura objFactura = objRepositorioFactura.findByAtrNumeroFactura(parFactura.getAtrNumeroFactura());
		if(objFactura == null) 
		{
			return null;
		}
		objFactura.setAtrEstado("Anulada");
		return objRepositorioFactura.save(objFactura);
	}

	@Override
	public Factura buscarPorNumeroFactura(String parNumFactura) {
		Factura objFactura = objRepositorioFactura.findByAtrNumeroFactura(parNumFactura);
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
	public Factura vender(Long parIdCliente) {
		System.out.println("\n\nIngresando a veder\n\n");
		Factura objFactura = Factura.builder().atrEstado("creado").atrIdCliente(parIdCliente).build();
		System.out.println("\n\n Factura creada\n\n");
		objFactura.setListaItems(obtenerCarrito().getAtrListaItems());
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
		objCarrito.setAtrListaItems(carrito);
		return objCarrito;
	}
	
}
