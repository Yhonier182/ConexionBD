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
    private JButton btnConsultar, btnComprar, btnProductos;
    private JSeparator separadorSuperior, separadorInferior;
    private ProductoVo miProducto;

    public VentanaProductos() {
        initComponents();
        setSize(750, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        miCoordinador = new Coordinador();
        panelProductos = new JPanel();
        panelProductos.setBackground(new Color(238, 238, 238));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelProductos.setLayout(null);

        // Título estilizado
        lblTitulo = new JLabel("Compra y Consulta de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(54, 54, 54));
        lblTitulo.setBounds(20, 10, 700, 50);
        panelProductos.add(lblTitulo);

        // Etiquetas de instrucciones
        lblIngresoNombre = new JLabel("Nombre del producto:");
        lblIngresoNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblIngresoNombre.setBounds(80, 80, 200, 20);
        panelProductos.add(lblIngresoNombre);

        lblIngresoID = new JLabel("ID del producto:");
        lblIngresoID.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblIngresoID.setBounds(80, 120, 200, 20);
        panelProductos.add(lblIngresoID);

        // Campos de texto
        campoNombre = new JTextField();
        campoNombre.setBounds(280, 80, 250, 25);
        campoNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelProductos.add(campoNombre);

        campoID = new JTextField();
        campoID.setBounds(280, 120, 250, 25);
        campoID.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelProductos.add(campoID);

        // Botones con estilo y colores
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
        btnComprar.setBounds(500, 240, 140, 35);
        btnComprar.setBackground(new Color(60, 179, 113));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(this);
        panelProductos.add(btnComprar);

        btnProductos = new JButton("Ver lista de productos");
        btnProductos.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnProductos.setBounds(520, 320, 200, 35);
        btnProductos.setBackground(new Color(30, 144, 255));
        btnProductos.setForeground(Color.WHITE);
        btnProductos.setFocusPainted(false);
        btnProductos.addActionListener(this);
        panelProductos.add(btnProductos);

        // Separadores
        separadorSuperior = new JSeparator();
        separadorSuperior.setBounds(20, 180, 700, 10);
        panelProductos.add(separadorSuperior);

        separadorInferior = new JSeparator();
        separadorInferior.setBounds(20, 300, 700, 10);
        panelProductos.add(separadorInferior);

        // Etiquetas de precio y cantidad
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

    public void setCoordinador(Coordinador coordinador) {
        this.miCoordinador = coordinador;
    }


    private void consultar() {
        String id = campoID.getText();
        String nombreProducto = campoNombre.getText();

        if (id.isEmpty() && nombreProducto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID o nombre para consultar el producto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        miProducto = id.isEmpty() ? miCoordinador.consultarProductoPorNombre(nombreProducto) : miCoordinador.consultarProducto(id);

        if (miProducto != null) {
            campoID.setText(miProducto.getIdProducto());
            campoNombre.setText(miProducto.getNombre());
            lblCantidadValue.setText(miProducto.getCantidad() + " unidades");
            lblPrecioValue.setText(miProducto.getPrecio() + "$");
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprarProducto() {
        if (miProducto != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado la compra de " + miProducto.getNombre() + " exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        campoID.setText("");
        campoNombre.setText("");
        lblPrecioValue.setText("0$");
        lblCantidadValue.setText("0 unidades");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConsultar) {
            consultar();
        } else if (e.getSource() == btnComprar) {
            comprarProducto();
        } else if (e.getSource() == btnProductos) {
           miCoordinador.mostrarVentanaListaProductos();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaProductos ventana = new VentanaProductos();
            ventana.setVisible(true);
        });
    }
}
