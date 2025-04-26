package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Seccion;
import com.usco.edu.rowMapper.SeccionRowMapper;

public class SeccionSetExtractor implements ResultSetExtractor<List<Seccion>> {

	@Override
	public List<Seccion> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Seccion> list = new ArrayList<Seccion>();
		while (rs.next()) {
			list.add(new SeccionRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
