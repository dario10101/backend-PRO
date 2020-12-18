package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;

/**
 * Esta interface sirve para acceder a los datos de la entidad "Cliente" de la BD H2, a través de 
 * jpaRepository que define métodos para todas las operaciones CRUD de nuestras entidades.
 * 
 * @author Héctor Fabio Meneses
 *
 */
@Repository
public interface RepositorioClientes extends JpaRepository<Cliente, Long> {
	
	/**
	 * El método findByNombresCliente, realizara una búsqueda en la BD H2 de todos los clientes con un
	 * nombre en particular.
	 * @param nombre_cliente: Es el parámetro por el cual el método realizara la búsqueda de los clientes
	 * que coincidan en el campo nombres_cliente con el valor asignado a este.
	 * @return List<Cliente>: El método retornara una lista de todos los clientes que coincidan en el campo
	 *  nombres_cliente con el valor asignado al parámetro de búsqueda.
	 */
	public List<Cliente> findByNombresCliente(String nombre_cliente);
	
	/**
	 * El método findByApellidosCliente, realizara una búsqueda en la BD H2 de todos los clientes con un
	 * apellido en particular.
	 * @param nombre_cliente: Es el parámetro por el cual el método realizara la búsqueda de los clientes
	 * que coincidan en el campo apellidos_cliente con el valor asignado a este.
	 * @return List<Cliente>: El método retornara una lista de todos los clientes que coincidan en el campo
	 *  apellidos_cliente con el valor asignado al parámetro de búsqueda.
	 */
	
	public List<Cliente> findByApellidosCliente(String apellidos_cliente);
	
	/**
	 * El método findByCorreoCliente, realizara la busqueda en la entidad "cliente" de la BD H2  
	 * de un cliente especifico.
	 * @param email_cliente: Es el parámetro por el cual el método realizara la búsqueda del cliente
	 * que coincida en el campo correo_cliente con el valor asignado a este.
	 * @return Cliente: El método retornara la información del cliente que coincida en el campo
	 *  correo_cliente con el valor asignado al parámetro de búsqueda.
	 */
	public Cliente findByCorreoCliente(String email_cliente);
	
}
