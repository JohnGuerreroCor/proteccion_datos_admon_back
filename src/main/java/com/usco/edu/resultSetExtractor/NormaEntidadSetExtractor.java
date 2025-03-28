package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.rowMapper.NormaEntidadRowMapper;

public class NormaEntidadSetExtractor implements ResultSetExtractor<List<NormativaEntidad>>{

	@Override
	public List<NormativaEntidad> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormativaEntidad> list = new ArrayList<NormativaEntidad>();
		while (rs.next()) {
			list.add(new NormaEntidadRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
