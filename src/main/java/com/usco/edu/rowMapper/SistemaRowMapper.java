package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Sistema;

public class SistemaRowMapper implements RowMapper<Sistema> {

	@Override
	public Sistema mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sistema sistema = new Sistema();
		sistema.setCodigo(rs.getInt("sis_codigo"));
		sistema.setNombre(rs.getString("sis_nombre"));
		sistema.setEstado(rs.getInt("sis_estado"));
		return sistema;
	}
}
