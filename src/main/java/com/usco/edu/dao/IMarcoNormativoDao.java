package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Normativa;
import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.entities.NormativaTipo;

public interface IMarcoNormativoDao {
	
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

}