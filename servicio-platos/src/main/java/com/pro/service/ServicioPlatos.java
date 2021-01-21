package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;
import com.pro.entity.Semanario;

/**
 * Interface que ofrece los servicios disponibles sobre los platos
 * 
 * @author Ruben
 *
 */
public interface ServicioPlatos {
	
	//Constantes para identificar los dias de la semana	
	public String CONST_DOMINGO = "1";
	public String CONST_LUNES = "2";
	public String CONST_MARTES = "3";
	public String CONST_MIERCOLES = "4";
	public String CONST_JUEVES = "5";
	public String CONST_VIERNES = "6";
	public String CONST_SABADO = "7";
	
	
	/**
	 * Lista todos lo platos de todos los restaurantes
	 * @deprecated
	 * @return
	 */
    public List<Plato> listarPlatos();
    
    /**
     * Busca un plato por su identificador
     * @param idPlato identificador del plato
     * @return Plato encontrado
     */
    public Plato buscarPlatoPorId(Long idPlato); 
    
    /**
     * Todos los platos con un mismo nombre de un restaurante
     * @param nit Nit del restaurante
     * @param nombrePlato nombre solicitado
     * @return Platos encontrados
     */
    public List<Plato> buscarPlatoPorNombre(String nit, String nombrePlato);
    
    /**
     * Todos los platos con un mismo status, de un restaurante en especifico
     * @param nit Identificadore del restaurante
     * @param statusPlato (ACTIVATED o DELETED)
     * @return
     */
    public List<Plato> buscarPlatoPorStatus(String nit, String statusPlato);
    
    /**
     * Todos los platos con una misma categoria de un restaurante 
     * @param nit Identificadore del restaurante
     * @param categoriaPlato Categoria del plato
     * @return Platos encontrados
     */
    public List<Plato> buscarPlatoPorCategoria(String nit, String categoriaPlato);
    
    
    /**
     * Agregar un plato a la base de datos
     * @param plato Plato configurado
     * @return Retorna el mismo plato si se creó correctamente
     */
    public Plato crearPlato(Plato plato);   
    
    /**
     * Modificar un plato
     * @param Plato Plato configurado
     * @return Retorna el mismo plato si se logra actualizar
     */
    public Plato actualizarPlato(Plato Plato);
    
    /**
     * Cambiar el estado de un plato de ACTIVATED a DELETED
     * @param idPlato Identificador del plato
     * @return Plato plato eliminado
     */
    public Plato eliminarPlato(Long idPlato);
    
    /**
     * Cambiar estado de un plato de DELETED a ACTIVATED
     * @param idPlato identificador del plato
     * @return rotorna el plato encontrado con el id
     */
    public Plato activarPlato(Long idPlato);
    
    /**
     * Buscar todos los platos de un restaurante
     * @param nit identificador del restaurante
     * @return Todos los platos del restaurante
     */
    public List<Plato> buscarPlatoPorRestaurante(String nit);	
    
    /**
     * Actualizar la cantidad disponible de un plato
     * @param iPplato Identificador del plato
     * @param cantidad Cantidad a quitar-aumentar del plato
     * @return Plato encontrado con el id
     */
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
    /**
     * Buscar todas las categorias disponibles de un restaurante
     * @param nit identificador del restaurante
     * @return 
     */
    public List<String>buscarCategoriasPorRestaurante(String nit);
    
    
    /*
	//----------------------------------------------------------------------------------------------
	//---------- METODOS DEL SEMANARIO--------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
    */
    
    /**
     * Buscar todos los platos que se ofrecen en cierto dia (1,2,3,4,5,6,7) en un restaurante
     * @param nitRest identificador del restaurante 
     * @param dia valor entre 1 y 7
     * @param categoria categoria de platos (null para todas las categorias)
     * @return Platos encontrados
     */
    public List<Plato> buscarPlatosPorDia(String nitRest, String dia, String categoria);
    
    /**
     * Agregar platos para ser ofrecidos ciertos dias de la semana
     * @param idPlato Identificador del plato
     * @param dias En forma de cadena, dias (de 1 a 7) que se va a ofrecer
     * @param separador Caracter que separa los dias en el parametro dias
     * @param reiniciar Borrar los datos actuales o solo actualizarlos
     * @return Plato encontrado con el identificador
     */
    public Plato agregarPlatoSemanario(Long idPlato, String dias, String separador, boolean reiniciar);
    
    /**
     * Quitar un plato de todos los dias del semanario a los que pertenezca
     * @param idPlato Identificador del plato
     * @return Plato encontrado
     */
    public Plato eliminarPlatoSemanario(Long idPlato);
    
    /**
     * Lista de todos los semanarios
     * @deprecated
     * @return Semanarios
     */
    public List<Semanario> listarSemanario();
    
    /**
     * Buscar los dias en que se ofrece un plato
     * @param idPlato Identificador del plato
     * @return Cadena con los dias (1 a 7)
     */
    public String buscarSemanarioPlato(Long idPlato);
    
}
