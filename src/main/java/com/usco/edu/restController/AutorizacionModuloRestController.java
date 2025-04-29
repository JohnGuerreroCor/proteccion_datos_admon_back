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

import com.usco.edu.entities.AutorizacionModulo;
import com.usco.edu.entities.Modulo;
import com.usco.edu.entities.Sistema;
import com.usco.edu.service.IAutorizacionModuloService;

@RestController
@RequestMapping(path = "autorizacionModulo")
public class AutorizacionModuloRestController {

	@Autowired
	IAutorizacionModuloService autorizacionModuloService;

	@GetMapping(path = "obtener-listado-modulo")
	public List<Modulo> obtenerListadoModulo() {

		return autorizacionModuloService.obtenerListadoModulo();

	}

	@GetMapping(path = "obtener-listado-modulo-por-sistema/{codigo}")
	public List<Modulo> obtenerListadoModuloPorSistema(@PathVariable Integer codigo) {

		return autorizacionModuloService.obtenerListadoModuloPorSistema(codigo);

	}

	@GetMapping(path = "obtener-listado-sistema")
	public List<Sistema> obtenerListadoSistema() {

		return autorizacionModuloService.obtenerListadoSistema();

	}

	@GetMapping(path = "obtener-listado-autorizacionModulo")
	public List<AutorizacionModulo> obtenerListadoAutorizacionModulo() {

		return autorizacionModuloService.obtenerListadoAutorizacionModulo();

	}

	@GetMapping(path = "obtener-listado-modulo-por-autorizacion/{codigo}")
	public List<AutorizacionModulo> obtenerListadoModulosPorAutorizacion(@PathVariable Integer codigo) {

		return autorizacionModuloService.obtenerListadoModulosPorAutorizacion(codigo);

	}

	@GetMapping(path = "obtener-AutorizacionModulo/{codigo}")
	public AutorizacionModulo obtenerAutorizacionModulo(@PathVariable Integer codigo) {

		return autorizacionModuloService.obtenerAutorizacionModulo(codigo);

	}

	@PostMapping(path = "registrar-autorizacionModulo/{usuarioBd}")
	public int insertarAutorizacionModulo(@RequestBody AutorizacionModulo autorizacionModulo,
			@PathVariable String usuarioBd) {

		return autorizacionModuloService.insertarAutorizacionModulo(autorizacionModulo, usuarioBd);

	}

	@PutMapping(path = "actualizar-autorizacionModulo/{usuarioBd}")
	public int actualizarAutorizacionModulo(@RequestBody AutorizacionModulo autorizacionModulo,
			@PathVariable String usuarioBd) {

		return autorizacionModuloService.actualizarAutorizacionModulo(autorizacionModulo, usuarioBd);

	}

	@PutMapping(path = "eliminar-autorizacionModulo/{usuarioBd}")
	public int eliminarAutorizacionModulo(@RequestBody AutorizacionModulo autorizacionModulo,
			@PathVariable String usuarioBd) {

		return autorizacionModuloService.eliminarAutorizacionModulo(autorizacionModulo, usuarioBd);

	}

}
