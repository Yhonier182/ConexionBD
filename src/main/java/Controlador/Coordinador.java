package Controlador;

import Dao.ProductoDao;
import Dao.UsuarioDao;
import Modelo.Logica;
import Modelo.UsuarioVo;
import Vista.*;

public class Coordinador {

    private VentanaPrincipal miVentana;
    private VentanaLogin miLogin;
    private Logica miLogica;
    private VentanaConsultaIndividual miVentanaConsultaIndividual;
    private VentanaLista miVentanaLista;
    private UsuarioDao miUsuarioDao;
    private ProductoDao miProductoDao;
    private VistaProductos productos;
    private InactivarUsuarioUI inactivarUsuarioUI;


    public Coordinador() {
        this.miUsuarioDao = new UsuarioDao();
    }

    public void setVentanaPrincipal(VentanaPrincipal miVentana) {
        this.miVentana = miVentana;
    }

    public void setVentanaLogin(VentanaLogin miLogin) {
        this.miLogin = miLogin;
    }

    public void setLogica(Logica miLogica) {
        this.miLogica = miLogica;
    }

    public void setVentanaConsultaIndividual(VentanaConsultaIndividual miVentanaConsultaIndividual) {
        this.miVentanaConsultaIndividual = miVentanaConsultaIndividual;
    }

    public void setUsuarioDao(UsuarioDao miUsuarioDao) {
        this.miUsuarioDao = miUsuarioDao;
    }



    public void mostrarLogin() {
        miLogin.limpiar();
        miLogin.setVisible(true);
    }

    public void mostrarVentanaConsulta() {
        miVentanaConsultaIndividual.setVisible(true);
    }

    public void mostrarVentanaInactivacion() {
        if (inactivarUsuarioUI != null) {
            inactivarUsuarioUI.setVisible(true);
        }
    }


    public String validarIngreso(int index, String username, String password) {
        return miLogica.validarIngreso(index, username, password);
    }

    public void cerrarVentanaLogin() {
        miLogin.dispose();
    }

    public void asignarPrivilegios(int index, String usuario) {
        miVentana.asignarPrivilegios(index, usuario);
        miVentanaConsultaIndividual.asignarPrivilegios(index, usuario);
    }

    public String registrarUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao.registrarUsuario(miUsuarioVo);
    }

    public boolean validarCampos(UsuarioVo miUsuarioVo) {
        return miLogica.validarCampos(miUsuarioVo);
    }

    public Integer validarEdad(int edadIngresada) {
        return miLogica.validarEdad(edadIngresada);
    }

    public UsuarioVo consultarUsuario(String username, String password) {
        return miUsuarioDao.consultarUsuario(username, password);
    }

    public String actualizaUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao.actualizaUsuario(miUsuarioVo);
    }

    public String eliminarUsuario(String documento) {
        return miUsuarioDao.eliminarUsuario(documento);
    }

    public Integer validarTipo(String tipoIngresado) {
        return miLogica.validarTipo(tipoIngresado);
    }

    public UsuarioVo buscarUsuarioPorDocumento(String documento) {
        return miUsuarioDao.buscarUsuarioPorDocumento(documento);
    }

    // Métodos para activar o inactivar usuarios
    public String inactivarUsuario(String documento) {
        return miUsuarioDao.inactivarUsuario(documento);
    }

    public String activarUsuario(String documento) {
        return miUsuarioDao.activarUsuario(documento);
    }

    //mostrar ventana De estado del usuario
    public void setInactivarUsuarioUI(InactivarUsuarioUI inactivarUsuarioUI) {
        this.inactivarUsuarioUI = inactivarUsuarioUI;
    }

}
