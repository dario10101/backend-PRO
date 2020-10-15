package com.pro.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurante {
    private Long idRest;
    
    private String nombreRest;
    
    private String descRest;
    
    private String imgRest;
    
    private String categoriaRest;
    
    private String statusRest;
}
