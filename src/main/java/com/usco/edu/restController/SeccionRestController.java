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

import com.usco.edu.entities.Seccion;
import com.usco.edu.entities.SeccionTipo;
import com.usco.edu.service.ISeccionService;

@RestController
@RequestMapping(path = "seccion")
public class SeccionRestController {

	@Autowired
	ISeccionService seccionService;

	@GetMapping(path = "obtener-listado-seccion")
	public List<Seccion> obtenerListadoSeccion() {

		return seccionService.obtenerListadoSeccion();

	}

	@GetMapping(path = "obtener-listado-seccion-tipo")
	public List<SeccionTipo> obtenerListadoSeccionTipo() {

		return seccionService.obtenerListadoSeccionTipo();

	}

	@GetMapping(path = "obtener-seccion/{codigo}")
	public Seccion obtenerSeccion(@PathVariable Integer codigo) {

		return seccionService.obtenerSeccion(codigo);

	}

	@PostMapping(path = "registrar-seccion/{usuarioBd}")
	public int insertarSeccion(@RequestBody Seccion seccion, @PathVariable String usuarioBd) {

		return seccionService.insertarSeccion(seccion, usuarioBd);

	}

	@PutMapping(path = "actualizar-seccion/{usuarioBd}")
	public int actualizarSeccion(@RequestBody Seccion seccion, @PathVariable String usuarioBd) {

		return seccionService.actualizarSeccion(seccion, usuarioBd);

	}

	@PutMapping(path = "eliminar-seccion/{usuarioBd}")
	public int eliminarSeccion(@RequestBody Seccion seccion, @PathVariable String usuarioBd) {

		return seccionService.eliminarSeccion(seccion, usuarioBd);

	}

}
