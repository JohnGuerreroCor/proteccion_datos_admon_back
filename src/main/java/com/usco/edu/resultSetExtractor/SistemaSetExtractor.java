package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Sistema;
import com.usco.edu.rowMapper.SistemaRowMapper;

public class SistemaSetExtractor implements ResultSetExtractor<List<Sistema>> {

	@Override
	public List<Sistema> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Sistema> list = new ArrayList<Sistema>();
		while (rs.next()) {
			list.add(new SistemaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
