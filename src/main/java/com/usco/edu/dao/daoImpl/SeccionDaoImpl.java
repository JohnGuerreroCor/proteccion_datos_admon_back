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

import com.usco.edu.dao.ISeccionDao;
import com.usco.edu.entities.Seccion;
import com.usco.edu.entities.SeccionTipo;
import com.usco.edu.resultSetExtractor.SeccionSetExtractor;
import com.usco.edu.resultSetExtractor.SeccionTipoSetExtractor;
import com.usco.edu.rowMapper.SeccionRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class SeccionDaoImpl implements ISeccionDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Seccion> obtenerListadoSeccion() {
		
		String sql = "select * from protecciondatos.seccion s "
				+ "inner join protecciondatos.seccion_tipo st on s.set_codigo = st.set_codigo "
				+ "where s.sec_estado = 1";
		return jdbcTemplate.query(sql, new SeccionSetExtractor());
		
	}
	
	@Override
	public List<SeccionTipo> obtenerListadoSeccionTipo() {
		
		String sql = "select * from protecciondatos.seccion_tipo st where st.set_estado = 1";
		return jdbcTemplate.query(sql, new SeccionTipoSetExtractor());
		
	}

	@Override
	public Seccion obtenerSeccion(Integer codigo) {
		
		String sql = "select * from protecciondatos.seccion s "
				+ "inner join protecciondatos.seccion_tipo st on s.set_codigo = st.set_codigo "
				+ "where s.sec_estado = 1 AND s.sec_codigo = ?";

	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{codigo}, new SeccionRowMapper());
	    } catch (EmptyResultDataAccessException e) {
	        return null; 
	    }
	}

	@Override
	public int insertarSeccion(Seccion seccion, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO protecciondatos.seccion (sec_nombre) VALUES(:1);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", seccion.getNombre());

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
	public int actualizarSeccion(Seccion seccion, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
	    NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

	    String sql = "UPDATE protecciondatos.seccion SET sec_nombre = :nombre WHERE sec_codigo = :codigo";

	    try {
	        MapSqlParameterSource parameter = new MapSqlParameterSource();
	        parameter.addValue("nombre", seccion.getNombre());
	        parameter.addValue("codigo", seccion.getCodigo());

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
	public int eliminarSeccion(Seccion seccion, String usuarioBd) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
	    NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

	    String sql = "UPDATE protecciondatos.seccion SET sec_estado = 0 WHERE sec_codigo = :codigo";

	    try {
	        MapSqlParameterSource parameter = new MapSqlParameterSource();
	        parameter.addValue("codigo", seccion.getCodigo());

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
