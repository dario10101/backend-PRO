package com.pro.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.client.RestauranteClient;
import com.pro.entity.Plato;
import com.pro.entity.Semanario;
import com.pro.model.Restaurante;
import com.pro.model.Semana;
import com.pro.repository.RepositorioPlatos;
import com.pro.repository.RepositorioSemanario;

import lombok.RequiredArgsConstructor;

/**
 * 
 * Implementacion de los servicios disponibles de los platos
 * 
 * @author Ruben
 *
 */
@Service
@RequiredArgsConstructor
public class ServicioPlatosImpl implements ServicioPlatos{	

	/**
	 * Abstraccion del medio de almacenamiento de datos, de la entidad de platos
	 */
	private final RepositorioPlatos miRepositorioPlatos;
	
	/**
	 * Abstraccion del medio de almacenamiento de datos, de la entidad de Semanario
	 */
	private final RepositorioSemanario miRepositorioSemanario;
		
	/**
	 * Referencia al servicio de restaurantes, utilizando inyeccion de dependencias
	 * automatizado por el framework 
	 */
	@Autowired	
	RestauranteClient clienteRestaurante;
		
	@Override
	public List<Plato> listarPlatos() {
		List<Plato> platosPrueba = miRepositorioPlatos.findAll();		
		
		//traer los restaurantes de los platos del otro servicio
		asignarRestaurantesAPlatos(platosPrueba);	
		
		
		return platosPrueba;
	}	
	
	@Override
	public List<Plato> buscarPlatoPorRestaurante(String nit) {  
		//Datos no validos
		if(nit == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}			
		
		// Buscar los platos de un restaurante
		List<Plato> platosEncontrados =  miRepositorioPlatos.findByNitRest(nit);
		
		// No tener en cuenta los platos eliminados
		this.eliminarPorStatus("DELETED", platosEncontrados);
		
		if(platosEncontrados.size() > 0) {				
			for(Plato plato: platosEncontrados) {
				plato.setRestaurante(rest);
			}
		}
		
		return platosEncontrados;
	}
	
	@Override
	public Plato buscarPlatoPorId(Long idPlato) {	
		Plato platoEncontrado = miRepositorioPlatos.findById(idPlato).orElse(null);
		
		//encontrar el restaurante
		if(platoEncontrado != null) {
			String nitRestaurante = platoEncontrado.getNitRest(); 
			
			//el restaurante existe
			if(nitRestaurante != null){
				Restaurante rest = this.buscarRestaurante(nitRestaurante);
				platoEncontrado.setRestaurante(rest);
			}
		}
		
		return platoEncontrado;
	}
		
	@Override
	public List<Plato> buscarPlatoPorNombre(String nit, String nombrePlato) {	
		//Datos no validos
		if(nit == null || nombrePlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getNombrePlato().equalsIgnoreCase(nombrePlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		// semanario, que dias se ofrece el plato
		this.configurarSemanario(platosEncontrados, nit);
		
		return platosEncontrados;
	}
	
	@Override
	public List<Plato> buscarPlatoPorStatus(String nit, String statusPlato) {
		//Datos no validos
		if(nit == null || statusPlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getStatusPlato().equals(statusPlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		// semanario, que dias se ofrece el plato
		this.configurarSemanario(platosEncontrados, nit);
		
		return platosEncontrados;
	}

	@Override
	public List<Plato> buscarPlatoPorCategoria(String nit, String categoriaPlato){
		//Datos no validos
		if(nit == null || categoriaPlato == null) {
			System.out.println("\nError: null\n");
			return null;
		}
		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nit);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}		
		
		List<Plato> platosPrueba = miRepositorioPlatos.findByNitRest(nit);		
		List<Plato> platosEncontrados = new ArrayList<Plato>();				
		
		for(Plato plato: platosPrueba) {
			if(plato.getCategoriaPlato().equals(categoriaPlato)) {
				plato.setRestaurante(rest);
				platosEncontrados.add(plato);
			}
		}
		
		// No tener en cuenta los platos eliminados
		this.eliminarPorStatus("DELETED", platosEncontrados);
		
		// semanario, que dias se ofrece el plato
		this.configurarSemanario(platosEncontrados, nit);
		
		return platosEncontrados;
	}
			
	@Override
	public Plato crearPlato(Plato plato) {
		//construir plato valido
		this.validarPlato(plato);			
        		
		//verificar si existe
		Plato plato_encontrado = null;
		if(plato.getIdPlato() != null)
			plato_encontrado = buscarPlatoPorId(plato.getIdPlato());
		
		//verificar por nombre
		
		//el plato ya existe
		if (plato_encontrado != null){
			System.out.println("\nPlato exixtente\n");
            return null;
        }			
		
		//Validacion del restaurante
		if(plato.getNitRest() != null) {
			Restaurante rest = this.buscarRestaurante(plato.getNitRest());
			
			//El restaurante no existe
			if(rest == null) {
				System.out.println("\nEl restaurante no existe\n");
				return null;
			}
			
			plato.setRestaurante(rest);
		}		
		
		//System.out.println("\n\nid del plato: " + plato.getIdPlato() + "\n\\n");
        return miRepositorioPlatos.save(plato);
	}

	@Override
	public Plato actualizarPlato(Plato plato) {
		Plato plato_encontrado = buscarPlatoPorId(plato.getIdPlato());
        
		if (plato_encontrado == null){
            return null;
        }
		
		//validar el restaurante
		if(plato.getNitRest() != null) {
			Restaurante rest = this.buscarRestaurante(plato.getNitRest());
			
			//El restaurante no existe
			if(rest == null) {
				System.out.println("\nEl restaurante no existe\n");
				return null;
			}
			
			plato_encontrado.setRestaurante(rest);
        }
		
        plato_encontrado.setNombrePlato(plato.getNombrePlato());
        plato_encontrado.setDescPlato(plato.getDescPlato());
        plato_encontrado.setPrecioPlato(plato.getPrecioPlato());
        plato_encontrado.setImgPlato(plato.getImgPlato());
        plato_encontrado.setCategoriaPlato(plato.getCategoriaPlato());
        plato_encontrado.setStatusPlato(plato.getStatusPlato());
        plato_encontrado.setCantidadPlato(plato.getCantidadPlato());
        
        if(plato.getNitRest() != null)
        	plato_encontrado.setNitRest(plato.getNitRest());
                
        return miRepositorioPlatos.save(plato_encontrado);
	}

	@Override
	public Plato eliminarPlato(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        plato_encontrado.setStatusPlato("DELETED");
        return miRepositorioPlatos.save(plato_encontrado);
	}
	
	@Override
	public Plato activarPlato(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        plato_encontrado.setStatusPlato("ACTIVATED");
        return miRepositorioPlatos.save(plato_encontrado);
	}

	@Override
	public Plato actualizarStock(Long idPlato, Double cantidad) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        if (null == plato_encontrado){
            return null;
        }
        
        Double stock =  plato_encontrado.getCantidadPlato() + cantidad;
        plato_encontrado.setCantidadPlato(stock);
        return miRepositorioPlatos.save(plato_encontrado);
	}
	
	@Override
	public List<String> buscarCategoriasPorRestaurante(String nit) {		
		// Buscar los platos de un restaurante
		List<Plato> platosEncontrados = this.buscarPlatoPorRestaurante(nit);
		// Categorias encontradas
		List<String> categorias = new ArrayList<String>();
		
		String categoria;
		
		// Datos incorrectos
		if(platosEncontrados != null) {
			for(Plato plato: platosEncontrados) {
				// Tiene categoria:
				categoria = plato.getCategoriaPlato(); 
				if(categoria != null) {
					//Si no está en la lista, la agrega
					if(!categorias.contains(categoria)) {
						categorias.add(categoria);
					}
				}
			}
		}
		
		return categorias;
	}
		
	
	//----------------------------------------------------------------------------------------------
	//---------- METODOS DEL SEMANARIO--------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
		
	@Override
	public List<Plato> buscarPlatosPorDia(String nitRest, String dia, String categoria) {		
		// traer el restaurante del otro microservicio
		Restaurante rest = this.buscarRestaurante(nitRest);
		
		//No existe el restaurante
		if(rest == null) {
			System.out.println("\nEl restaurante no existe\n");
			return null;
		}
		
		//platos de ese restaurante
		List<Semanario> semanarios = miRepositorioSemanario.findByNitRest(nitRest);		
		String diaValido = this.validarDia(dia);
		
		//Validar si el plato se ofrece el dia
		List<Plato> platosEncontrados = new ArrayList<Plato>();
		String diasEncontrado = "";
		for(Semanario sem: semanarios) {
			diasEncontrado = sem.getDias(); 
			if(diasEncontrado.contains(diaValido)) {						
				platosEncontrados.add(this.construirPlato(sem.getPlato()));
			}
		}
		
		// No tener en cuenta los platos eliminados
		this.eliminarPorStatus("DELETED", platosEncontrados);
		
		//filtrar por categoria
		if(categoria != null) {
			if(!categoria.equals("")) {
				platosEncontrados = this.filtrarPorCategoria(categoria, platosEncontrados);
			}
		}
		System.out.println("platosEncontrados -> " + platosEncontrados);
		
		//Asignar el restaurante a los platos
		for(Plato plato: platosEncontrados) {
			plato.setRestaurante(rest);			
		}
		
		//System.out.println("\n" + platosEncontrados + "\n");
		
		// semanario, que dias se ofrece el plato
		this.configurarSemanario(platosEncontrados, nitRest);
				
		return platosEncontrados;
	}

	@Override
	public Plato agregarPlatoSemanario(Long idPlato, String dias, String separador, boolean reiniciar) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        
		//el id del plato es incorrecto
		if (plato_encontrado == null){
            return null;
        }
		//Restaurante del plato no existe por alguna razon
		if (plato_encontrado.getRestaurante() == null){
            return null;
        }
		
		//validacion de los dias
		String[] arregloDias = dias.split(separador);
		
		if(!this.validarDias(arregloDias)) {
			System.out.println("Dias no validos");
			return null;
		}
		
		Semanario semanario_encontrado = this.miRepositorioSemanario.findByPlato(plato_encontrado);
		
		System.out.println("\nsemanario --> " + semanario_encontrado + " <--\n");
		
		// borrar los datos antiguos y sobreescribir los nuevos
		if(reiniciar) {			
			semanario_encontrado = this.sobreEscribirSemanario(semanario_encontrado, plato_encontrado, dias);
		}
		//agregar los datos nuevos a los antiguos
		else {			
			semanario_encontrado = this.agregarSemanarioSinSobreEscribir(semanario_encontrado, plato_encontrado, arregloDias);
		}				
		
		if(semanario_encontrado != null) {
			this.miRepositorioSemanario.save(semanario_encontrado);
		}
		
		return plato_encontrado;
	}
	
	@Override
	public Plato eliminarPlatoSemanario(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
		
		//el id del plato es incorrecto
		if (plato_encontrado == null){
            return null;
        }
		
		Semanario semanario_encontrado = this.miRepositorioSemanario.findByPlato(plato_encontrado);
		
		if(semanario_encontrado != null) {
			this.miRepositorioSemanario.delete(semanario_encontrado);
		}
		
		return plato_encontrado;
	}

	@Override
	public List<Semanario> listarSemanario() {
		return this.miRepositorioSemanario.findAll();
	}
	
	@Override
	public String buscarSemanarioPlato(Long idPlato) {
		Plato plato_encontrado = buscarPlatoPorId(idPlato);
        String dias = "";
		
		//el id del plato es correcto
		if (plato_encontrado != null){
            Semanario semanario_encontrado = this.miRepositorioSemanario.findByPlato(plato_encontrado);
            if(semanario_encontrado != null) {
            	if(semanario_encontrado.getDias() != null) {
            		dias += semanario_encontrado.getDias();
            	}
            }
        }
		
		return dias;
	}
	
	
	//----------------------------------------------------------------------------------------------
	//---------- METODOS PRIVADOS-------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
	
	/**
	 * Actualizar los dias en que se ofrece un plato, borrando los datos viejos
	 * @param semanario_encontrado
	 * @param plato_encontrado
	 * @param dias
	 * @return Semanario nuevo
	 */
	private Semanario sobreEscribirSemanario(Semanario semanario_encontrado, Plato plato_encontrado, String dias) {
		if(semanario_encontrado == null) {
			semanario_encontrado = Semanario.builder()
					.plato(plato_encontrado)
					.nitRest(plato_encontrado.getRestaurante().getNitRest())
					.dias(dias)
					.build();					
		}else {
			semanario_encontrado.setDias(dias);
		}
		return semanario_encontrado;
	}
	
	/**
	 * Actualizar los dias en que se ofrece un plato, conservando los datos viejos
	 * @param semanario_encontrado
	 * @param plato_encontrado
	 * @param diasNuevos
	 * @return Semanario actualizado
	 */
	private Semanario agregarSemanarioSinSobreEscribir(Semanario semanario_encontrado, Plato plato_encontrado, String[] diasNuevos) {
		if(semanario_encontrado != null && plato_encontrado != null) {
			String[] diasAntiguos = semanario_encontrado.getDias().split(",");
			String dias = "";
			
			//agregar dias antiguos
			for(int i = 0; i < diasAntiguos.length; i++) {
				dias += diasAntiguos[i] + ",";
			}
			
			//Agregar dias nuevos si no existen
			for(int i = 0; i < diasNuevos.length; i++) {
				if(!dias.contains(diasNuevos[i])) {
					dias += diasNuevos[i] + ",";
				}
			}
			
			semanario_encontrado.setDias(dias);				
		}
		return semanario_encontrado;
	}
	
	/**
	 * Construir un plato usando el metodo builder() proporcionado por el framework, 
	 * para no tener problemas al almacenarlo en la base de datos
	 * @param platoBase plato que se va a clonar
	 * @return Plato creado a partir de otro
	 */
	private Plato construirPlato(Plato platoBase) {
		Plato plAux = Plato.builder()
				.idPlato(platoBase.getIdPlato())
				.precioPlato(platoBase.getPrecioPlato())
				.nombrePlato(platoBase.getNombrePlato())
				.nitRest(platoBase.getNitRest())
				.statusPlato(platoBase.getStatusPlato())
				.descPlato(platoBase.getDescPlato())
				.imgPlato(platoBase.getImgPlato())
				.categoriaPlato(platoBase.getCategoriaPlato())
				.cantidadPlato(platoBase.getCantidadPlato())
				.build();
		
		return plAux;
	}
	
	/**
	 * validar los atributos de un plato, arreglando valores que puedan estar mal
	 * @param platoBase
	 * @return Plato valido
	 */
	private Plato validarPlato(Plato platoBase) {
		
		//validar status
		if (platoBase.getStatusPlato() == null) {
			platoBase.setStatusPlato("ACTIVATED");
		}
		if(!(platoBase.getStatusPlato().equals("ACTIVATED") || platoBase.getStatusPlato().equals("DELETED"))) {
			platoBase.setStatusPlato("ACTIVATED");
		}
		
		//validar cantidad
		Double cantidad = platoBase.getCantidadPlato();
		if(cantidad == null){
			platoBase.setCantidadPlato(0.0);
		}
		
		//validar precio
		if(platoBase.getPrecioPlato() == null) {
			platoBase.setPrecioPlato(0.0);
		}
				
		return platoBase;
	}
	
	/**
	 * Establecer comunicacion con el servicio de restaurantes, bara buscar un restaurante con un nit
	 * @param nit
	 * @return
	 */
	private Restaurante buscarRestaurante(String nit) {
		Restaurante rest = null;
		try {
			rest = clienteRestaurante.buscarRestaurantePorId(nit).getBody();
		}catch(Exception e) {
			return null;
		}
		return rest;
	}
	
	/**
	 * Eliminar los platos de una lista con un status
	 * @param status status a eliminar de la lista
	 * @param Lista que se va a actualizar
	 * @deprecated Intentar hacerlo directamente con una consulta a la base de datos
	 */
	private void eliminarPorStatus(String status, List<Plato> platos){
		if(platos != null && status != null) {
			for(int i = 0; i < platos.size(); i++) {
				if(platos.get(i).getStatusPlato().equals(status)) {
					platos.remove(i);
				}
			}
		}
	}
	
	/**
	 * Crear una nueva lista con los platos de una categoria en especifico
	 * @param categoria categoria solicitada
	 * @param platos lista original de platos
	 * @return lista depurada de platos
	 * @deprecated Intentar hacerlo directamente con una consulta a la base de datos
	 */
	private List<Plato> filtrarPorCategoria(String categoria, List<Plato> platos) {
		List<Plato> platosAux = new ArrayList<Plato>();
		
		if(platos != null && categoria != null) {			
			for(int i = 0; i < platos.size(); i++) {
				if(platos.get(i).getCategoriaPlato().equals(categoria)) {
					//platos.remove(i);
					platosAux.add(platos.get(i));
				}
			}
		}
		return platosAux;
	}
	
	/**
	 * Calcula el dia de hoy segun el sistema, en caso de que el dia sea nulo
	 * @param dia Si es nulo, retorna el dia actual
	 * @return Dia valido
	 */
	private String validarDia(String dia) {
		String diaValido = dia;
		
		if(diaValido == null) {
			Calendar fecha = new GregorianCalendar(); 
			int diaHoy = fecha.get(Calendar.DAY_OF_WEEK); 
			
			diaValido = String.valueOf(diaHoy);
		}
		
		return diaValido;
	}
	
	/**
	 * A una lista de platos les asigna su restaurante, dependiendo de su parametro del nit del restaurante
	 * @param platos platos que necesitan su restaurante
	 */
	private void asignarRestaurantesAPlatos(List<Plato> platos){
		//TODO mejorar eficiencia de este codigo
		Restaurante rest;
		String nitTemp;
		
		if(platos.size() > 0) {				
			for(Plato plato: platos) {
				nitTemp = plato.getNitRest();
				if(nitTemp != null) {					
					rest = this.buscarRestaurante(nitTemp);
					plato.setRestaurante(rest);
				}
			}
		}
	}
	
	/**
	 * Verificar si una lista de dias, es correcta, osea con datos de 1 - 7
	 * @param dias
	 * @return true o false
	 */
	private boolean validarDias(String[] dias) {
		boolean validos = false;
		
		if(dias != null) {
			validos = true;
			for(int i = 0; i < dias.length; i++) {
				//verificar si el dia i es valido
				if(!(dias[i].equals(CONST_LUNES)      ||
					 dias[i].equals(CONST_MARTES)     ||
					 dias[i].equals(CONST_MIERCOLES)  ||
					 dias[i].equals(CONST_JUEVES)     ||
					 dias[i].equals(CONST_VIERNES)    ||
					 dias[i].equals(CONST_SABADO)     ||
				     dias[i].equals(CONST_DOMINGO)) )
				{
					validos = false;
					break;
				}
			}
		}
		
		return validos;
	}

	/**
	 * A una lista de platos de un restaurante, se le configura la semana, que indica que dias se ofrece y que dias nó
	 * @param platos Platos a configurar
	 * @param nitRest identificador de un restauranre
	 */
	private void configurarSemanario(List<Plato> platos, String nitRest) {
		List<Semanario> semanarios = miRepositorioSemanario.findByNitRest(nitRest);	
		Plato plato = null;
		Plato platoAux = null;
		
		if(semanarios == null || platos == null) {
			System.out.println("error: null 1");
			return;
		}
	
		if(platos.size() <= 0) {
			System.out.println("error: null 2");
			return;
		}		
		
		for(int i = 0; i < platos.size(); i++) {
			plato = platos.get(i);
			for(int j = 0; j < semanarios.size(); j++) {
				platoAux = semanarios.get(j).getPlato();
				
				if(plato.getIdPlato() == platoAux.getIdPlato()) {
					Semana sem = crearSemana(semanarios.get(j).getDias());
					plato.setSemanario(sem);
				}
			}
			if(plato.getSemanario() == null) {
				plato.setSemanario(crearSemana(""));
			}
		}
	}
	
	/**
	 * Configurar una clase Semana con los dias que se va a ofrecer un plato
	 * @param dias dias que se va a ofrecer en forma de cadena
	 * @return Objeto semana con los dias que se ofrece, en true o false
	 */
	private Semana crearSemana(String dias) {
		boolean lunes = false;
		boolean martes = false;
		boolean miercoles = false;
		boolean jueves = false;
		boolean viernes = false;
		boolean sabado = false;
		boolean domingo = false;
		
		if(dias.contains(CONST_LUNES)) {	lunes = true;	}
		if(dias.contains(CONST_MARTES)) {	martes = true;	}
		if(dias.contains(CONST_MIERCOLES)) {	miercoles = true;	}
		if(dias.contains(CONST_JUEVES)) {	jueves = true;	}
		if(dias.contains(CONST_VIERNES)) {	viernes = true;	}
		if(dias.contains(CONST_SABADO)) {	sabado = true;	}
		if(dias.contains(CONST_DOMINGO)) {	domingo = true;	}
		
		Semana sem = new Semana();
		sem.setLunes(lunes);
		sem.setMartes(martes);
		sem.setMiercoles(miercoles);
		sem.setJueves(jueves);
		sem.setViernes(viernes);
		sem.setSabado(sabado);
		sem.setDomingo(domingo);
		return sem;
	}
	

		
	
}
