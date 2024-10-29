package Vista;

import Controlador.Coordinador;
import Modelo.ProductoVo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.*;

public class VentanaProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;

    private JPanel panelProductos;
    private JLabel lblTitulo;
    private JLabel lblIngresoNombre;
    private JLabel lblIngresoID;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblPrecioValue;
    private JLabel lblCantidadValue;
    private JTextField campoID;
    private JTextField campoNombre;
    private JButton btnConsultar;
    private JButton btnComprar;
    private JButton btnCarrito;
    private JButton btnProductos;
    private JSeparator separadorSuperior;
    private JSeparator separadorInferior;
    private ProductoVo miProducto;
    private String id_producto;

    public VentanaProductos(Frame parent, boolean modal) {

        initComponents();
        setSize(710, 320);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelProductos = new JPanel();
        lblTitulo = new JLabel();
        lblIngresoNombre = new JLabel();
        lblIngresoID = new JLabel();
        lblPrecio = new JLabel();
        lblCantidad = new JLabel();
        lblPrecioValue = new JLabel();
        lblCantidadValue = new JLabel();
        campoID = new JTextField();
        campoNombre = new JTextField();
        btnConsultar = new JButton();
        btnComprar = new JButton();
        btnCarrito = new JButton();
        btnProductos = new JButton();
        separadorSuperior = new JSeparator();
        separadorInferior = new JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelProductos.setBackground(new java.awt.Color(204, 204, 204));
        panelProductos.setLayout(null);

        lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Compra y consulta de productos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelProductos.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 660, 60);

        lblIngresoNombre.setFont(new Font("Verdana", 1, 14));
        lblIngresoNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoNombre.setText("Ingresar el nombre del producto");
        panelProductos.add(lblIngresoNombre);
        lblIngresoNombre.setBounds(0, 90, 300, 20);

        lblIngresoID.setFont(new Font("Verdana", 1, 14));
        lblIngresoID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoID.setText("Ingresar el ID del producto");
        panelProductos.add(lblIngresoID);
        lblIngresoID.setBounds(0, 120, 300, 20);

        lblPrecio.setFont(new Font("Verdana", 1, 14));
        lblPrecio.setText("Precio: ");
        panelProductos.add(lblPrecio);
        lblPrecio.setBounds(245, 160, 200, 20);

        lblCantidad.setFont(new Font("Verdana", 1, 14));
        lblCantidad.setText("Cantidad: ");
        panelProductos.add(lblCantidad);
        lblCantidad.setBounds(245, 190, 200, 20);

        lblCantidadValue.setFont(new Font("Verdana", 0, 14));
        lblCantidadValue.setText("0 unidades");
        panelProductos.add(lblCantidadValue);
        lblCantidadValue.setBounds(320, 190, 200, 20);

        lblPrecioValue.setFont(new Font("Verdana", 0, 14));
        lblPrecioValue.setText("0$");
        panelProductos.add(lblPrecioValue);
        lblPrecioValue.setBounds(305, 160, 200, 20);

        panelProductos.add(separadorSuperior);
        separadorSuperior.setBounds(20, 150, 660, 10);
        panelProductos.add(separadorInferior);
        separadorInferior.setBounds(20, 225, 660, 10);
        panelProductos.add(campoNombre);
        campoNombre.setBounds(310, 90, 200, 20);
        panelProductos.add(campoID);
        campoID.setBounds(310, 120, 200, 20);

        btnConsultar.setFont(new Font("Verdana", 0, 14));
        btnConsultar.setText("Consultar");
        panelProductos.add(btnConsultar);
        btnConsultar.setBounds(540, 100, 140, 30);
        btnConsultar.addActionListener(this);

        btnComprar.setFont(new Font("Verdana", 0, 14));
        btnComprar.setText("Comprar");
        panelProductos.add(btnComprar);
        btnComprar.setBounds(470, 174, 140, 30);
        btnComprar.addActionListener(this);

        btnCarrito.setFont(new Font("Verdana", 0, 14));
        btnCarrito.setText("Ver carrito");
        panelProductos.add(btnCarrito);
        btnCarrito.setBounds(20, 240, 140, 30);
        btnCarrito.addActionListener(this);

        btnProductos.setFont(new Font("Verdana", 0, 14));
        btnProductos.setText("Ver lista de productos");
        panelProductos.add(btnProductos);
        btnProductos.setBounds(480, 240, 200, 30);
        btnProductos.addActionListener(this);

        getContentPane().add(panelProductos);
        panelProductos.setBounds(0, 0, 300, 350);
        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }

    private void consultar() {
        String id = campoID.getText();
        String nombre = campoNombre.getText();
        miProducto = null;
        if (id.length() > 0) {
            miProducto = miCoordinador.consultarProducto(id);
        } else if (nombre.length() > 0) {
            miProducto = miCoordinador.consultarProductoPorNombre(nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Se debe ingresar el ID o nombre del producto");
        }

        if (miProducto != null) {
            campoID.setText(miProducto.getIdProducto());
            campoNombre.setText(miProducto.getNombre());
            lblCantidadValue.setText(miProducto.getCantidad() + " unidades");
            lblPrecioValue.setText(miProducto.getPrecio() + "$");
        } else if (id.length() > 0 || nombre.length() > 0) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void comprar (String id_producto) {
        if (miProducto != null) {
            if (miCoordinador.comprar(miProducto.getIdProducto(), id_producto)) {
                JOptionPane.showMessageDialog(null, "Se ha realizado la compra de "+miProducto.getNombre()+" exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un producto primero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnConsultar) {
            consultar();
        }

        if (e.getSource() == this.btnComprar) {
            comprar((String) id_producto);
        }

        if (e.getSource() == this.btnProductos) {
            this.miCoordinador.mostrarVentanaListaProductos();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame parentFrame = new JFrame(); // Crea o usa una ventana padre
            boolean modal = true; // O false si no quieres que sea modal
            VentanaProductos ventana = new VentanaProductos(parentFrame, modal);
            Coordinador coordinador = new Coordinador();
            ventana.setCoordinador(coordinador);
            ventana.setVisible(true);
        });
    }
}

