package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Item;

public interface IItemService {
	
	public List<Item> obtenerListadoItem();
	
	public List<Item> obtenerItemsPorSeccion(Integer seccionCodigo);
	
	public Item obtenerItem(Integer codigo);
	
	public int insertarItem(Item item, String usuarioBd);
	
	public int actualizarItem(Item item, String usuarioBd);
	
	public int eliminarItem(Item item, String usuarioBd);

}
