package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Imagen")  
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Imagen {

	@Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
}
