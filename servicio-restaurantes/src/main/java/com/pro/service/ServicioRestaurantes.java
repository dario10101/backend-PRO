package com.pro.service;

import java.util.List;

import com.pro.entity.Restaurante;

/**
 * Servicios disponibles sobre los restaurantes
 * 
 * @author Ruben
 *
 */
public interface ServicioRestaurantes {

	/**
	 * Listar todos los restaurantes
	 * @return Lista de restaurantes encoontrados
	 */
	public List<Restaurante> listarRestaurantes();
	
	/**
	 * Buscar restaurante por su nit unico
	 * @param nit Identificador del restaurante
	 * @return Lista de restaurantes encoontrados
	 */
    public Restaurante buscarRestaurantePorNit(String nit); 
    
    /**
     * Buscar un restaurante por su nombre
     * @param nombreRest nombre del restaurante
     * @return Lista de restaurantes encoontrados
     */
    public List<Restaurante> buscarRestaurantePorNombre(String nombreRest);
    
    /**
     * Buscar restaurante por su status, activo o inactivo
     * @param status (ACTIVATED o DELETED)
     * @return Lista de restaurantes encoontrados
     */
    public List<Restaurante> buscarRestaurantePorStatus(String status);
    
    /**
     * Agregar un nuevo restaurante
     * @param rest Restaurante con sus datos configurados
     * @return Restaurante creado, si se crea correctamente
     */
    public Restaurante crearRestaurante(Restaurante rest);
    
    /**
     * Actualizar restaurante existente
     * @param rest Restaurante a modificar, con el mismo nit (no se puede cambiar)
     * @return Restaurante modificado
     */
    public Restaurante actualizarRestaurante(Restaurante rest);
    
    /**
     * Cambiar estado de un restaurante a DELETED
     * @param nitRestaurante Identificador del restaurante
     * @return Restaurante modificado
     */
    public Restaurante eliminarRestaurante(String nitRestaurante);
    
    /**
     * Cambiar estado de un restaurante a ACTIVATED
     * @param nitRestaurante Identificador del restaurante
     * @return Restaurante modificado
     */
    public Restaurante activarRestaurante(String nitRestaurante);
    
	
	
}
