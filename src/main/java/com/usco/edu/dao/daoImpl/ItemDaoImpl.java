package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IItemDao;
import com.usco.edu.entities.Item;
import com.usco.edu.resultSetExtractor.ItemSetExtractor;
import com.usco.edu.rowMapper.ItemRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class ItemDaoImpl implements IItemDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Item> obtenerListadoItem() {
		
		String sql = "select * from protecciondatos.item i "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where i.ite_estado = 1;";
		return jdbcTemplate.query(sql, new ItemSetExtractor());
		
	}



	@Override
	public List<Item> obtenerItemsPorSeccion(Integer seccionCodigo) {
		
		String sql = "select * from protecciondatos.item i "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where i.ite_estado = 1 and i.sec_codigo = ?;";
		return jdbcTemplate.query(sql, new Object[] { seccionCodigo }, new ItemSetExtractor());
		
	}



	@Override
	public Item obtenerItem(Integer codigo) {
		
		String sql = "select * from protecciondatos.item i "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where i.ite_estado = 1 and i.ite_codigo = ?;";

	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{codigo}, new ItemRowMapper());
	    } catch (EmptyResultDataAccessException e) {
	        return null; 
	    }
		
	}



	@Override
	public int insertarItem(Item item, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO protecciondatos.item (ite_contenido, sec_codigo) VALUES(:1, :2);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", item.getContenido());
			parameter.addValue("2", item.getSeccionCodigo());

			jdbc.update(sql, parameter, keyHolder);
			return keyHolder.getKey().intValue();

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}



	@Override
	public int actualizarItem(Item item, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
	    NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

	    String sql = "UPDATE protecciondatos.item SET ite_contenido=:contenido, sec_codigo=:seccion WHERE ite_codigo =:codigo;";

	    try {
	        MapSqlParameterSource parameter = new MapSqlParameterSource();
	        parameter.addValue("contenido", item.getContenido());
	        parameter.addValue("seccion", item.getSeccionCodigo());
	        parameter.addValue("codigo", item.getCodigo());

	        return jdbc.update(sql, parameter);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        try {
	            cerrarConexion(dataSource.getConnection());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	}



	@Override
	public int eliminarItem(Item item, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
	    NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

	    String sql = "UPDATE protecciondatos.item SET ite_estado=0 WHERE ite_codigo =:codigo;";

	    try {
	        MapSqlParameterSource parameter = new MapSqlParameterSource();
	        parameter.addValue("codigo", item.getCodigo());

	        return jdbc.update(sql, parameter); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        try {
	            cerrarConexion(dataSource.getConnection());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	private void cerrarConexion(Connection con) {
		if (con == null)
			return;

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
