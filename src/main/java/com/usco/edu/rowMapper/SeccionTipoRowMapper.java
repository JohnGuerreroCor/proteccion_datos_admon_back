package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.SeccionTipo;

public class SeccionTipoRowMapper implements RowMapper<SeccionTipo>{

	@Override
	public SeccionTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SeccionTipo seccionTipo = new SeccionTipo();
		seccionTipo.setCodigo(rs.getInt("set_codigo"));
		seccionTipo.setNombre(rs.getString("set_nombre"));
		seccionTipo.setEstado(rs.getInt("set_estado"));
		return seccionTipo;
	}
}
