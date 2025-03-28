package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usco.edu.dao.IDocumentoDao;
import com.usco.edu.dao.IMarcoNormativoDao;
import com.usco.edu.dto.RespuestaSubirArchivo;
import com.usco.edu.dto.RespuestaVerArchivo;
import com.usco.edu.entities.Documento;
import com.usco.edu.entities.Normativa;
import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.feing.EnviarArchivoClient;
import com.usco.edu.service.IMarcoNormativoService;

@Service
public class MarcoNormativoServiceImpl implements IMarcoNormativoService {

	@Autowired
	private IMarcoNormativoDao marcoNormativoDao;

	@Autowired
	private IDocumentoDao documentoDao;

	@Autowired
	private EnviarArchivoClient enviarArchivo;

	@Override
	public List<NormativaEntidadTipo> obtenerEntidadTipo() {

		return marcoNormativoDao.obtenerEntidadTipo();

	}
	
	@Override
	public List<NormativaEntidad> obtenerEntidad() {
		
		return marcoNormativoDao.obtenerEntidad();
		
	}

	@Override
	public List<NormativaTipo> obtenerNormativaTipo() {
		
		return marcoNormativoDao.obtenerNormativaTipo();
		
	}
	
	@Override
	public List<NormativaMedio> obtenerMedio() {
		
		return marcoNormativoDao.obtenerMedio();
		
	}
	
	@Override
	public List<Normativa> obtenerNormativa() {
		
		return marcoNormativoDao.obtenerNormativa();
		
	}

	@Override
	public List<NormativaEntidad> obtenerEntidadPorTipo(Integer entidadTipoCodigo) {

		return marcoNormativoDao.obtenerEntidadPorTipo(entidadTipoCodigo);

	}
	
	@Override
	public List<NormativaExpide> obtenerExpidePorEntidad(Integer entidadCodigo) {
		
		return marcoNormativoDao.obtenerExpidePorEntidad(entidadCodigo);
		
	}

	@Override
	public void registrarNormativa(Normativa normativa, String sys) {

		marcoNormativoDao.registrarNormativa(normativa, sys);

	}

	@Override
	public void actualizarNormativa(Normativa normativa, String sys) {

		marcoNormativoDao.actualizarNormativa(normativa, sys);

	}

	@Override
	public int eliminarNormativa(Normativa normativa, String sys) {

		return marcoNormativoDao.eliminarNormativa(normativa, sys);

	}

	@Override
	public String subirArchivo(MultipartFile file, Integer perCodigo, Integer uaa, HttpServletRequest request) {

		String err = "";
		if (!file.isEmpty()) {

			if (isValido(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))) {

				Documento documento = new Documento();
				documento.setDocNombreArchivo(file.getOriginalFilename());
				documento.setPerCodigo("" + perCodigo);
				documento.setDocCliente("ProteccionDatos");
				documento.setDocContenido("Normativa");
				documento.setDocExtension("pdf");
				documento.setDocIp(request.getRemoteAddr());
				documento.setDocSesion(request.getSession().getId());
				documento.setModCodigo(35);// CAMBIAR PARA PRODUCCION
				documento.setTdocCodigo(38);// CAMBIAR PARA PRODUCCION
				documento.setUaaCodigo(uaa);

				String Key = documentoDao.obtenerTokenDocumento(
						documento.getModCodigo().toString() + documento.getUaaCodigo() + documento.getPerCodigo());

				System.out.println("datos: " + documento.getModCodigo().toString() + documento.getUaaCodigo()
						+ documento.getPerCodigo());
				ObjectMapper objectMapper = new ObjectMapper();
				RespuestaSubirArchivo respuesta = new RespuestaSubirArchivo();
				try {
					respuesta = enviarArchivo.subirArchivo(file, Key, objectMapper.writeValueAsString(documento));
					System.out.println(respuesta.getMensaje());
					System.out.println(respuesta.getIdDocumento());
					err = respuesta.getMensaje();
					if (!respuesta.isEstado()) {

						System.out.println("Ocurrio un error: " + respuesta.getMensaje());
					}

				} catch (Exception e) {

					System.out.println("Ocurrio un error: " + e);
					return null;
				}
				System.out.println("Creado" + respuesta.getMensaje());
				return respuesta.getIdDocumento() + "";

			} else {

				System.out.println("Ocurrio un error" + err);
				return null;
			}

		} else {
			System.out.println("Ocurrio un error" + err);
			return null;
		}
	}

	@Override
	public ByteArrayInputStream mirarArchivo(long archivoCodigo, HttpServletResponse response) {

		String Key = documentoDao.obtenerTokenDocumento(archivoCodigo + "");

		RespuestaVerArchivo respuesta = new RespuestaVerArchivo();

		try {
			respuesta = enviarArchivo.mostrarArchivo(archivoCodigo, Key);

			byte[] archivoBytes = Base64.getDecoder().decode(respuesta.getBase64().split(",")[1]);
			return new ByteArrayInputStream(archivoBytes);

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public boolean isValido(String nombre) {
		String expresion = "^([[a-zA-Z][0-9]_]{2,150})$";
		try {
			Pattern p = Pattern.compile(expresion);
			Matcher matcher = p.matcher(nombre);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

}
