package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Rol;
import com.example.demo.model.Usuario;

public interface ServicioRoles {
	
	public List<Rol> listarRoles();
	
	public Rol validarRol(Usuario user);
	
	
	
}
