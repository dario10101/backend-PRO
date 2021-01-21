package com.pro;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pro.entity.Plato;
import com.pro.repository.RepositorioPlatos;

@DataJpaTest
public class RepositorioPlatosMockTest {
	
	@Autowired
    private RepositorioPlatos miRepositorioPlatos;
	
	@Test
    public void buscarPorRestaurante(){
		System.out.println("\n\n -------------------------------  Iniciando prueba unitaria.\n\n");
		
		/*
		Plato pl1 = Plato.builder()
                .nombrePlato("Sancocho")
                .descPlato("De gallina muerta")
                .precioPlato(15000.0)
                .imgPlato("sin imagen")
                .categoriaPlato("Especial")
                .statusPlato("ACTIVATED")
                .cantidadPlato(15.0)
                .ingredientesPlato("ENTRADAS INGREDIENTES POSTRES")
                .restaurante(Restaurante.builder().idRest(2L).build())
                .build();

		miRepositorioPlatos.save(pl1);
		
        List<Plato> platos = miRepositorioPlatos.findByRestaurante(pl1.getRestaurante());
        
        
        System.out.println("\n\nCantidad encontrada del id_rest 1-> " + platos.size());
        for(int i = 0; i < platos.size(); i++) {
        	System.out.println("nombre: " + platos.get(i).getNombrePlato());
        }
        System.out.println("\n\n");
        //Assertions.assertThat(founds.size()).isEqualTo(3);
		*/

    }
	
	@Test
	public void buscarPorNombre()
	{
		Plato platoRecuperado = Plato.builder()
                .nombrePlato("Bandeja paisa")
                .descPlato("Plato especial del dia")
                .precioPlato(12.500)
                .imgPlato("Sin imagen")
                .categoriaPlato("especial")
                .statusPlato("ACTIVATE")
                .cantidadPlato(15.0)
                .ingredientesPlato("Frijoles, huevo, arroz, chicharron,salchicha")
                .restaurante(Restaurante.builder().idRest(1L).build())
                .build();

		miRepositorioPlatos.save(platoRecuperado);
		List<Plato> listaPlatos = miRepositorioPlatos.findByNombrePlato("Bandeja paisa");
		Assertions.assertThat(listaPlatos.size()).isEqualTo(1);
		
	}
	
}
