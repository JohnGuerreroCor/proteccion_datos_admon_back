package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.rowMapper.NormaEntidadTipoRowMapper;

public class NormaEntidadTipoSetExtractor implements ResultSetExtractor<List<NormativaEntidadTipo>>{

	@Override
	public List<NormativaEntidadTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormativaEntidadTipo> list = new ArrayList<NormativaEntidadTipo>();
		while (rs.next()) {
			list.add(new NormaEntidadTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
