package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;

public interface ServicioPlatos {	

    public List<Plato> listarPlatos();
    public Plato buscarPlatoPorId(Long idPlato); 
    public List<Plato> buscarPlatoPorNombre(Long idRest, String nombrePlato);
    public List<Plato> buscarPlatoPorStatus(Long idRest, String statusPlato);
    public List<Plato> buscarPlatoPorCategoria(Long idRest, String categoriaPlato);
    
    public Plato crearPlato(Plato plato);    
    public Plato actualizarPlato(Plato Plato);
    public Plato eliminarPlato(Long idPlato);
    public Plato activarPlato(Long idPlato);
    public List<Plato> buscarPlatoPorRestaurante(Long idRest);	
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
}
