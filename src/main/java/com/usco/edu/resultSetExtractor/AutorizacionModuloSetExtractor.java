package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.AutorizacionModulo;
import com.usco.edu.rowMapper.AutorizacionModuloRowMapper;

public class AutorizacionModuloSetExtractor implements ResultSetExtractor<List<AutorizacionModulo>> {

	@Override
	public List<AutorizacionModulo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<AutorizacionModulo> list = new ArrayList<AutorizacionModulo>();
		while (rs.next()) {
			list.add(new AutorizacionModuloRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
