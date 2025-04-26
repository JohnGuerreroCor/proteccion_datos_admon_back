package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ISeccionDao;
import com.usco.edu.entities.Seccion;
import com.usco.edu.entities.SeccionTipo;
import com.usco.edu.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService {

	@Autowired
	private ISeccionDao seccionDao;

	@Override
	public List<Seccion> obtenerListadoSeccion() {
		
		return seccionDao.obtenerListadoSeccion();
		
	}
	
	@Override
	public List<SeccionTipo> obtenerListadoSeccionTipo() {
		
		return seccionDao.obtenerListadoSeccionTipo();
		
	}

	@Override
	public Seccion obtenerSeccion(Integer codigo) {
		
		return seccionDao.obtenerSeccion(codigo);
		
	}

	@Override
	public int insertarSeccion(Seccion seccion, String usuarioBd) {
		
		return seccionDao.insertarSeccion(seccion, usuarioBd);
		
	}

	@Override
	public int actualizarSeccion(Seccion seccion, String usuarioBd) {
		
		return seccionDao.actualizarSeccion(seccion, usuarioBd);
		
	}

	@Override
	public int eliminarSeccion(Seccion seccion, String usuarioBd) {
		
		return seccionDao.eliminarSeccion(seccion, usuarioBd);
		
	}
	
}
