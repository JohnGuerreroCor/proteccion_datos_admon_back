package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Item;

public class ItemRowMapper implements RowMapper<Item>{

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setCodigo(rs.getInt("ite_codigo"));
		item.setContenido(rs.getString("ite_contenido"));
		item.setSeccionCodigo(rs.getInt("sec_codigo"));
		item.setSeccionNombre(rs.getString("sec_nombre"));
		item.setEstado(rs.getInt("ite_estado"));
		return item;
	}
}