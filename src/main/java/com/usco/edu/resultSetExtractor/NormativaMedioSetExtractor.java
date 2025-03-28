package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.rowMapper.NormativaMedioRowMapper;

public class NormativaMedioSetExtractor implements ResultSetExtractor<List<NormativaMedio>>{

	@Override
	public List<NormativaMedio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<NormativaMedio> list = new ArrayList<NormativaMedio>();
		while (rs.next()) {
			list.add(new NormativaMedioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
