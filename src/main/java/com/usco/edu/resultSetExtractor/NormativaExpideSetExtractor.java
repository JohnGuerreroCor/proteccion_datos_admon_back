package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.rowMapper.NormativaExpideRowMapper;

public class NormativaExpideSetExtractor implements ResultSetExtractor<List<NormativaExpide>>{

	@Override
	public List<NormativaExpide> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormativaExpide> list = new ArrayList<NormativaExpide>();
		while (rs.next()) {
			list.add(new NormativaExpideRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
