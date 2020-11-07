package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioImagenesImpl implements ServicioImagenes {

	@Override
	public String agregarImagenPlato(Long idPlato, String imagenCodificada) throws Exception {
		String appdir = System.getProperty("user.dir") + File.separator;
		String txtPath = "vacio por ahora";
		String savePath = appdir + "imagenfacildebuscar2.txt";
		
		//FileInputStream inputStream = new FileInputStream(txtPath);
		
		System.out.println("La hora de la verdad: ");
		
		FileOutputStream fileOutputStream = new FileOutputStream(savePath);
		fileOutputStream.write(1);
		fileOutputStream.close();
		
		
		
		return null;
	}

	@Override
	public String obtenerImagenPlato(String idPlato) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	@Override
	public String agregarImagenRestaurante(String nitRest, String imagenCodificada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerImagenRestaurante(String nitRest) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
