package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaExpide;

public class NormativaExpideRowMapper implements RowMapper<NormativaExpide>{

	@Override
	public NormativaExpide mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormativaExpide normativaExpide = new NormativaExpide();
		normativaExpide.setCodigo(rs.getInt("nex_codigo"));
		normativaExpide.setNormativaEntidaCodigo(rs.getInt("noe_codigo"));
		normativaExpide.setNormativaEntidaNombre(rs.getString("noe_nombre"));
		normativaExpide.setNormativaTipoCodigo(rs.getInt("not_codigo"));
		normativaExpide.setNormativaTipoNombre(rs.getString("not_nombre"));
		normativaExpide.setEstado(rs.getInt("nex_estado"));
		return normativaExpide;
	}
}
