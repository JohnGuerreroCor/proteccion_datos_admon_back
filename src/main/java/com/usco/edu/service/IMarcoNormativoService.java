package com.usco.edu.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.entities.Normativa;
import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.entities.NormativaTipo;

public interface IMarcoNormativoService {

	public List<NormativaEntidadTipo> obtenerEntidadTipo();
	
	public List<NormativaEntidad> obtenerEntidad();
	
	public List<NormativaTipo> obtenerNormativaTipo();
	
	public List<NormativaMedio> obtenerMedio();
	
	public List<Normativa> obtenerNormativa();
	
	public List<NormativaEntidad> obtenerEntidadPorTipo(Integer entidadTipoCodigo);
	
	public List<NormativaExpide> obtenerExpidePorEntidad(Integer entidadCodigo);
	
	public void registrarNormativa(Normativa normativa, String sys);
	
	public void actualizarNormativa(Normativa normativa, String sys);
	
	public int eliminarNormativa(Normativa normativa, String sys);

	String subirArchivo(MultipartFile file, Integer perCodigo, Integer uaa, HttpServletRequest request);

	ByteArrayInputStream mirarArchivo(long archivoCodigo, HttpServletResponse response);

}
