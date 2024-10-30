package Controlador;

import Dao.ProductoDao; // Importar ProductoDao
import Dao.UsuarioDao;
import Modelo.Logica;
import Modelo.LogicaCarrito;
import Modelo.ProductoVo;
import Modelo.UsuarioVo;
import Vista.*;
import conexion.Conexion;
import java.sql.Connection;
import java.util.ArrayList;

public class Coordinador {

    private  Connection conexion;
    private VentanaPrincipal miVentana;
    private VentanaLogin miLogin;
    private Logica miLogica;
    private VentanaConsultaIndividual miVentanaConsultaIndividual;
    private VentanaListaProductos miVentanaLista;
    private UsuarioDao miUsuarioDao;
    private ProductoDao miProductoDao;
    private VentanaProductos ventanaProductos;
    private InactivarUsuarioUI inactivarUsuarioUI;
    private ventanaUsuarios ventanaUsuarios;
    private LogicaCarrito logicaCarrito;

    private String documentoUsuario;


    public Coordinador() {
        this.logicaCarrito=new LogicaCarrito();
        this.miUsuarioDao = new UsuarioDao();
        this.miProductoDao = new ProductoDao();
        this.miProductoDao.setCoordinador(this);
        this.miUsuarioDao.setCoordinador(this);
        this.logicaCarrito.setCoordinador(this);
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return Conexion.getInstance().getConnection();
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
    // Método para establecer el documento del usuario
    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
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

    public void mostrarVentanaUsuarios() {
        if (ventanaUsuarios == null) {
            ventanaUsuarios = new ventanaUsuarios();
            ventanaUsuarios.setCoordinador(this);
        }
        ventanaUsuarios.setVisible(true);
    }

    public void mostrarVentanaListaProductos() {
        if (miVentanaLista == null) {
            miVentanaLista = new VentanaListaProductos();
            miVentanaLista.setCoordinador(this);
        }
        miVentanaLista.setVisible(true);
    }

    public void mostrarVentanaProductos() {
        if (ventanaProductos == null) {
            ventanaProductos = new VentanaProductos(null, true);
            ventanaProductos.setCoordinador(this);
        }
        ventanaProductos.setVisible(true);
    }

    public void mostrarVentanaCarrito() {
        VentanaCarritoCompras ventanaCarrito = new VentanaCarritoCompras(null, true);
        ventanaCarrito.setCoordinador(this);
        ventanaCarrito.refrescar();
        ventanaCarrito.setVisible(true);
    }

    // usar metodos

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

    //listarUsuarios
    public ArrayList<UsuarioVo> listarUsuarios() {
        return miUsuarioDao.listarUsuarios();
    }


    // Metodos para interactuar con productoDao
    public boolean registrarProducto(ProductoVo producto) {
        return miProductoDao.registrarProducto(producto);
    }


    public ProductoVo consultarProducto(String idProducto) {
        return miProductoDao.consultarProducto(idProducto);
    }

    public boolean actualizarProducto(ProductoVo producto) {
        return miProductoDao.actualizarProducto(producto);
    }

    public boolean eliminarProducto(String idProducto) {
        return miProductoDao.eliminarProducto(idProducto);
    }

    public ArrayList<ProductoVo> listarProductos() {
        if (miProductoDao != null) {
            return miProductoDao.listarProductos();
        } else {
            System.out.println("Error: ProductoDao no está inicializado.");
            return new ArrayList<>();
        }
    }


    // Método de compra de producto
    public boolean comprar(String idProducto, String id_usuario) {
        return logicaCarrito.comprar(idProducto,documentoUsuario);
    }

    // Método para listar productos en el carrito
    public ArrayList<String> listarCarrito() {
        if (documentoUsuario != null) {
            return miProductoDao.listarCarrito(documentoUsuario);
        } else {
            System.out.println("Error: No se ha definido el documento del usuario.");
            return new ArrayList<>();
        }
    }

    public ProductoVo consultarProductoPorNombre(String nombre) {

        return null;
    }
}




