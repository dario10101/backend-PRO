package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;

public interface ServicioPlatos {	

    public List<Plato> listarPlatos();
    public Plato buscarPlatoPorId(Long idPlato); 
    public List<Plato> buscarPlatoPorNombre(String nit, String nombrePlato);
    public List<Plato> buscarPlatoPorStatus(String nit, String statusPlato);
    public List<Plato> buscarPlatoPorCategoria(String nit, String categoriaPlato);
    
    public Plato crearPlato(Plato plato);    
    public Plato actualizarPlato(Plato Plato);
    public Plato eliminarPlato(Long idPlato);
    public Plato activarPlato(Long idPlato);
    public List<Plato> buscarPlatoPorRestaurante(String nit);	
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
    public List<String>buscarCategoriasPorRestaurante(String nit);
    
}
