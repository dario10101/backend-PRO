package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;

/**
 * Esta interface define los métodos que va a consumir el controlador REST
 * @author Héctor Fabio Meneses
 *
 */
public interface ServicioClientes {
	/**
	 * El método listarClientes, realizara una busqueda de todos los clientes registrados en la aplicación.
	 * @return. Retornará una lista de todos todos los registros de la entidad "Cliente".
	 */
	public List<Cliente> listarClientes();	
	
	/**
	 * Este método permite buscar en la BD un cliente registrado en el sistema mediante su identificador único.
	 * @param idCliente. Es el identificador único del cliente en el sistema.
	 * @return. Retornará el cliente con el identificador especificado en el parámetro de búsqueda.
	 */
	public Cliente buscarClientePorId(Long idCliente);
	
	/**
	 * Este método permite el registro de un nuevo cliente en la BD del sistema.
	 * @param cliente. Es el nuevo cliente que se registrara en el sistema.
	 * @return. Retornará el cliente con cada uno de los atributos especificados en el registro.
	 */
	public Cliente crearCliente(Cliente cliente);	
	
	/**
	 * Este método permite permite al cliente mantener actulizada su información en el sistema.
	 * @param cliente. Cliente al cual se le desea actualizar la información.
	 * @return. Retornará el cliente con la información actualizada.
	 */
    public Cliente actualizarCliente(Cliente cliente);  
    
    /**
     * El método eliminarCliente, cambiara el estado de un cliente registrado en el sistema.
     * @param idCliente. Es el identificador único del cliente en el sistema.
     * @return. Retornará el cliente con el atributo statusCliente en disabled.
     */
    public Cliente eliminarCliente(Long idCliente);
    
    /**
     * El método activarCliente, cambiara el estado de un cliente que fue dado de baja con anterioridad.
     * @param idCliente. Es el identificador único del cliente en el sistema.
     * @return Retornará el cliente con el atributo statusCliente en activated.
     */
    public Cliente activarCliente(Long idCliente);
    
    /**
     * Este método validara las credenciales de un cliente que desea ingresar a al aplicación.
     * @param Cliente. Cliente que desea ingresar a la aplicación.
     * @return. si las credenciales son correctas devuelve el cliente registrado en el sistema, caso contrario
     * devuelve null
     */
	public Cliente validarCredenciales(Cliente cliente);
	
}
