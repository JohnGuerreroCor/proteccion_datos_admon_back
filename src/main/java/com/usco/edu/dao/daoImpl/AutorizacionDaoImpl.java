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

import com.usco.edu.dao.IAutorizacionDao;
import com.usco.edu.entities.Autorizacion;
import com.usco.edu.entities.Modulo;
import com.usco.edu.resultSetExtractor.AutorizacionSetExtractor;
import com.usco.edu.resultSetExtractor.ModuloSetExtractor;
import com.usco.edu.rowMapper.AutorizacionRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class AutorizacionDaoImpl implements IAutorizacionDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Modulo> obtenerListadoModulo() {

		String sql = "select * from dbo.modulo m";
		return jdbcTemplate.query(sql, new ModuloSetExtractor());

	}

	@Override
	public List<Autorizacion> obtenerListadoAutorizacion() {

		String sql = "select * from protecciondatos.autorizacion a "
				+ "inner join protecciondatos.normativa n on a.nor_codigo = n.nor_codigo "
				+ "inner join protecciondatos.normativa_expide ne on n.nex_codigo = ne.nex_codigo "
				+ "inner join protecciondatos.normativa_tipo net on ne.not_codigo = net.not_codigo "
				+ "where a.aut_estado = 1;";
		return jdbcTemplate.query(sql, new AutorizacionSetExtractor());

	}

	@Override
	public Autorizacion obtenerAutorizacion(Integer codigo) {

		String sql = "select * from protecciondatos.autorizacion a "
				+ "inner join protecciondatos.normativa n on a.nor_codigo = n.nor_codigo "
				+ "inner join protecciondatos.normativa_expide ne on n.nex_codigo = ne.nex_codigo "
				+ "inner join protecciondatos.normativa_tipo net on ne.not_codigo = net.not_codigo "
				+ "where a.aut_estado = 1 and a.aut_codigo = ?;";

		try {

			return jdbcTemplate.queryForObject(sql, new Object[] { codigo }, new AutorizacionRowMapper());

		} catch (EmptyResultDataAccessException e) {

			return null;

		}

	}

	@Override
	public int insertarAutorizacion(Autorizacion autorizacion, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO protecciondatos.autorizacion (aut_titulo, nor_codigo, aut_version, aut_descripcion, aut_anexo_requerido) "
				+ "VALUES(:1, :2, :3, :4, :5);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", autorizacion.getTitulo());
			parameter.addValue("2", autorizacion.getNormativaCodigo());
			parameter.addValue("3", autorizacion.getVersion());
			parameter.addValue("4", autorizacion.getDescripcion());
			parameter.addValue("5", autorizacion.getAnexo());

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
	public int actualizarAutorizacion(Autorizacion autorizacion, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion SET aut_titulo=:titulo, nor_codigo=:normativaCodigo, aut_version=:version, "
				+ "aut_descripcion=:descripcion, aut_anexo_requerido=:anexo WHERE aut_codigo =:codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("titulo", autorizacion.getTitulo());
			parameter.addValue("normativaCodigo", autorizacion.getNormativaCodigo());
			parameter.addValue("version", autorizacion.getVersion());
			parameter.addValue("descripcion", autorizacion.getDescripcion());
			parameter.addValue("anexo", autorizacion.getAnexo());
			parameter.addValue("codigo", autorizacion.getCodigo());

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
	public int eliminarAutorizacion(Autorizacion autorizacion, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion SET aut_estado = 0 WHERE aut_codigo =:codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", autorizacion.getCodigo());

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
