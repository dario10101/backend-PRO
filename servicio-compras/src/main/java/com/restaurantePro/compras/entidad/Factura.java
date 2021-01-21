package com.restaurantePro.compras.entidad;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.restaurantePro.compra.modelo.Cliente;
import com.restaurantePro.compras.modelo.Cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//import lombok.NoArgsConstructor;

/**
 * Clase que representa la entidad "tbl_facturas" que será mapeada en la base datos en memoria(H2)
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
@Table(name = "tbl_facturas")
@Data
@AllArgsConstructor
@Builder

public class Factura {
	/**
	 * La decoración @Id: indica la propiedad que actúa como identificador
	 * 
	 * La decoración @Column: indica contra que columna de la tabla se mapea una propiedad
	 * 
	 * La decoración @GeneratedValue Se basa en una columna de base de datos con incremento automático y 
	 * permite que la base de datos genere un nuevo valor con cada operación de inserción. 
	 * 
	 * La decoración @Transient significa que el atributo no será mapeado en la base de datos
	 * 
	 * La decoración @PrePersist permite que la fecha de la factura se registre automáticamente antes
	 * de insertar en la base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * Indentificador único con el cual se registro la factura en el sistema
	 */
	@Column(name = "fac_id")
	private Long atrIdFactua;
	
	/**
	 * Identicador único del restaurante al cual esta asociada la factura de venta.
	 */
	@Column(name = "fac_idrestaurante")
	private String atrIdRestaurante;
	
	/**
	 * Número de la factura el cual debe ser único para cada una de ellas.
	 */
	@Column(name = "fac_numfactura",unique = true)
	private String atrNumeroFactura;
	@Column(name = "fac_descripcion")
	private String atrDescripcion;	
	/**
	 * Identificador del cliente que realizó la compra y que esta asociado a la factura.
	 */
	@Column(name = "fac_idcliente")
	private Long atrIdCliente;
	
	/**
	 * Estado de la factura en la BD de la aplicación, puede diponer de dos estados: CREADO O ANULADA
	 */
	@Column(name = "fac_estado")
	private String atrEstado;
	
	/**
	 * Fecha en que se realizó la expedición de la factura
	 */
	@Column(name = "fac_fecha")
	private String atrFecha;
	
	/**
	 * Total a pagar por el cliente que realiza la compra
	 */
	@Column(name = "fac_totalventa")
	private double atrTotalVenta;
	
	/**
	 * Lista de todos los platos registrados en el pedido.
	 */
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_items")
	private List<ItemFactura> listaItems;
	
	/**
	 * Cliente asociado a la factura.
	 */
	@Transient
	private Cliente objCliente;
	
	public Factura() 
	{
		this.atrTotalVenta = 0.0;
	}
	
	public String getAtrNumeroFactura() 
	{
		return "No: "+this.atrIdFactua;
	}
	
	public String getAtrDescripcion() 
	{
		return "Factura menu del dia";
	}
	
	/**
	 * Captura la fecha actual y la registra de forma automática en en la BD. 
	 */
    @PrePersist
    public void prePersist() {
    	LocalDate fecha = LocalDate.now();
        this.atrFecha = fecha.getYear()+"-"+fecha.getMonthValue()+"-"+fecha.getDayOfMonth();
       
    }

}
