package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Item;
import com.usco.edu.service.IItemService;

@RestController
@RequestMapping(path = "item")
public class ItemRestController {

	@Autowired
	IItemService itemService;

	@GetMapping(path = "obtener-listado-item")
	public List<Item> obtenerListadoItem() {

		return itemService.obtenerListadoItem();

	}

	@GetMapping(path = "obtener-item-por-seccion/{codigo}")
	public List<Item> obtenerItemsPorSeccion(@PathVariable Integer codigo) {

		return itemService.obtenerItemsPorSeccion(codigo);

	}

	@GetMapping(path = "obtener-item/{codigo}")
	public Item obtenerItem(@PathVariable Integer codigo) {

		return itemService.obtenerItem(codigo);

	}

	@PostMapping(path = "registrar-item/{usuarioBd}")
	public int insertarItem(@RequestBody Item item, @PathVariable String usuarioBd) {

		return itemService.insertarItem(item, usuarioBd);

	}

	@PutMapping(path = "actualizar-item/{usuarioBd}")
	public int actualizarItem(@RequestBody Item item, @PathVariable String usuarioBd) {

		return itemService.actualizarItem(item, usuarioBd);

	}

	@PutMapping(path = "eliminar-item/{usuarioBd}")
	public int eliminarItem(@RequestBody Item item, @PathVariable String usuarioBd) {

		return itemService.eliminarItem(item, usuarioBd);

	}

}
