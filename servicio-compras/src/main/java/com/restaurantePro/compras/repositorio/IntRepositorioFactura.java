package com.restaurantePro.compras.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurantePro.compras.entidad.Factura;
/**
 * Esta interface permite la cominicación con la BD, a través de 
 * jpaRepository que define métodos para todas las operaciones CRUD de nuestras entidades.
 * 
 * @author Héctor Fabio Meneses
 *
 */
@Repository
public interface IntRepositorioFactura extends JpaRepository<Factura,Long>{
	/**
	 * Este método realiza la búsqueda de una factura especifica asociada a un único restaurante.
	 * @param parIdFactura. Identificador único de la factura registrada en el sistema.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @return.La factura correspondiente al identificador especificado en el parámetro
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrIdFactua = ?1 AND f.atrIdRestaurante = ?2")
	public Factura buscarFactuaPorId(Long parIdFactura,String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas asociadas a cliente en un restaurante epecifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @param atrIdCliente. Identificador único del cliente registrado en el sistema.
	 * @return. lista de facturas expedidas al cliente cuyo identifador coincide con especificado en 
	 * el Parámetro
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrIdCliente = ?1 AND f.atrIdRestaurante = ?2")
	public List<Factura> buscarPorIdCliente(Long atrIdCliente,String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de una factura especifica asociada a un único restaurante.
	 * @param atrNumeroFactura. Número de factura mediante el cual se realizará la búsqueda
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura
	 * @return. La factura correspondiente al número de factura especificado en el parámetro
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrNumeroFactura = ?1 AND f.atrIdRestaurante = ?2")
	public Factura buscarPorNumeroFactura(String atrNumeroFactura, String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas asociadas a un restaurante especifico.
	 * @param parIdRestaurante.  Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturaas asociadas al restaurante cuyo identificador coincide con el 
	 * especificado en el parámetro.
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrIdRestaurante = ?1")
	public List<Factura> ListarFacturasPorIdRestaurante(String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas ANULADAS asociadas a un restaurante especifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ANULADAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro.
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrEstado = ' ANULADA ' AND f.atrIdRestaurante = ?1")
	public List<Factura> findByAtrEstado(String parIdRestaurante);

	/**
	 * Este método realiza la búsqueda de todas facturas ACTIVAS asociadas a un restaurante especifico.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ACTIVAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro.
	 */
	@Query("SELECT f FROM Factura f WHERE f.atrEstado = ' CREADO ' AND f.atrIdRestaurante = ?1")
	public List<Factura> findByAtrEstado1(String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas facturas ACTIVAS asociadas a un restaurante especifico, 
	 * comprendidas en un rango de fechas definidas por el usuario.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las facturas ACTIVAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro y correspoden al rango de fechas establecido.
	 */	
	@Query("SELECT f FROM Factura f WHERE f.atrFecha BETWEEN ?1 AND ?2 AND f.atrIdRestaurante = ?3 AND f.atrEstado = ' CREADO '")
	public List<Factura> findReporte(String parFechaInicio, String parFechaFin, String parIdRestaurante);
	
	/**
	 * Este método realiza la búsqueda de todas las fechas asociadas a las diferentes facturas de un 
	 * restaurante especifico y que están comprendidas en un rango de fechas definidas por el usuario.
	 * @param parIdRestaurante. Identificador único del restaurante al cual está asociada la factura.
	 * @return. lista de todas las fechas correspondinetes a las facturas ACTIVAS asociadas al restaurante cuyo identificador 
	 * coincide con el especificado en el parámetro y correspoden al rango de fechas establecido.
	 */	
	@Query("SELECT DISTINCT f.atrFecha FROM Factura f WHERE f.atrFecha BETWEEN ?1 AND ?2 AND f.atrIdRestaurante = ?3 AND f.atrEstado = ' CREADO '")
	public List<String> obtenerFechasParaReportes(String parFechaInicio, String parFechaFin, String parIdRestaurante);
	
}
