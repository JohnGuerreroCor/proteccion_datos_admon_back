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

import com.usco.edu.dao.IAutorizacionModuloDao;
import com.usco.edu.entities.AutorizacionModulo;
import com.usco.edu.entities.Modulo;
import com.usco.edu.entities.Sistema;
import com.usco.edu.resultSetExtractor.AutorizacionModuloSetExtractor;
import com.usco.edu.resultSetExtractor.ModuloSetExtractor;
import com.usco.edu.resultSetExtractor.SistemaSetExtractor;
import com.usco.edu.rowMapper.AutorizacionModuloRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class AutorizacionModuloDaoImpl implements IAutorizacionModuloDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Modulo> obtenerListadoModulo() {

		String sql = "select * from dbo.modulo m where m.mod_estado = 1;";
		return jdbcTemplate.query(sql, new ModuloSetExtractor());

	}

	@Override
	public List<Modulo> obtenerListadoModuloPorSistema(Integer sistemaCodigo) {

		String sql = "select * from dbo.modulo m " + "inner join dbo.sistema s on m.sis_codigo = s.sis_codigo "
				+ "where m.mod_estado = 1 and m.sis_codigo = ?;";
		return jdbcTemplate.query(sql, new Object[] { sistemaCodigo }, new ModuloSetExtractor());

	}

	@Override
	public List<Sistema> obtenerListadoSistema() {

		String sql = "select * from dbo.sistema s where s.sis_estado = 1;";
		return jdbcTemplate.query(sql, new SistemaSetExtractor());

	}

	@Override
	public List<AutorizacionModulo> obtenerListadoAutorizacionModulo() {

		String sql = "select * from protecciondatos.autorizacion_modulo am  "
				+ "inner join protecciondatos.autorizacion a on am.aut_codigo = a.aut_codigo "
				+ "inner join dbo.modulo m on am.mod_codigo = m.mod_codigo "
				+ "inner join dbo.sistema s on m.sis_codigo = s.sis_codigo " + "where am.aum_estado = 1;";
		return jdbcTemplate.query(sql, new AutorizacionModuloSetExtractor());

	}

	@Override
	public List<AutorizacionModulo> obtenerListadoModulosPorAutorizacion(Integer autorizacionCodigo) {

		String sql = "select * from protecciondatos.autorizacion_modulo am  "
				+ "inner join protecciondatos.autorizacion a on am.aut_codigo = a.aut_codigo "
				+ "inner join dbo.modulo m on am.mod_codigo = m.mod_codigo "
				+ "inner join dbo.sistema s on m.sis_codigo = s.sis_codigo "
				+ "where am.aum_estado = 1 and am.aut_codigo =?;";
		return jdbcTemplate.query(sql, new Object[] { autorizacionCodigo }, new AutorizacionModuloSetExtractor());

	}

	@Override
	public AutorizacionModulo obtenerAutorizacionModulo(Integer codigo) {

		String sql = "select * from protecciondatos.autorizacion_modulo am  "
				+ "inner join protecciondatos.autorizacion a on am.aut_codigo = a.aut_codigo "
				+ "inner join dbo.modulo m on am.mod_codigo = m.mod_codigo "
				+ "inner join dbo.sistema s on m.sis_codigo = s.sis_codigo "
				+ "where am.aum_estado = 1 and am.aum_codigo =;";

		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { codigo }, new AutorizacionModuloRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public int insertarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO protecciondatos.autorizacion_modulo (aut_codigo, mod_codigo) VALUES(:1, :2);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", autorizacionModulo.getAutorizacionCodigo());
			parameter.addValue("2", autorizacionModulo.getModuloCodigo());

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
	public int actualizarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion_modulo SET aut_codigo=:autorizacionCodigo, mod_codigo=:moduloCodigo WHERE aum_codigo =:codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("autorizacionCodigo", autorizacionModulo.getAutorizacionCodigo());
			parameter.addValue("moduloCodigo", autorizacionModulo.getModuloCodigo());
			parameter.addValue("codigo", autorizacionModulo.getCodigo());

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
	public int eliminarAutorizacionModulo(AutorizacionModulo autorizacionModulo, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion_modulo SET aum_estado = 0 WHERE aum_codigo =:codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", autorizacionModulo.getCodigo());

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
