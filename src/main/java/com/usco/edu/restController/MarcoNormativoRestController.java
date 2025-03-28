package com.usco.edu.restController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.entities.Normativa;
import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.service.IMarcoNormativoService;

@RestController
@RequestMapping(path = "normativo")
public class MarcoNormativoRestController {

	@Autowired
	IMarcoNormativoService marcoNormativoService;

	@GetMapping(path = "obtener-entidad-tipo")
	public List<NormativaEntidadTipo> obtenerEntidadTipo() {

		return marcoNormativoService.obtenerEntidadTipo();

	}
	
	@GetMapping(path = "obtener-entidad")
	public List<NormativaEntidad> obtenerEntidad() {

		return marcoNormativoService.obtenerEntidad();

	}
	
	@GetMapping(path = "obtener-normativa-tipo")
	public List<NormativaTipo> obtenerNormativaTipo() {

		return marcoNormativoService.obtenerNormativaTipo();

	}
	
	@GetMapping(path = "obtener-medio")
	public List<NormativaMedio> obtenerMedio() {

		return marcoNormativoService.obtenerMedio();

	}
	
	@GetMapping(path = "obtener-normativa")
	public List<Normativa> obtenerNormativa() {

		return marcoNormativoService.obtenerNormativa();

	}
	
	@GetMapping(path = "obtener-entidades-por-tipo/{codigo}")
	public List<NormativaEntidad> obtenerEntidadPorTipo(@PathVariable Integer codigo) {

		return marcoNormativoService.obtenerEntidadPorTipo(codigo);

	}
	
	@GetMapping(path = "obtener-expide-por-entidad/{codigo}")
	public List<NormativaExpide> obtenerExpidePorEntidad(@PathVariable Integer codigo) {

		return marcoNormativoService.obtenerExpidePorEntidad(codigo);

	}

	@PostMapping("registrar-normativa/{sys}/{perCodigo}/{uaa}")
	public void registrarSoporteExpedicion(@PathVariable String sys, @PathVariable Integer perCodigo,
			@RequestPart MultipartFile archivo, HttpServletRequest request, @PathVariable Integer uaa,
			@RequestParam String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		Normativa normativa;
		try {
			normativa = objectMapper.readValue(json, Normativa.class);
			normativa.setAnexo(marcoNormativoService.subirArchivo(archivo, perCodigo, uaa, request));
			marcoNormativoService.registrarNormativa(normativa, sys);

		} catch (JsonParseException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@GetMapping("mirar-archivo/{codigo}")
	public ResponseEntity<InputStreamResource> mirarArchivo(@PathVariable Long codigo, HttpServletResponse response)
			throws Exception {
		ByteArrayInputStream stream = marcoNormativoService.mirarArchivo(codigo, response);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\" documento.pdf\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

	}

}
