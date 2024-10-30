package Controlador;

import Dao.ProductoDao;
import Dao.UsuarioDao;
import Modelo.Logica;
import Modelo.ProductoVo;
import Modelo.UsuarioVo;
import Vista.*;
import conexion.Conexion;
import Modelo.LogicaCarrito;

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
    private Conexion conexion;
    private LogicaCarrito miCarrito;

    public Coordinador() {
        this.conexion = new Conexion(); // Inicia conexión a base de datos
        this.miCarrito = new LogicaCarrito();
        this.miUsuarioDao = new UsuarioDao();
        this.miProductoDao = new ProductoDao();

        // Establecer coordinador en Daos y lógica del carrito para acceso a la conexión
        this.miProductoDao.setCoordinador(this);
        this.miUsuarioDao.setCoordinador(this);
        this.miCarrito.setCoordinador(this);
    }

    // Método para obtener la conexión
    public Conexion getConexion() {
        return this.conexion;
    }

    // Métodos de configuración de ventanas
    public void setVentanaPrincipal(VentanaPrincipal miVentana)
    { this.miVentana = miVentana; }
    public void setVentanaLogin(VentanaLogin miLogin)
    { this.miLogin = miLogin; }
    public void setLogica(Logica miLogica)
    { this.miLogica = miLogica; }
    public void setVentanaConsultaIndividual(VentanaConsultaIndividual miVentanaConsultaIndividual)
    { this.miVentanaConsultaIndividual = miVentanaConsultaIndividual; }
    public void setUsuarioDao(UsuarioDao miUsuarioDao)
    { this.miUsuarioDao = miUsuarioDao; }

    // Métodos para mostrar ventanas
    public void mostrarLogin() { miLogin.limpiar(); miLogin.setVisible(true); }
    public void mostrarVentanaConsulta()
    { miVentanaConsultaIndividual.setVisible(true); }
    public void mostrarVentanaInactivacion()
    { if (inactivarUsuarioUI != null) inactivarUsuarioUI.setVisible(true); }
    public void cerrarVentanaLogin()
    { miLogin.dispose(); }

    // Método para asignar privilegios
    public void asignarPrivilegios(int index, String usuario) {
        // Asigna privilegios en las ventanas principales según el índice y el nombre de usuario
        if (miVentana != null) {
            miVentana.asignarPrivilegios(index, usuario);
        }
        if (miVentanaConsultaIndividual != null) {
            miVentanaConsultaIndividual.asignarPrivilegios(index, usuario);
        }
    }

    // Métodos de usuario
    public String validarIngreso(int index, String username, String password)
    { return miLogica.validarIngreso(index, username, password); }
    public String registrarUsuario(UsuarioVo miUsuarioVo)
    { return miUsuarioDao.registrarUsuario(miUsuarioVo); }
    public boolean validarCampos(UsuarioVo miUsuarioVo)
    { return miLogica.validarCampos(miUsuarioVo); }
    public Integer validarEdad(int edadIngresada)
    { return miLogica.validarEdad(edadIngresada); }
    public UsuarioVo consultarUsuario(String username, String password)
    { return miUsuarioDao.consultarUsuario(username, password); }
    public String actualizaUsuario(UsuarioVo miUsuarioVo)
    { return miUsuarioDao.actualizaUsuario(miUsuarioVo); }
    public String eliminarUsuario(String documento)
    { return miUsuarioDao.eliminarUsuario(documento); }
    public Integer validarTipo(String tipoIngresado)
    { return miLogica.validarTipo(tipoIngresado); }
    public UsuarioVo buscarUsuarioPorDocumento(String documento)
    { return miUsuarioDao.buscarUsuarioPorDocumento(documento); }
    public String inactivarUsuario(String documento)
    { return miUsuarioDao.inactivarUsuario(documento); }
    public String activarUsuario(String documento)
    { return miUsuarioDao.activarUsuario(documento); }
    public void setInactivarUsuarioUI(InactivarUsuarioUI inactivarUsuarioUI)
    { this.inactivarUsuarioUI = inactivarUsuarioUI; }

    // Métodos de producto
    public ProductoVo consultarProducto(String id)
    { return miProductoDao.consultarProducto(id); }
    public ProductoVo consultarProductoPorNombre(String nombre)
    { return miProductoDao.consultarProductoPorNombre(nombre); }
    public ArrayList<ProductoVo> listarProductos()
    { return miProductoDao.listarProductos(); }
    public void mostrarVentanaProductos() {
        if (ventanaProductos == null) {
            ventanaProductos = new VentanaProductos(null, true);
            ventanaProductos.setCoordinador(this);
        }
        ventanaProductos.setVisible(true);
    }

    // Método de compra de producto
    public boolean comprar(String idProducto, String documentoUsuario) {
        return miCarrito.comprar(idProducto, documentoUsuario); // Usa la lógica del carrito para registrar la compra
    }

    // Mostrar ventana de lista de productos
    public void mostrarVentanaListaProductos() {
        if (miVentanaLista == null) {
            miVentanaLista = new VentanaListaProductos();
            miVentanaLista.setCoordinador(this);
        }
        miVentanaLista.setVisible(true);
    }

    // Método para mostrar el carrito y cargar los productos en él
    public ArrayList<String> obtenerProductosCarrito(String documento) {
        return miCarrito.obtenerProductosCarrito(documento); // Llama al método obtenerProductosCarrito en LogicaCarrito para obtener productos del carrito
    }

    public void mostrarVentanaCarrito() {
        VentanaCarritoCompras ventanaCarrito = new VentanaCarritoCompras(null, true);
        ventanaCarrito.setCoordinador(this);
        ventanaCarrito.setVisible(true);
    }
}


