package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rol;

/**
 * Abstrae informacion del medio de almacenamiento seleccionado para almacenar la
 * informacion de los roles.
 * 
 * Funcionalidad proporcionada por el framework
 * 
 * @author Ruben
 *
 */
@Repository
public interface RepositorioRoles extends JpaRepository<Rol, Long>{

}
