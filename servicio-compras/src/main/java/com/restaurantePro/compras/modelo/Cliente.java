package com.restaurantePro.compras.modelo;

import lombok.Data;

@Data
public class Cliente 
{
 /*
	private Long atrIdCliente;
	private String atrNombre;
	private String atrApellido;
	private String atrDireccion;
	private String atrUrlFoto;
	private String atrCorreoElectronico;
	private String atrNumeroTelefono;
	private String atrNumeroCelular;
	private String atrEstado;
	private String atrContraseña;*/
	
    private Long idCliente;    
    private String nombresCliente;
    private String apellidosCliente;
    private String correoCliente;
    private String passwordCliente;
    private String telefonoCliente;
    private String celularCliente;
    private String statusCliente;
    private String imgCliente; 
    private String direccionCliente;

}
