package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Autorizacion;

public class AutorizacionRowMapper implements RowMapper<Autorizacion>{

	@Override
	public Autorizacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Autorizacion autorizacion = new Autorizacion();
		autorizacion.setCodigo(rs.getInt("aut_codigo"));
		autorizacion.setTitulo(rs.getString("aut_titulo"));
		autorizacion.setNormativaCodigo(rs.getInt("nor_codigo"));
		autorizacion.setNormativaNumero(rs.getString("nor_numero"));
		autorizacion.setNormativaTipoNombre(rs.getString("not_nombre"));
		autorizacion.setNormativaFechaCreacion(rs.getDate("nor_fecha_creacion"));
		autorizacion.setVersion(rs.getString("aut_version"));
		autorizacion.setDescripcion(rs.getString("aut_descripcion"));
		autorizacion.setFechaCreacion(rs.getDate("aut_fecha_creacion"));
		autorizacion.setAnexo(rs.getInt("aut_anexo_requerido"));
		autorizacion.setEstado(rs.getInt("aut_estado"));
		return autorizacion;
	}
}
