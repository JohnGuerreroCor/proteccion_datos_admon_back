package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.AutorizacionModulo;

public class AutorizacionModuloRowMapper implements RowMapper<AutorizacionModulo> {

	@Override
	public AutorizacionModulo mapRow(ResultSet rs, int rowNum) throws SQLException {
		AutorizacionModulo autorizacionModulo = new AutorizacionModulo();
		autorizacionModulo.setCodigo(rs.getInt("aum_codigo"));
		autorizacionModulo.setAutorizacionCodigo(rs.getInt("aut_codigo"));
		autorizacionModulo.setAutorizacionTitulo(rs.getString("aut_titulo"));
		autorizacionModulo.setModuloCodigo(rs.getInt("mod_codigo"));
		autorizacionModulo.setModuloNombre(rs.getString("mod_nombre"));
		autorizacionModulo.setModuloUrl(rs.getString("mod_url"));
		autorizacionModulo.setSistemaCodigo(rs.getInt("sis_codigo"));
		autorizacionModulo.setSistemaNombre(rs.getString("sis_nombre"));
		autorizacionModulo.setEstado(rs.getInt("aum_estado"));
		return autorizacionModulo;
	}
}