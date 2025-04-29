package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAutorizacionDao;
import com.usco.edu.entities.Autorizacion;
import com.usco.edu.service.IAutorizacionService;

@Service
public class AutorizacionServiceImpl implements IAutorizacionService {

	@Autowired
	private IAutorizacionDao autorizacionDao;

	@Override
	public List<Autorizacion> obtenerListadoAutorizacion() {
		
		return autorizacionDao.obtenerListadoAutorizacion();
		
	}

	@Override
	public Autorizacion obtenerAutorizacion(Integer codigo) {
		
		return autorizacionDao.obtenerAutorizacion(codigo);
		
	}

	@Override
	public int insertarAutorizacion(Autorizacion autorizacion, String usuarioBd) {
		
		return autorizacionDao.insertarAutorizacion(autorizacion, usuarioBd);
		
	}

	@Override
	public int actualizarAutorizacion(Autorizacion autorizacion, String usuarioBd) {
		
		return autorizacionDao.actualizarAutorizacion(autorizacion, usuarioBd);
		
	}

	@Override
	public int eliminarAutorizacion(Autorizacion autorizacion, String usuarioBd) {
		
		return autorizacionDao.eliminarAutorizacion(autorizacion, usuarioBd);
		
	}
	
}
