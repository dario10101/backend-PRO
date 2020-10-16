package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;
import com.pro.entity.Restaurante;

public interface ServicioPlatos {	

	
	/*
	 * Retorna todos los platos, incluidos los inactivos
	 * @return Lista con todos lo platos
	 */
    public List<Plato> listarPlatos();
    
    /*
	 * Busca un plato por el id y lo retorna
	 * @param idPlato Debe ser tipo Long
	 * @return Retorna el plato encontrado, null si no lo encuentra
	 */
    public Plato buscarPlatoPorId(Long idPlato); 
    
    /*
	 * Busca todos por el nombre y los retorna
	 * @param nombrePlato Cadena con el nombre del plato
	 * @return Retorna una lista con los platos que tengan ese nombre
	 */
    public List<Plato> buscarPlatoPorNombre(String nombrePlato);
    
    /*
	 * Busca todos por el status y los retorna, CREATED o DELETED
	 * @param statusPlato Cadena con el status del plato
	 * @return Retorna una lista con los platos que tengan ese status
	 */
    public List<Plato> buscarPlatoPorStatus(String statusPlato);
    
    /*
	 * Busca todos por la categoria y los retorna
	 * @param statusPlato Cadena con el status de la categoria
	 * @return Retorna una lista con los platos que tengan la categoria
	 */
    public List<Plato> buscarPlatoPorCategoria(String categoriaPlato);
    
    /*
	 * Almacena un nuevo plato en la base de datos, en caso de que no exista.
	 * @param plato recive la entidad del plato
	 * @return si po puede agregar lo retorna, sino, retorna null
	 */
    public Plato crearPlato(Plato plato);  
    
    /*
	 * Edita un nuevo plato de la base de datos, en caso de que exista.
	 * @param plato recive la entidad del plato
	 * @return si lo encuentra y puede editar lo retorna, sino, retorna null
	 */
    public Plato actualizarPlato(Plato Plato);
    
    /*
	 * Cambia el estadod de un plato a DELETED
	 * @param idPlato id del plato a iliminar, tipo Long
	 * @return El plato si lo puede eliminar, sino, retorna null
	 */
    public Plato eliminarPlato(Long idPlato);
    
    /*
	 * Cambia el estadod de un plato a ACTIVATED
	 * @param idPlato id del plato a activar, tipo Long
	 * @return El plato si lo puede activar, sino, retorna null
	 */
    public Plato activarPlato(Long idPlato);
    
    /*
	 * Busca todos los platos que perteneecn a un restaurante
	 * @param restaurante del cual se necesita buscar sus platos
	 * @return Lista con todos los platos de ese restaurante
	 */
    public List<Plato> buscarPlatoPorRestaurante(Restaurante restaurante);
    
    /*
	 * Actualiza la cantidad disponible de un plato en especifico
	 * @param idPlato contiene el id del plato que vamos a actualizar el stock
	 * @param cantidad la cantidad que le vamos a sumar/restar al plato
	 * @return Retorna el plato con sus datos actualizados
	 */
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
}
