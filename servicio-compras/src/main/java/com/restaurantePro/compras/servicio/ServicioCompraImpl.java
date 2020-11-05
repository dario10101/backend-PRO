package com.restaurantePro.compras.servicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantePro.compras.clienteFeign.IntClienteFeign;
import com.restaurantePro.compras.clienteFeign.IntPlatoFeign;
import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;
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

	@Override
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato) {
		
		return null;
	}

	@Override
	public List<ItemFactura> listarTodosLosItems() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factura> listarTodasLasFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura EliminarFactura(Factura parFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura buscarPorNumeroFactura(String parNumFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura vender(Long parIdCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
