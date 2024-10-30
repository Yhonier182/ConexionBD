package Controlador;

import Dao.ProductoDao;
import Dao.UsuarioDao;
import Modelo.Logica;
import Modelo.ProductoVo;
import Modelo.UsuarioVo;
import Vista.*;
import conexion.Conexion;

import java.util.ArrayList;

public class Coordinador {

    private VentanaPrincipal miVentana;
    private VentanaLogin miLogin;
    private Logica miLogica;
    private VentanaConsultaIndividual miVentanaConsultaIndividual;
    private VentanaListaProductos miVentanaLista;
    private UsuarioDao miUsuarioDao;
    private ProductoDao miProductoDao;
    private VentanaProductos ventanaProductos;
    private InactivarUsuarioUI inactivarUsuarioUI;
    private ventanaUsuarios USER;


    private Conexion conexion;

    public Coordinador() {
        this.miUsuarioDao = new UsuarioDao();
        this.miProductoDao = new ProductoDao();
        this.miProductoDao.setCoordinador(this); // Set Coordinador to ProductoDao

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

    public void cerrarVentanaLogin() {
        miLogin.dispose();
    }

    public void asignarPrivilegios(int index, String usuario) {
        miVentana.asignarPrivilegios(index, usuario);
        miVentanaConsultaIndividual.asignarPrivilegios(index, usuario);
    }

    public String validarIngreso(int index, String username, String password) {
        return miLogica.validarIngreso(index, username, password);
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

    public ProductoVo consultarProducto(String id) {
        return miProductoDao.consultarProducto(id);
    }

    public ProductoVo consultarProductoPorNombre(String nombre) {
        return miProductoDao.consultarProductoPorNombre(nombre);
    }

     //listarUsuarios
     public ArrayList<UsuarioVo> listarUsuarios() {
         return miUsuarioDao.listarUsuarios();
     }



    public void mostrarVentanaProductos() {
        if (ventanaProductos == null) {
            ventanaProductos = new VentanaProductos(null, true);
            ventanaProductos.setCoordinador(this);
        }
        ventanaProductos.setVisible(true);
    }

    // Método de compra de producto
    public boolean comprar(String idProducto) {
        return miProductoDao.comprarProducto(idProducto);
    }

    public void mostrarVentanaListaProductos(VentanaProductos ventanaProductos) {
        if (miVentanaLista == null) {
            miVentanaLista = new VentanaListaProductos();
            miVentanaLista.setCoordinador(this); // Asigna el Coordinador
        }
        miVentanaLista.actualizarTablaProductos();

        miVentanaLista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ventanaProductos.limpiarCampos(); // Limpia los campos
                ventanaProductos.setVisible(false); // Restaura VentanaProductos
            }
        });

        miVentanaLista.setVisible(true); // Muestra la lista de productos
    }

    public void mostrarVentanaUsuarios() {
      if (USER != null){
          USER.setVisible(true);
      }
    }



    public ArrayList<ProductoVo> listarProductos() {
        return miProductoDao.listarProductos();
    }
    }



