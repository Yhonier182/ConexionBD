package Modelo;

import Controlador.Coordinador;

public class Logica {

	final int SELECCION = 0;
	final int ADMINISTRADOR = 1;
	final int USUARIO = 2;
	final int SECRETARIA = 3;

	private Coordinador miCoordinador;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	// Método para validar el ingreso de los diferentes perfiles
	public String validarIngreso(int index, String username, String password) {
		String retorno = "";

		if (index == USUARIO) {
			retorno = validarLoginUsuario(username, password);
		} else if (index == ADMINISTRADOR) {
			retorno = validarLoginAdministrador(username, password);
		} else if (index == SECRETARIA) {
			retorno = validarLoginSecretaria(username, password);
		} else {
			retorno = "error";
		}
		return retorno;
	}

	private String validarLoginUsuario(String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo != null) {
			return miUsuarioVo.getNombre(); // Usuario activo
		} else {
			return "desconectado"; // Usuario no encontrado
		}
	}

	// Método para validar el login de un administrador
	private String validarLoginAdministrador(String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo != null && miUsuarioVo.getTipo() == ADMINISTRADOR) {
			if (password.equals(miUsuarioVo.getPassword())) {
				return miUsuarioVo.getNombre(); // Login exitoso
			} else {
				return "invalido"; // Contraseña incorrecta
			}
		} else {
			return "desconectado"; // Usuario no encontrado o no es administrador
		}
	}

	// Método para validar el login de una secretaria
	private String validarLoginSecretaria(String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo != null && miUsuarioVo.getTipo() == SECRETARIA) {
			if (password.equals(miUsuarioVo.getPassword())) {
				return miUsuarioVo.getNombre(); // Login exitoso
			} else {
				return "invalido"; // Contraseña incorrecta
			}
		} else {
			return "desconectado"; // Usuario no encontrado o no es secretaria
		}
	}

	// Método para validar campos del usuario
	public boolean validarCampos(UsuarioVo miUsuarioVo) {
		boolean validarUsername = false;
		boolean validarPassword = false;

		String password = miUsuarioVo.getPassword();
		String username = miUsuarioVo.getUsername();

		if (password != null && !password.equals("")) {
			validarPassword = true;
		}

		if (username != null && !username.equals("")) {
			validarUsername = true;
		}

		return validarPassword && validarUsername;
	}

	// Método para validar la edad
	public Integer validarEdad(int edadIngresada) {
		Integer edad = null;
		try {
			edad = Integer.parseInt(String.valueOf(edadIngresada));
		} catch (Exception e) {
			edad = null;
		}
		return edad;
	}

	// Método para validar el tipo de usuario
	public Integer validarTipo(String tipoIngresado) {
		Integer tipo = null;
		try {
			tipo = Integer.parseInt(tipoIngresado);
		} catch (Exception e) {
			tipo = null;
		}
		return tipo;
	}
}
