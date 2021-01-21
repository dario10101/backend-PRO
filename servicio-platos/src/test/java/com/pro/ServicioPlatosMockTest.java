package com.pro;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pro.entity.Plato;
import com.pro.repository.RepositorioPlatos;
import com.pro.repository.RepositorioSemanario;
import com.pro.service.ServicioPlatos;
import com.pro.service.ServicioPlatosImpl;

@SpringBootTest
public class ServicioPlatosMockTest {
	
	@Mock
	RepositorioPlatos objRepositorioPlatos;
	
	@Mock
	RepositorioSemanario objRepositorioSemanario;
	
	private ServicioPlatos objServicioPlatos;
	
	@BeforeEach
	public void propararPrueba() 
	{
		MockitoAnnotations.initMocks(this);
		objServicioPlatos = new ServicioPlatosImpl(objRepositorioPlatos, objRepositorioSemanario);
		Plato objPlato = Plato.builder()
				.idPlato(1L)
                .nombrePlato("Ollucos")
                .descPlato("Plato del dia")
                .precioPlato(5000.0)
                .imgPlato("sin imagen")
                .categoriaPlato("Especial")
                .statusPlato("ACTIVATED")
                .cantidadPlato(15.0)
                .build();
		Mockito.when(objRepositorioPlatos.findById(1L)).thenReturn(Optional.of(objPlato));		
		Mockito.when(objRepositorioPlatos.findByNombrePlato("Ollucos")).thenReturn(List.of(objPlato));
		Mockito.when(objRepositorioPlatos.save(objPlato)).thenReturn(objPlato);
	}
	
	@Test
	public void realizarBusquedaPorNombre() 
	{
		List<Plato> listaPlatos = objServicioPlatos.buscarPlatoPorNombre("1", "Ollucos");
		Assertions.assertThat(listaPlatos.get(0).getNombrePlato()).isEqualTo("Ollucos");
	}
	
	@Test
	public void realizarBusquedaPorId() 
	{
		Plato platoRecuperado = objServicioPlatos.buscarPlatoPorId(1L);
		Assertions.assertThat(platoRecuperado.getNombrePlato()).isEqualTo("Ollucos");
	}
	
	@Test
	public void eliminarPlato() 
	{
		Plato platoEliminado = objServicioPlatos.eliminarPlato(1L);
		System.out.println("\n\n****************************************************\n\n");
		System.out.println("\n\n ESTADO :"+ platoEliminado.getStatusPlato());
		System.out.println("\n\n****************************************************\n\n");
		Assertions.assertThat(platoEliminado.getNombrePlato()).isEqualTo("Ollucos");
	}
	
	@Test
	public void actualizarPlato() 
	{
		Plato platoEncontrado = objServicioPlatos.buscarPlatoPorId(1L);
		platoEncontrado.setNombrePlato("frijoles");
		Plato PlatoActualizado = objServicioPlatos.actualizarPlato(platoEncontrado);
		System.out.println("\n\n****************************************************\n\n");
		System.out.println("\n\n Nombre del plato :"+PlatoActualizado.getNombrePlato());
		System.out.println("\n\n****************************************************\n\n");
		Assertions.assertThat(PlatoActualizado.getNombrePlato()).isEqualTo("frijoles");
	}
	

}
