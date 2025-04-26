package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Seccion;

public class SeccionRowMapper implements RowMapper<Seccion>{

	@Override
	public Seccion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seccion seccion = new Seccion();
		seccion.setCodigo(rs.getInt("sec_codigo"));
		seccion.setNombre(rs.getString("sec_nombre"));
		seccion.setOrden(rs.getInt("sec_orden"));
		seccion.setSeccionTipoCodigo(rs.getInt("set_codigo"));
		seccion.setSeccionTipoNombre(rs.getString("set_nombre"));
		seccion.setEstado(rs.getInt("sec_estado"));
		return seccion;
	}
}
