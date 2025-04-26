package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Autorizacion;
import com.usco.edu.entities.Modulo;

public interface IAutorizacionService {
	
	public List<Modulo> obtenerListadoModulo();
	
	public List<Autorizacion> obtenerListadoAutorizacion();
	
	public Autorizacion obtenerAutorizacion(Integer codigo);
	
	public int insertarAutorizacion(Autorizacion autorizacion, String usuarioBd);
	
	public int actualizarAutorizacion(Autorizacion autorizacion, String usuarioBd);
	
	public int eliminarAutorizacion(Autorizacion autorizacion, String usuarioBd);

}
