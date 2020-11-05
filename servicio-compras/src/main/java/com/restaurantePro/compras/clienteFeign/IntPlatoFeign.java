package com.restaurantePro.compras.clienteFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurantePro.compras.modelo.Plato;


@FeignClient(name = "servicio-platos")
@RequestMapping (value = "/platos")
public interface IntPlatoFeign {
	@GetMapping(value = "buscar-por-id/{idplato}")
    public ResponseEntity<Plato> buscarPlatoPorId(@PathVariable("idplato") Long idPlato);
	@PutMapping (value = "/actualizar-cantidad/{id}")
    public ResponseEntity<Plato> actualizarCantidadPlato(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad);

}
