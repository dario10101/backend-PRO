package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la entidad "Cliente" que será mapeada en la base datos en memoria(H2)
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
@Table (name = "Cliente")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {
	
	/**
	 * La decoración @Id: indica la propiedad que actúa como identificador
	 * 
	 * La decoración @Column: indica contra que columna de la tabla se mapea una propiedad
	 * 
	 * La decoración @GeneratedValue Se basa en una columna de base de datos con incremento automático y 
	 * permite que la base de datos genere un nuevo valor con cada operación de inserción. 
	 * 
	 * La decoración @NotEmpty: indica que el  elemento anotado no debe ser nulo ni vacío. 
	 */
	
	@Id
    @Column(name = "id_cliente")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idCliente;    
    
    @Column(name = "nombres_cliente")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombresCliente;
    
    
    @Column(name = "apellidos_cliente")
    //@NotEmpty (message = "El apellido no debe ser vacío")
    private String apellidosCliente;
    
    
    @Column(name = "correo_cliente")
    @NotEmpty (message = "El correo no debe ser vacío")
    private String correoCliente;
    
    @Column(name = "password_cliente")
    @NotEmpty (message = "Error en la contraseña")
    /**
     * Atributo passwordCliente: Es la contraseña del cliente para ingresar en el sistema
     */
    private String passwordCliente;
    
    @Column(name = "telefono_cliente")
    private String telefonoCliente;
    
    @Column(name = "celular_cliente")
    private String celularCliente;
    
    /**
     * Atributo statusCliente: Indica el estado del cliente dentro de la aplicación, 
     * puede tomar dos valores "ACTIVATED" O "DISABLED"
     *  
     */
    @Column(name = "status_cliente")
    private String statusCliente;
    
    /**
     * Atributo imgCliente: hace referencia a la foto del cliente
     */
    @Column(name = "img_cliente")
    private String imgCliente; 
    
    @Column(name = "direccion_cliente")
    @NotEmpty (message = "Error en la direccion")
    private String direccionCliente;
    
}
