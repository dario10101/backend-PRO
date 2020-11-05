package com.restaurantePro.compras.entidad;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.restaurantePro.compras.modelo.Plato;

//import com.restaurantePro.compra.modelo.Plato;

//import com.restaurantePro.compra.modelo.Plato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_itemfacturas")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ItemFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long atrId;
	@Column(name = "item_cantidad")
	private Double atrCantidad;
	@Column(name = "item_precio")
	private Double atrPrecio;
	@Column(name = "item_producto_id")
	private Long atrIdPlato;
	
	@Transient
	private Double atrSubtotal;
	
	@Transient
	private Plato objplato;
	
	public Double getAtrSubtotal() 
	{
		if(this.atrCantidad>0 && this.atrPrecio>0) 
		{
			return this.atrPrecio*this.atrCantidad;
		}else 
		{
			return  (double) 0;
		}
	}
	
	public ItemFactura(Double parPrecio,Double parCantidad,Long parIdPlato) 
	{
		this.atrPrecio = parPrecio;
		this.atrCantidad = parCantidad;
		this.atrIdPlato = parIdPlato;
	} 
	

}
