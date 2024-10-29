package Vista;

import Controlador.Coordinador;
import Modelo.ProductoVo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaProductos extends JDialog implements ActionListener {

    private Coordinador miCoordinador;
    private JPanel panelProductos;
    private JLabel lblTitulo, lblIngresoNombre, lblIngresoID, lblPrecio, lblCantidad, lblPrecioValue, lblCantidadValue;
    private JTextField campoID, campoNombre;
    private JButton btnConsultar, btnComprar, btnCarrito, btnProductos;
    private JSeparator separadorSuperior, separadorInferior;
    private ProductoVo miProducto;

    public VentanaProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(750, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelProductos = new JPanel();
        panelProductos.setBackground(new Color(238, 238, 238));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelProductos.setLayout(null);

        lblTitulo = new JLabel("Compra y Consulta de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(54, 54, 54));
        lblTitulo.setBounds(20, 10, 700, 50);
        panelProductos.add(lblTitulo);

        lblIngresoNombre = new JLabel("Nombre del producto:");
        lblIngresoNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblIngresoNombre.setBounds(80, 80, 200, 20);
        panelProductos.add(lblIngresoNombre);

        lblIngresoID = new JLabel("ID del producto:");
        lblIngresoID.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblIngresoID.setBounds(80, 120, 200, 20);
        panelProductos.add(lblIngresoID);

        campoNombre = new JTextField();
        campoNombre.setBounds(280, 80, 250, 25);
        campoNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelProductos.add(campoNombre);

        campoID = new JTextField();
        campoID.setBounds(280, 120, 250, 25);
        campoID.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelProductos.add(campoID);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnConsultar.setBounds(570, 100, 140, 35);
        btnConsultar.setBackground(new Color(30, 144, 255));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addActionListener(this);
        panelProductos.add(btnConsultar);

        btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnComprar.setBounds(450, 240, 140, 35);
        btnComprar.setBackground(new Color(60, 179, 113));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(this);
        panelProductos.add(btnComprar);

        btnCarrito = new JButton("Ver carrito");
        btnCarrito.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnCarrito.setBounds(80, 320, 150, 35);
        btnCarrito.setBackground(new Color(255, 165, 0));
        btnCarrito.setForeground(Color.WHITE);
        btnCarrito.setFocusPainted(false);
        btnCarrito.addActionListener(this);
        panelProductos.add(btnCarrito);

        btnProductos = new JButton("Ver lista de productos");
        btnProductos.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnProductos.setBounds(520, 320, 200, 35);
        btnProductos.setBackground(new Color(30, 144, 255));
        btnProductos.setForeground(Color.WHITE);
        btnProductos.setFocusPainted(false);
        btnProductos.addActionListener(this);
        panelProductos.add(btnProductos);

        separadorSuperior = new JSeparator();
        separadorSuperior.setBounds(20, 180, 700, 10);
        panelProductos.add(separadorSuperior);

        separadorInferior = new JSeparator();
        separadorInferior.setBounds(20, 300, 700, 10);
        panelProductos.add(separadorInferior);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Verdana", Font.BOLD, 16));
        lblPrecio.setBounds(280, 200, 100, 30);
        lblPrecio.setForeground(new Color(54, 54, 54));
        panelProductos.add(lblPrecio);

        lblPrecioValue = new JLabel("0$");
        lblPrecioValue.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblPrecioValue.setBounds(350, 200, 100, 30);
        lblPrecioValue.setForeground(new Color(128, 128, 128));
        panelProductos.add(lblPrecioValue);

        lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Verdana", Font.BOLD, 16));
        lblCantidad.setBounds(280, 240, 100, 30);
        lblCantidad.setForeground(new Color(54, 54, 54));
        panelProductos.add(lblCantidad);

        lblCantidadValue = new JLabel("0 unidades");
        lblCantidadValue.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblCantidadValue.setBounds(380, 240, 120, 30);
        lblCantidadValue.setForeground(new Color(128, 128, 128));
        panelProductos.add(lblCantidadValue);

        getContentPane().add(panelProductos);
        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
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
            JOptionPane.showMessageDialog(this, "Se debe ingresar el ID o nombre del producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

        if (miProducto != null) {
            campoID.setText(miProducto.getIdProducto());
            campoNombre.setText(miProducto.getNombre());
            lblCantidadValue.setText(miProducto.getCantidad() + " unidades");
            lblPrecioValue.setText(miProducto.getPrecio() + "$");
        } else if (id.length() > 0 || nombre.length() > 0) {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprarProducto() {
        if (miProducto != null) {
            String documentoUsuario = JOptionPane.showInputDialog(this, "Ingrese el documento del usuario:");
            if (documentoUsuario != null && !documentoUsuario.isEmpty()) {
                boolean resultado = miCoordinador.comprar(miProducto.getIdProducto(), documentoUsuario);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Producto añadido al carrito con éxito.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo añadir el producto al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Se debe seleccionar un producto primero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConsultar) {
            consultar();
        } else if (e.getSource() == btnComprar) {
            comprarProducto();
        } else if (e.getSource() == btnProductos) {
            miCoordinador.mostrarVentanaListaProductos();
        } else if (e.getSource() == btnCarrito) {
            miCoordinador.mostrarVentanaCarrito();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame parentFrame = new JFrame();
            VentanaProductos ventana = new VentanaProductos(parentFrame, true);
            Coordinador coordinador = new Coordinador();
            ventana.setCoordinador(coordinador);
            ventana.setVisible(true);
        });
    }
}