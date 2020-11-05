package com.restaurantePro.compras.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantePro.compras.entidad.ItemFactura;



@Repository

public interface IntRepositorioItemFactura extends JpaRepository<ItemFactura,Long> {
	public ItemFactura findByAtrIdPlato(Long parIdPlato);

}
