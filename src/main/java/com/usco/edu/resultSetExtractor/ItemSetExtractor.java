package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Item;
import com.usco.edu.rowMapper.ItemRowMapper;

public class ItemSetExtractor implements ResultSetExtractor<List<Item>>{

	@Override
	public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Item> list = new ArrayList<Item>();
		while (rs.next()) {
			list.add(new ItemRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}
}
