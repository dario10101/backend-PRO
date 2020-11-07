package com.example.demo.entity;

import javax.persistence.Entity;
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

	private int id;
	
}
