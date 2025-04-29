package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Modulo;

public class ModuloRowMapper implements RowMapper<Modulo> {

	@Override
	public Modulo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Modulo modulo = new Modulo();
		modulo.setCodigo(rs.getInt("mod_codigo"));
		modulo.setNombre(rs.getString("mod_nombre"));
		modulo.setDescripcion(rs.getString("mod_descripcion"));
		modulo.setUrl(rs.getString("mod_url"));
		modulo.setSistemaCodigo(rs.getInt("sis_codigo"));
		modulo.setSistemaNombre(rs.getString("sis_nombre"));
		modulo.setEstado(rs.getInt("mod_estado"));
		return modulo;
	}
}
