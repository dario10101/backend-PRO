package com.restaurantePro.compras.modelo;

import lombok.Builder;
import lombok.Data;

/**
 * Esta clase hace referencia a la definida en el microservicio "servicio-platos" y es 
 * necesaria junto con la herramienta Feign suministrada por netflix para la integracion con 
 * el microservicio "servicio-compras" 
 * 
 * La decoración @builder: nos creará el método público build y el objeto estático Builder con todos sus atributos y métodos
 * 
 * La decoración @Data: permite crear los getters, setters, el constructor y algún otro método como equals, canEquals,
 * hashCode y toString de forma automática.
 * 
 * @author Héctor Fabio Meneses
 *
 */

@Data
@Builder
public class Plato {

    private Long idPlato;    
    private String nombrePlato;
    private String descPlato;
    private Double precioPlato; 
    private String imgPlato;    
    private String categoriaPlato;  
    private String statusPlato;
    private Double cantidadPlato;
    private String nitRest;
    
}
