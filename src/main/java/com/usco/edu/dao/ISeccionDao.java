package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Seccion;
import com.usco.edu.entities.SeccionTipo;

public interface ISeccionDao {
	
	public List<Seccion> obtenerListadoSeccion();
	
	public List<SeccionTipo> obtenerListadoSeccionTipo();
	
	public Seccion obtenerSeccion(Integer codigo);
	
	public int insertarSeccion(Seccion seccion, String usuarioBd);
	
	public int actualizarSeccion(Seccion seccion, String usuarioBd);
	
	public int eliminarSeccion(Seccion seccion, String usuarioBd);

}
