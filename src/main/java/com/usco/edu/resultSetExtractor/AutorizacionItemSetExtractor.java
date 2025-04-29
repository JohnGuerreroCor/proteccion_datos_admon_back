package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.AutorizacionItem;
import com.usco.edu.rowMapper.AutorizacionItemRowMapper;

public class AutorizacionItemSetExtractor implements ResultSetExtractor<List<AutorizacionItem>> {

	@Override
	public List<AutorizacionItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<AutorizacionItem> list = new ArrayList<AutorizacionItem>();
		while (rs.next()) {
			list.add(new AutorizacionItemRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}

		return list;
	}
}
