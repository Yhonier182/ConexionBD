package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Controlador.Coordinador;
import Modelo.UsuarioVo;
import conexion.Conexion;

public class UsuarioDao {

	private Coordinador miCoordinador;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public String registrarUsuario(UsuarioVo miUsuarioVo) {
		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO usuario (documento, nombre, profesion, edad, direccion, telefono, tipo, password, username, estado)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miUsuarioVo.getDocumento());
			preStatement.setString(2, miUsuarioVo.getNombre());
			preStatement.setString(3, miUsuarioVo.getProfesion());
			preStatement.setInt(4, miUsuarioVo.getEdad());
			preStatement.setString(5, miUsuarioVo.getDireccion());
			preStatement.setString(6, miUsuarioVo.getTelefono());
			preStatement.setInt(7, miUsuarioVo.getTipo());
			preStatement.setString(8, miUsuarioVo.getPassword());
			preStatement.setString(9, miUsuarioVo.getUsername());
			preStatement.setInt(10, miUsuarioVo.getEstado());

			preStatement.execute();

			resultado = "ok";

		} catch (SQLException e) {
			System.out.println("No se pudo registrar el dato: " + e.getMessage());
			e.printStackTrace();
			resultado = "error";
		} finally {
			if (preStatement != null) {
				try {
					preStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			conexion.desconectar();
		}

		return resultado;
	}


	public UsuarioVo consultarUsuario(String username, String password) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		UsuarioVo miUsuario = null;

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM usuario WHERE username = ? AND password = ? AND estado = true";
		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);
				statement.setString(1, username);
				statement.setString(2, password);

				result = statement.executeQuery();

				if (result.next()) {
					miUsuario = new UsuarioVo();
					miUsuario.setDocumento(result.getString("documento"));
					miUsuario.setNombre(result.getString("nombre"));
					miUsuario.setProfesion(result.getString("profesion"));
					miUsuario.setEdad(result.getInt("edad"));
					miUsuario.setDireccion(result.getString("direccion"));
					miUsuario.setTelefono(result.getString("telefono"));
					miUsuario.setTipo(result.getInt("tipo"));
					miUsuario.setPassword(result.getString("password"));
					miUsuario.setUsername(result.getString("username"));
					miUsuario.setEstado(result.getInt("estado"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}

		return miUsuario;
	}



	public UsuarioVo buscarUsuarioPorDocumento(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		UsuarioVo miUsuario = null;

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM usuario WHERE documento = ?";
		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);
				statement.setString(1, documento);

				result = statement.executeQuery();

				if (result.next()) {
					miUsuario = new UsuarioVo();
					miUsuario.setDocumento(result.getString("documento"));
					miUsuario.setNombre(result.getString("nombre"));
					miUsuario.setProfesion(result.getString("profesion"));
					miUsuario.setEdad(result.getInt("edad"));
					miUsuario.setDireccion(result.getString("direccion"));
					miUsuario.setTelefono(result.getString("telefono"));
					miUsuario.setTipo(result.getInt("tipo"));
					miUsuario.setPassword(result.getString("password"));
					miUsuario.setUsername(result.getString("username"));
					miUsuario.setEstado(result.getInt("estado"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en la búsqueda del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return miUsuario;
	}



	public String actualizaUsuario(UsuarioVo miUsuarioVo ) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE usuario SET documento= ? ,nombre = ? , profesion=? , edad=? , direccion=? ,telefono= ?, tipo=?, password=?, username=? ,estado =? WHERE documento= ?";
			PreparedStatement preStatement = connection.prepareStatement(consulta);

			preStatement.setString(1, miUsuarioVo.getDocumento());
			preStatement.setString(2, miUsuarioVo.getNombre());
			preStatement.setString(3, miUsuarioVo.getProfesion());
			preStatement.setInt(4, miUsuarioVo.getEdad());
			preStatement.setString(5, miUsuarioVo.getDireccion());
			preStatement.setString(6, miUsuarioVo.getTelefono());
			preStatement.setInt(7, miUsuarioVo.getTipo());
			preStatement.setString(8, miUsuarioVo.getPassword());
			preStatement.setString(9, miUsuarioVo.getUsername());
			preStatement.setInt(10, miUsuarioVo.getEstado());
			preStatement.setString(11, miUsuarioVo.getDocumento());

			preStatement.executeUpdate();

			resultado = "ok";

			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "error";
		}
		return resultado;
	}




	public String eliminarUsuario(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();

		String resp = "";
		try {
			String sentencia = "DELETE FROM usuario WHERE documento= ? ";

			PreparedStatement statement = connection.prepareStatement(sentencia);
			statement.setString(1, documento);

			statement.executeUpdate();

			resp = "ok";
			statement.close();
			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp = "error";
		}
		return resp;
	}



	public String inactivarUsuario(String documento) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE usuario SET estado= false WHERE documento = ?";
			PreparedStatement preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, documento);
			preStatement.executeUpdate();
			resultado = "ok";
		} catch (SQLException e) {
			System.out.println("Error al inactivar el usuario: " + e.getMessage());
			resultado = "error";
		} finally {
			if (connection != null) {
				miConexion.desconectar();
			}
		}
		return resultado;
	}


	public String activarUsuario(String documento) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE usuario SET estado = true WHERE documento = ?";
			PreparedStatement preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, documento);
			preStatement.executeUpdate();
			resultado = "ok";
		} catch (SQLException e) {
			System.out.println("Error al activar el usuario: " + e.getMessage());
			resultado = "error";
		} finally {
			if (connection != null) {
				miConexion.desconectar();
			}
		}
		return resultado;
	}





}
