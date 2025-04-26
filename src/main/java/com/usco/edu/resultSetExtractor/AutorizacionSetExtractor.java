package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Autorizacion;
import com.usco.edu.rowMapper.AutorizacionRowMapper;

public class AutorizacionSetExtractor implements ResultSetExtractor<List<Autorizacion>> {

	@Override
	public List<Autorizacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Autorizacion> list = new ArrayList<Autorizacion>();
		while (rs.next()) {
			list.add(new AutorizacionRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
