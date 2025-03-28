package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IMarcoNormativoDao;
import com.usco.edu.entities.Normativa;
import com.usco.edu.entities.NormativaEntidad;
import com.usco.edu.entities.NormativaEntidadTipo;
import com.usco.edu.entities.NormativaExpide;
import com.usco.edu.entities.NormativaMedio;
import com.usco.edu.entities.NormativaTipo;
import com.usco.edu.resultSetExtractor.NormaEntidadSetExtractor;
import com.usco.edu.resultSetExtractor.NormaEntidadTipoSetExtractor;
import com.usco.edu.resultSetExtractor.NormativaExpideSetExtractor;
import com.usco.edu.resultSetExtractor.NormativaMedioSetExtractor;
import com.usco.edu.resultSetExtractor.NormativaSetExtractor;
import com.usco.edu.resultSetExtractor.NormativaTipoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class MarcoNormativoDaoImpl implements IMarcoNormativoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<NormativaEntidadTipo> obtenerEntidadTipo() {

		String sql = "select * from protecciondatos.normativa_entidad_tipo ne where ne.net_estado = 1";
		return jdbcTemplate.query(sql, new NormaEntidadTipoSetExtractor());

	}
	
	@Override
	public List<NormativaEntidad> obtenerEntidad() {
		
		String sql = "select * from protecciondatos.normativa_entidad e "
				+ "inner join protecciondatos.normativa_entidad_tipo net on e.net_codigo = net.net_codigo "
				+ "where e.noe_estado = 1";
		return jdbcTemplate.query(sql, new NormaEntidadSetExtractor());
		
	}
	
	@Override
	public List<NormativaTipo> obtenerNormativaTipo() {
		
		String sql = "select * from protecciondatos.normativa_tipo nt where nt.not_estado = 1";
		return jdbcTemplate.query(sql, new NormativaTipoSetExtractor());
		
	}
	
	@Override
	public List<NormativaMedio> obtenerMedio() {
		
		String sql = "select * from protecciondatos.normativa_medio n where n.nom_estado = 1";
		return jdbcTemplate.query(sql, new NormativaMedioSetExtractor());
		
	}
	
	@Override
	public List<Normativa> obtenerNormativa() {
		
		String sql = "select * from protecciondatos.normativa n "
				+ "inner join protecciondatos.normativa_expide ne on n.nex_codigo = ne.nex_codigo "
				+ "inner join protecciondatos.normativa_entidad e on ne.noe_codigo = e.noe_codigo "
				+ "inner join protecciondatos.normativa_entidad_tipo net on e.net_codigo = net.net_codigo "
				+ "inner join protecciondatos.normativa_tipo nt on ne.not_codigo = nt.not_codigo "
				+ "inner join protecciondatos.normativa_medio nm on n.nom_codigo = nm.nom_codigo "
				+ "where n.nor_estado = 1";
		return jdbcTemplate.query(sql, new NormativaSetExtractor());
		
	}

	@Override
	public List<NormativaEntidad> obtenerEntidadPorTipo(Integer entidadTipoCodigo) {

		String sql = "select * from protecciondatos.normativa_entidad ne "
				+ "inner join protecciondatos.normativa_entidad_tipo net on ne.net_codigo = net.net_codigo "
				+ "where ne.noe_estado = 1 and ne.net_codigo = ?";
		return jdbcTemplate.query(sql, new Object[] { entidadTipoCodigo }, new NormaEntidadSetExtractor());

	}
	
	@Override
	public List<NormativaExpide> obtenerExpidePorEntidad(Integer entidadCodigo) {

		String sql = "select nx.nex_codigo, nx.noe_codigo, e.noe_nombre, nx.not_codigo, nt.not_nombre, nx.nex_estado from protecciondatos.normativa_expide nx "
				+ "inner join protecciondatos.normativa_entidad e on nx.noe_codigo = e.noe_codigo "
				+ "inner join protecciondatos.normativa_tipo nt on nx.not_codigo = nt.not_codigo "
				+ "where nx.nex_estado = 1 and nx.noe_codigo = ?";
		return jdbcTemplate.query(sql, new Object[] { entidadCodigo }, new NormativaExpideSetExtractor());

	}

	@Override
	public void registrarNormativa(Normativa normativa, String sys) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(sys);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO protecciondatos.normativa (nor_numero, nor_nombre, nex_codigo, nom_codigo, nor_url, nor_anexo, nor_fecha_creacion, nor_fecha_vigencia, nor_deroga, nor_observacion) "
				+ "VALUES(:1, :2, :3, :4, :5, :6, :7, :8, :9, :10);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", normativa.getNumero());
			parameter.addValue("2", normativa.getNombre());
			parameter.addValue("3", normativa.getNormativaExpideCodigo());
			parameter.addValue("4", normativa.getNormativaMedioCodigo());
			parameter.addValue("5", normativa.getUrl());
			parameter.addValue("6", normativa.getAnexo());
			parameter.addValue("7", normativa.getFechaCreacion());
			parameter.addValue("8", normativa.getFechaVigencia(), Types.DATE);
			parameter.addValue("9", normativa.getDeroga());
			parameter.addValue("10", normativa.getObservacion());

			jdbc.update(sql, parameter);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void actualizarNormativa(Normativa normativa, String sys) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(sys);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE protecciondatos.normativa SET nor_numero=?, nor_nombre=?, nex_codigo=?, "
				+ "nom_codigo=?, nor_url=?, nor_anexo=?, nor_fecha_creacion=?, nor_fecha_vigencia=?, "
				+ "nor_deroga=?, nor_observacion=?"
				+ "WHERE nor_codigo = ?;";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", normativa.getNumero());
			parameter.addValue("2", normativa.getNombre());
			parameter.addValue("3", normativa.getNormativaExpideCodigo());
			parameter.addValue("4", normativa.getNormativaMedioCodigo());
			parameter.addValue("5", normativa.getUrl());
			parameter.addValue("6", normativa.getAnexo());
			parameter.addValue("7", normativa.getFechaCreacion());
			parameter.addValue("8", normativa.getFechaVigencia(), Types.DATE);
			parameter.addValue("9", normativa.getDeroga());
			parameter.addValue("10", normativa.getObservacion());
			parameter.addValue("11", normativa.getCodigo());

			jdbc.update(sql, parameter);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int eliminarNormativa(Normativa normativa, String sys) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(sys);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "UPDATE protecciondatos.normativa SET nor_estado = 0 WHERE nor_codigo = ?;";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("1", normativa.getCodigo());

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
