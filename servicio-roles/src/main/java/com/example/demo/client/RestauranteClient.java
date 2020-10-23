package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "servicio-restaurantes")
@RequestMapping (value = "/restaurantes")
public interface RestauranteClient {

}
