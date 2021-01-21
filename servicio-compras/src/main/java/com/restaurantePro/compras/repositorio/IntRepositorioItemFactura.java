package com.restaurantePro.compras.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantePro.compras.entidad.ItemFactura;


/**
 * Esta interface permite la cominicación con la BD, a través de 
 * jpaRepository que define métodos para todas las operaciones CRUD de nuestras entidades.
 * 
 * @author Héctor Fabio Meneses
 *
 */

@Repository

public interface IntRepositorioItemFactura extends JpaRepository<ItemFactura,Long> {
	/**
	 * Este metodo permite realizar la búsqueda de un item de factura especifico por 
	 * medio del identicador único de un plato registrado en el sistema
	 * @param parIdPlato. Es identificador único de un plato registrado en el sistema
	 * @return. devuelve el Item de factura registrado en la BD con el identificador del plato
	 * ingresado por parámetro
	 */
	public ItemFactura findByAtrIdPlato(Long parIdPlato);

}
