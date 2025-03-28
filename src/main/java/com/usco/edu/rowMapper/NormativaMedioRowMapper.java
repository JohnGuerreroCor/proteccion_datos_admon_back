package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaMedio;

public class NormativaMedioRowMapper implements RowMapper<NormativaMedio>{

	@Override
	public NormativaMedio mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormativaMedio normativaMedio = new NormativaMedio();
		normativaMedio.setCodigo(rs.getInt("nom_codigo"));
		normativaMedio.setNombre(rs.getString("nom_nombre"));
		normativaMedio.setEstado(rs.getInt("nom_estado"));
		return normativaMedio;
	}
}
