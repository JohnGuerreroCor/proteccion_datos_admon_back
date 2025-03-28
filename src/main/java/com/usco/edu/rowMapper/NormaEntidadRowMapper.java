package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.NormativaEntidad;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormaEntidadRowMapper implements RowMapper<NormativaEntidad>{

	@Override
	public NormativaEntidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		NormativaEntidad normaEntidad = new NormativaEntidad();
		normaEntidad.setCodigo(rs.getInt("noe_codigo"));
		normaEntidad.setNombre(rs.getString("noe_nombre"));
		normaEntidad.setNormaEntidadTipoCodigo(rs.getInt("net_codigo"));
		normaEntidad.setNormaEntidadTipoNombre(rs.getString("net_nombre"));
		normaEntidad.setEstado(rs.getInt("noe_estado"));
		return normaEntidad;
	}
}