package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;
import com.pro.entity.Semanario;

public interface ServicioPlatos {
	
	//Definicion de los dias
	public String CONST_DOMINGO = "1";
	public String CONST_LUNES = "2";
	public String CONST_MARTES = "3";
	public String CONST_MIERCOLES = "4";
	public String CONST_JUEVES = "5";
	public String CONST_VIERNES = "6";
	public String CONST_SABADO = "7";

    public List<Plato> listarPlatos();
    public Plato buscarPlatoPorId(Long idPlato); 
    public List<Plato> buscarPlatoPorNombre(String nit, String nombrePlato);
    public List<Plato> buscarPlatoPorStatus(String nit, String statusPlato);
    public List<Plato> buscarPlatoPorCategoria(String nit, String categoriaPlato);
    
    
    /**
     * 
     * @param plato
     * @return
     */
    public Plato crearPlato(Plato plato);    
    public Plato actualizarPlato(Plato Plato);
    public Plato eliminarPlato(Long idPlato);
    public Plato activarPlato(Long idPlato);
    public List<Plato> buscarPlatoPorRestaurante(String nit);	
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
    public List<String>buscarCategoriasPorRestaurante(String nit);
    
    
	//----------------------------------------------------------------------------------------------
	//---------- METODOS DEL SEMANARIO--------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
    public List<Plato> buscarPlatosPorDia(String nitRest, String dia, String categoria);
    public Plato agregarPlatoSemanario(Long idPlato, String dias, String separador, boolean reiniciar);
    public Plato eliminarPlatoSemanario(Long idPlato);
    public List<Semanario> listarSemanario();
    public String buscarSemanarioPlato(Long idPlato);
    
}
