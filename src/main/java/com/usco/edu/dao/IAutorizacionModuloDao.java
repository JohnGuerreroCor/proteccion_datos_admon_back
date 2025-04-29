package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.AutorizacionModulo;
import com.usco.edu.entities.Modulo;
import com.usco.edu.entities.Sistema;

public interface IAutorizacionModuloDao {
	
	public List<Modulo> obtenerListadoModulo();
	
	public List<Modulo> obtenerListadoModuloPorSistema(Integer sistemaCodigo);
	
	public List<Sistema> obtenerListadoSistema();
	
	public List<AutorizacionModulo> obtenerListadoAutorizacionModulo();
	
	public List<AutorizacionModulo> obtenerListadoModulosPorAutorizacion(Integer autorizacionCodigo);
	
	public AutorizacionModulo obtenerAutorizacionModulo(Integer codigo);
	
	public int insertarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd);
	
	public int actualizarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd);
	
	public int eliminarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd);

}
