package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Modulo;
import com.usco.edu.rowMapper.ModuloRowMapper;

public class ModuloSetExtractor implements ResultSetExtractor<List<Modulo>> {

	@Override
	public List<Modulo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Modulo> list = new ArrayList<Modulo>();
		while (rs.next()) {
			list.add(new ModuloRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
