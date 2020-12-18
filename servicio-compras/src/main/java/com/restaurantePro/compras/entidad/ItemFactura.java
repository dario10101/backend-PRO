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

/**
 * Clase que representa la entidad "tbl_itemfacturas" que será mapeada en la base datos en memoria(H2),
 * esta clase hace referencia a cada uno de los item de factura registrados en el pedido y a sus repectivas
 * Cantidades, precios, y subtotal a pagar por cada uno de estos.
 * 
 * Las decoraciones @AllArgsContructor y @NoArgsConstructor, nos brindan dos constructores uno 
 * sin argumentos y otro con todos los argumentos.
 * 
 * La decoración @builder: nos creará el método público build y el objeto estático Builder con todos sus atributos y métodos
 * 
 * La decoración @Data: permite crear los getters, setters, el constructor y algún otro método como equals, canEquals,
 * hashCode y toString de forma automática.
 * 
 * La decoración @Entity: indica que la clase es una entidad
 * 
 * La decoración @Table: indica que tabla estamos mapeando en la clase
 * 
 * @author Héctor Fabio Meneses
 *
 */
@Entity
@Table(name = "tbl_itemfacturas")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ItemFactura {
	/**
	 * La decoración @Id: indica la propiedad que actúa como identificador
	 * 
	 * La decoración @Column: indica contra que columna de la tabla se mapea una propiedad
	 * 
	 * La decoración @GeneratedValue Se basa en una columna de base de datos con incremento automático y 
	 * permite que la base de datos genere un nuevo valor con cada operación de inserción. 
	 * 
	 * La decoración @Transient significa que el atributo no será mapeado en la base de datos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	/**
	 * Identificador único para cada ítem de factura registrado en la BD
	 */
	@Column(name = "item_id")
	private long atrId;
	
	/**
	 * Número de instancias de un mismo plato
	 */
	@Column(name = "item_cantidad")
	private Double atrCantidad;
	
	/**
	 * Precio de cada plato
	 */
	@Column(name = "item_precio")
	private Double atrPrecio;
	
	/**
	 * Identificador único del plato registrado en la BD.
	 */
	@Column(name = "item_producto_id")
	private Long atrIdPlato;
	
	/**
	 * Subtototal a pagar por cada intem de factura
	 */
	@Transient
	private Double atrSubtotal;
	
	/**
	 * Objeto de tipo plato que no será mapeado en la BD pero se utilizara para enviar la información 
	 * completa de este.
	 */
	@Transient
	private Plato objplato;
	
	/**
	 * Método que calcula el subtotal de cada ítem de factura multiplicando el número de instancias 
	 * de un mismo plato por el valor de cada uno de estos.
	 * @return. subtotal a pagar por cada item de factura.
	 */
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
