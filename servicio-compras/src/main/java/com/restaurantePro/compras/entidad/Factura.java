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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fac_id")
	private Long atrIdFactua;
	@Column(name = "fac_numfactura",unique = true)
	private String atrNumeroFactura;
	@Column(name = "fac_descripcion")
	private String atrDescripcion;
	@Column(name = "fac_idcliente")
	private Long atrIdCliente;
	@Column(name = "fac_estado")
	private String atrEstado;
	@Column(name = "fac_fecha")
	private LocalDate atrFecha;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_items")
	private List<ItemFactura> listaItems;
	
	@Transient
	private Cliente objCliente;
	
	public String getAtrNumeroFactura() 
	{
		return "No: "+this.atrIdFactua;
	}
	
	public String getAtrDescripcion() 
	{
		return "Factura menu del dia";
	}
	
    @PrePersist
    public void prePersist() {
        this.atrFecha = LocalDate.now();
        System.out.println("\n\n dia del mes "+this.atrFecha.getDayOfMonth());
        System.out.println("\n\n mes "+this.atrFecha.getMonth().getValue());
        System.out.println("\n\n anio "+this.atrFecha.getYear());
       
    }

}
