package com.restaurantePro.compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal de la aplicación
 * @SpringBootApplication @EnableDiscoveryClient y @EnableFeignClients. Son decoraciones necesarias para que se 
 * ejecute la aplicación  de forma correcta, debido a que esta se encuentra desarrollada con
 * una arquitectura de microservicios y para ello se utilizaron las herramientas que nos brinda
 * netflix para resolver las principales necesidades de una arquitectura distribuida.
 * 
 * 
 * @author Héctor Fabio Meneses
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServicioComprasApplication {
	/**
	 * 
	 * @param args. Argumentos del programa 
	 */

	public static void main(String[] args) {
		SpringApplication.run(ServicioComprasApplication.class, args);
	}

}
