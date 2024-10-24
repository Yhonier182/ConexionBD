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
			return miUsuarioVo.getNombre();
		} else {
			return "desconectado";
		}
	}

	private String validarLoginAdministrador(String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo != null && miUsuarioVo.getTipo() == ADMINISTRADOR) {
			if (password.equals(miUsuarioVo.getPassword())) {
				return miUsuarioVo.getNombre();
			} else {
				return "invalido";
			}
		} else {
			return "desconectado";
		}
	}


	private String validarLoginSecretaria(String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo != null && miUsuarioVo.getTipo() == SECRETARIA) {
			if (password.equals(miUsuarioVo.getPassword())) {
				return miUsuarioVo.getNombre();
			} else {
				return "invalido";
			}
		} else {
			return "desconectado";
		}
	}


	public boolean validarCampos(UsuarioVo miUsuarioVo) {
		boolean validarUsername = false;
		boolean validarPassword = false;

		String password = miUsuarioVo.getPassword();
		String username = miUsuarioVo.getUsername();
		String documento = miUsuarioVo.getDocumento();

		if (password != null && !password.equals("")) {
			validarPassword = true;
		}

		if (username != null && !username.equals("")) {
			validarUsername = true;
		}


		return validarPassword && validarUsername ;
	}


	public Integer validarEdad(int edad) {
		if (edad < 0 || edad > 80) {
			return null;
		}
		return edad;
	}


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
