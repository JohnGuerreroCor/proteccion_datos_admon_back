package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaTipo;

public class NormativaTipoRowMapper implements RowMapper<NormativaTipo>{

	@Override
	public NormativaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormativaTipo normativaTipo = new NormativaTipo();
		normativaTipo.setCodigo(rs.getInt("not_codigo"));
		normativaTipo.setNombre(rs.getString("not_nombre"));
		normativaTipo.setEstado(rs.getInt("not_estado"));
		return normativaTipo;
	}
}
