package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.SeccionTipo;
import com.usco.edu.rowMapper.SeccionTipoRowMapper;

public class SeccionTipoSetExtractor implements ResultSetExtractor<List<SeccionTipo>> {

	@Override
	public List<SeccionTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SeccionTipo> list = new ArrayList<SeccionTipo>();
		while (rs.next()) {
			list.add(new SeccionTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
