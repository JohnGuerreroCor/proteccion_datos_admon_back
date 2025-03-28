package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Normativa;
import com.usco.edu.rowMapper.NormativaRowMapper;

public class NormativaSetExtractor implements ResultSetExtractor<List<Normativa>>{

	@Override
	public List<Normativa> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Normativa> list = new ArrayList<Normativa>();
		while (rs.next()) {
			list.add(new NormativaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
