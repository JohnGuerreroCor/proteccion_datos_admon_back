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

import com.usco.edu.entities.AutorizacionItem;
import com.usco.edu.service.IAutorizacionItemService;

@RestController
@RequestMapping(path = "autorizacionItem")
public class AutorizacionItemRestController {

	@Autowired
	IAutorizacionItemService autorizacionItemService;

	@GetMapping(path = "obtener-listado-autorizacionItem")
	public List<AutorizacionItem> obtenerListadoAutorizacionItem() {

		return autorizacionItemService.obtenerListadoAutorizacionItem();

	}

	@GetMapping(path = "obtener-item-por-autorizacion/{codigo}")
	public List<AutorizacionItem> obtenerListadoItemPorAutorizacion(@PathVariable Integer codigo) {

		return autorizacionItemService.obtenerListadoItemPorAutorizacion(codigo);

	}

	@GetMapping(path = "obtener-autorizacionItem/{codigo}")
	public AutorizacionItem obtenerAutorizacionItem(@PathVariable Integer codigo) {

		return autorizacionItemService.obtenerAutorizacionItem(codigo);

	}

	@PostMapping(path = "registrar-autorizacionItem/{usuarioBd}")
	public int insertarAutorizacionItem(@RequestBody AutorizacionItem autorizacionItem,
			@PathVariable String usuarioBd) {

		return autorizacionItemService.insertarAutorizacionItem(autorizacionItem, usuarioBd);

	}

	@PutMapping(path = "actualizar-autorizacionItem/{usuarioBd}")
	public int actualizarAutorizacionItem(@RequestBody AutorizacionItem autorizacionItem,
			@PathVariable String usuarioBd) {

		return autorizacionItemService.actualizarAutorizacionItem(autorizacionItem, usuarioBd);

	}

	@PutMapping(path = "eliminar-autorizacionItem/{usuarioBd}")
	public int eliminarAutorizacionItem(@RequestBody AutorizacionItem autorizacionItem,
			@PathVariable String usuarioBd) {

		return autorizacionItemService.eliminarAutorizacionItem(autorizacionItem, usuarioBd);

	}

}
