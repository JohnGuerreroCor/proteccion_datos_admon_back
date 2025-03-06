package com.usco.edu.service;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletResponse;

import com.usco.edu.dto.Foto;

public interface IFotoService {

	ByteArrayInputStream mirarFoto(String codigo, HttpServletResponse response);
	
	Foto mirarFotoAntigua(String codigo);

}
