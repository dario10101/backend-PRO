package com.restaurantePro.compras.servicio;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurantePro.compras.entidad.Factura;
import com.restaurantePro.compras.entidad.ItemFactura;


@Service
public class ServicioCompraImpl implements IntServicioCompra{

	@Override
	public ItemFactura buscarItemFacturaPorIdPlato(Long parIdPlato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemFactura> listarTodosLosItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura crearFactura(Factura parFactura) {
		// TODO Auto-generated method stub
		return null;
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
