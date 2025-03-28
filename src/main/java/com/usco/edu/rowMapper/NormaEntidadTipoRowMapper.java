package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaEntidadTipo;

public class NormaEntidadTipoRowMapper implements RowMapper<NormativaEntidadTipo>{

	@Override
	public NormativaEntidadTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormativaEntidadTipo normaEntidad = new NormativaEntidadTipo();
		normaEntidad.setCodigo(rs.getInt("net_codigo"));
		normaEntidad.setNombre(rs.getString("net_nombre"));
		normaEntidad.setEstado(rs.getInt("net_estado"));
		return normaEntidad;
	}
}
