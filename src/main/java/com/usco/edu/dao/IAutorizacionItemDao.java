package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.AutorizacionItem;

public interface IAutorizacionItemDao {
	
	public List<AutorizacionItem> obtenerListadoAutorizacionItem();
	
	public List<AutorizacionItem> obtenerListadoItemPorAutorizacion(Integer autorizacionCodigo);
	
	public AutorizacionItem obtenerAutorizacionItem(Integer codigo);
	
	public int insertarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd);
	
	public int actualizarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd);
	
	public int eliminarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd);

}
