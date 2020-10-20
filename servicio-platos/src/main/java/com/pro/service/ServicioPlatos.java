package com.pro.service;

import java.util.List;

import com.pro.entity.Plato;
import com.pro.model.Restaurante;

public interface ServicioPlatos {	

    public List<Plato> listarPlatos();
    public Plato buscarPlatoPorId(Long idPlato); 
    public List<Plato> buscarPlatoPorNombre(String nombrePlato);
    public List<Plato> buscarPlatoPorStatus(String statusPlato);
    public List<Plato> buscarPlatoPorCategoria(String categoriaPlato);
    public Plato crearPlato(Plato plato);    
    public Plato actualizarPlato(Plato Plato);
    public Plato eliminarPlato(Long idPlato);
    public Plato activarPlato(Long idPlato);
    public List<Plato> buscarPlatoPorRestaurante(Long idRest);	
    public Plato actualizarStock(Long iPplato, Double cantidad);
    
}
