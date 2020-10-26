package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Restaurante")
@Data 
@AllArgsConstructor @NoArgsConstructor @Builder
public class Restaurante {	

    @Id
    @Column(name = "id_rest")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private Long idRest;
    
    @Column(name = "nombre_rest")
    @NotEmpty (message = "El nombre no debe ser vacío")
    private String nombreRest;
    
    @Column(name = "desc_rest")
    private String descRest;
    
    @Column(name = "img_rest")
    private String imgRest;
    
    @Column(name = "telefono_rest")
    private String telefonoRest;
    
    @Column(name = "categoria_rest")
    private String categoriaRest;
    
    @Column(name = "status_rest")
    private String statusRest;

}