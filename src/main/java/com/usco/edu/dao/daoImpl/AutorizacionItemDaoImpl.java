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

import com.usco.edu.dao.IAutorizacionItemDao;
import com.usco.edu.entities.AutorizacionItem;
import com.usco.edu.resultSetExtractor.AutorizacionItemSetExtractor;
import com.usco.edu.rowMapper.AutorizacionItemRowMapper;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class AutorizacionItemDaoImpl implements IAutorizacionItemDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<AutorizacionItem> obtenerListadoAutorizacionItem() {

		String sql = "select * from protecciondatos.autorizacion_item ai "
				+ "inner join protecciondatos.autorizacion a on ai.aut_codigo = a.aut_codigo "
				+ "inner join protecciondatos.item i on ai.ite_codigo = i.ite_codigo "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where ai.aui_estado = 1";
		return jdbcTemplate.query(sql, new AutorizacionItemSetExtractor());

	}

	@Override
	public List<AutorizacionItem> obtenerListadoItemPorAutorizacion(Integer autorizacionCodigo) {

		String sql = "select * from protecciondatos.autorizacion_item ai "
				+ "inner join protecciondatos.autorizacion a on ai.aut_codigo = a.aut_codigo "
				+ "inner join protecciondatos.item i on ai.ite_codigo = i.ite_codigo "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where ai.aui_estado = 1 and ai.aut_codigo = ?";
		return jdbcTemplate.query(sql, new Object[] { autorizacionCodigo }, new AutorizacionItemSetExtractor());

	}

	@Override
	public AutorizacionItem obtenerAutorizacionItem(Integer codigo) {

		String sql = "select * from protecciondatos.autorizacion_item ai "
				+ "inner join protecciondatos.autorizacion a on ai.aut_codigo = a.aut_codigo "
				+ "inner join protecciondatos.item i on ai.ite_codigo = i.ite_codigo "
				+ "inner join protecciondatos.seccion s on i.sec_codigo = s.sec_codigo "
				+ "where ai.aui_estado = 1 and ai.aui_codigo = ?";

		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { codigo }, new AutorizacionItemRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public int insertarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO protecciondatos.autorizacion_item (aut_codigo, ite_codigo) VALUES(:1, :2);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", autorizacionItem.getAutorizacionCodigo());
			parameter.addValue("2", autorizacionItem.getItemCodigo());

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
	public int actualizarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion_item SET aut_codigo=:autorizacionCodigo, ite_codigo=:itemCodigo WHERE aui_codigo = :codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("autorizacionCodigo", autorizacionItem.getAutorizacionCodigo());
			parameter.addValue("itemCodigo", autorizacionItem.getItemCodigo());
			parameter.addValue("codigo", autorizacionItem.getCodigo());

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
	public int eliminarAutorizacionItem(AutorizacionItem autorizacionItem, String usuarioBd) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(usuarioBd);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.autorizacion_item SET aui_estado = 0 WHERE aui_codigo =:codigo;";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", autorizacionItem.getCodigo());

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
