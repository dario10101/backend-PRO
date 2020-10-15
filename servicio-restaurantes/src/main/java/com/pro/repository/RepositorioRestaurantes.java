package com.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Restaurante;


@Repository
public interface RepositorioRestaurantes extends JpaRepository<Restaurante, Long>{

}
