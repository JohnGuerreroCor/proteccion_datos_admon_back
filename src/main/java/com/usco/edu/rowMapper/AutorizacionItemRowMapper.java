package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.AutorizacionItem;

public class AutorizacionItemRowMapper implements RowMapper<AutorizacionItem>{

	@Override
	public AutorizacionItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		AutorizacionItem autorizacionItem = new AutorizacionItem();
		autorizacionItem.setCodigo(rs.getInt("aui_codigo"));
		autorizacionItem.setAutorizacionCodigo(rs.getInt("aut_codigo"));
		autorizacionItem.setAutorizacionTitulo(rs.getString("aut_titulo"));
		autorizacionItem.setItemCodigo(rs.getInt("ite_codigo"));
		autorizacionItem.setItemContenido(rs.getString("ite_contenido"));
		autorizacionItem.setSeccionCodigo(rs.getInt("sec_codigo"));
		autorizacionItem.setSeccionNombre(rs.getString("sec_nombre"));
		autorizacionItem.setEstado(rs.getInt("aui_estado"));
		return autorizacionItem;
	}
}
