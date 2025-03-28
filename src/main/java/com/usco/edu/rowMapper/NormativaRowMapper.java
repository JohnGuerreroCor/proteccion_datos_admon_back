package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Normativa;

public class NormativaRowMapper implements RowMapper<Normativa>{

	@Override
	public Normativa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Normativa normativa = new Normativa();
		normativa.setCodigo(rs.getInt("nor_codigo"));
		normativa.setNumero(rs.getString("nor_numero"));
		normativa.setNombre(rs.getString("nor_nombre"));
		normativa.setNormativaExpideCodigo(rs.getInt("nex_codigo"));
		normativa.setNormativaEntidadCodigo(rs.getInt("noe_codigo"));
		normativa.setNormativaEntidadNombre(rs.getString("noe_nombre"));
		normativa.setNormativaEntidadTipoCodigo(rs.getInt("net_codigo"));
		normativa.setNormativaEntidadTipoNombre(rs.getString("net_nombre"));
		normativa.setNormativaTipoCodigo(rs.getInt("not_codigo"));
		normativa.setNormativaTipoNombre(rs.getString("not_nombre"));
		normativa.setNormativaMedioCodigo(rs.getInt("nom_codigo"));
		normativa.setNormativaMedioNombre(rs.getString("nom_nombre"));
		normativa.setUrl(rs.getString("nor_url"));
		normativa.setAnexo(rs.getString("nor_anexo"));
		normativa.setFechaCreacion(rs.getDate("nor_fecha_creacion"));
		normativa.setFechaVigencia(rs.getDate("nor_fecha_vigencia"));
		normativa.setDeroga(rs.getInt("nor_deroga"));
		normativa.setObservacion(rs.getString("nor_observacion"));
		normativa.setEstado(rs.getInt("net_estado"));
		return normativa;
		
	}
}
