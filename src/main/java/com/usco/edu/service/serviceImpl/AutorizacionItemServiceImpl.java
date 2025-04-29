package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAutorizacionItemDao;
import com.usco.edu.entities.AutorizacionItem;
import com.usco.edu.service.IAutorizacionItemService;

@Service
public class AutorizacionItemServiceImpl implements IAutorizacionItemService {

	@Autowired
	private IAutorizacionItemDao autorizacionItemDao;

	@Override
	public List<AutorizacionItem> obtenerListadoAutorizacionItem() {

		return autorizacionItemDao.obtenerListadoAutorizacionItem();

	}

	@Override
	public List<AutorizacionItem> obtenerListadoItemPorAutorizacion(Integer autorizacionCodigo) {

		return autorizacionItemDao.obtenerListadoItemPorAutorizacion(autorizacionCodigo);

	}

	@Override
	public AutorizacionItem obtenerAutorizacionItem(Integer codigo) {

		return autorizacionItemDao.obtenerAutorizacionItem(codigo);

	}

	@Override
	public int insertarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		return autorizacionItemDao.insertarAutorizacionItem(autorizacionItem, usuarioBd);

	}

	@Override
	public int actualizarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		return autorizacionItemDao.actualizarAutorizacionItem(autorizacionItem, usuarioBd);

	}

	@Override
	public int eliminarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		return autorizacionItemDao.eliminarAutorizacionItem(autorizacionItem, usuarioBd);

	}
}
