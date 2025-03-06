package com.usco.edu.service.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.usco.edu.dao.IInicioSesionDao;
import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Rol;
import com.usco.edu.entities.Usuario;
import com.usco.edu.service.IUsuarioService;
import com.usco.edu.util.ConexionBuilder;
import com.usco.edu.util.DisabledException;
import com.usco.edu.util.SegundaClave;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private IUsuarioDao usuariodao;

	@Autowired
	private IInicioSesionDao inicioSesionDao;

	@Autowired
	private SegundaClave segundaClaveComponent;

	@Autowired
	private ConexionBuilder conexionBuilder;

	// IMPLEMENTACIÓN DEL MÉTODO DE LA INTERFAZ UserDetailsService
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String segundaClave = request.getParameter("clave2");

		// VALIDAR SI EL USUARIO EXISTE EN EL SISTEMA
		if (!usuariodao.validarUsuario(username)) {
			logger.error("Error, no existe el usuario '" + username + "' en el sistema!!!");
			throw new DisabledException("No existe el usuario en el sistema.");
		}

		// OBTENER INFORMACIÓN DEL USUARIO
		Usuario usuario = usuariodao.buscarUsuario(username);

		// CREAR UNA INSTANCIA DE ROLE Y ASIGNARLE LOS ROLES DEL USUARIO
		Rol rol = new Rol(1, "ROLE_" + usuario.getRole());
		ArrayList<Rol> roles = new ArrayList<>();
		roles.add(rol);

		// CONVERTIR LOS ROLES A GRANTED AUTHORITIES
		List<GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority(((Rol) role).getNombre_rol()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		// OBTENER LA SEGUNDA CLAVE REAL
		String SegundaClaveReal = inicioSesionDao.obtenerSegundaClaveReal(segundaClave);

		// COMPROBAR LA SEGUNDA CLAVE
		comprobarSegundaClave(usuario.getUserdb(), SegundaClaveReal);

		// CONFIGURAR LA SEGUNDA CLAVE EN EL COMPONENTE
		segundaClaveComponent.setClave(username, SegundaClaveReal);
		segundaClaveComponent.setUser(username, usuario.getUserdb());

		// CREAR Y DEVOLVER UNA INSTANCIA DE USERDETAILS
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isState(), true, true, true,
				authorities);
	}

	// MÉTODO PARA COMPROBAR LA SEGUNDA CLAVE
	private void comprobarSegundaClave(String UsuarioDb, String clave) {
		DataSource datasource = conexionBuilder.construir(UsuarioDb, clave);
		Connection conexion = null;

		try {
			conexion = datasource.getConnection();
		} catch (Exception e) {
			throw new DisabledException("La segunda clave es incorrecta.");
		} finally {
			cerrarConexion(conexion);
		}
	}

	// MÉTODO PARA BUSCAR UN USUARIO POR NOMBRE DE USUARIO
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(String username) {
		return usuariodao.buscarUsuario(username);
	}

	// MÉTODO PARA CERRAR LA CONEXIÓN
	private void cerrarConexion(Connection conexion) {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}