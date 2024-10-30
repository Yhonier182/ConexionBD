package Vista;

import Modelo.ProductoVo;
import Controlador.Coordinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaListaProductos extends JFrame implements ActionListener {

    private JButton btnVerProductos; // Botón para abrir la ventana de productos
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coordinador miCoordinador;

    public VentanaListaProductos() {
        setTitle("Lista de Productos");
        setSize(900, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicializar datos de la tabla
        String[] columnas = {"idProducto", "nombreProducto", "descripcion", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPaneProductos = new JScrollPane(tablaProductos);

        // Crear panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Botón "Ver Productos" para abrir VentanaProductos
        btnVerProductos = new JButton("Ver Productos");
        styleButton(btnVerProductos);
        btnVerProductos.addActionListener(this);

        panelBotones.add(btnVerProductos); // Agregar botón al panel de botones

        // Panel principal con tabla y botones
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPaneProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Llenar la tabla con productos iniciales desde la base de datos
        actualizarTablaProductos();
    }

    // Método para actualizar la tabla con productos desde la base de datos
    private void actualizarTablaProductos() {
        if (miCoordinador == null) {
            JOptionPane.showMessageDialog(this, "Coordinador no asignado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<ProductoVo> productos = miCoordinador.listarProductos(); // Obtener productos desde Coordinador
        if (productos == null || productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos en la base de datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        modeloTabla.setRowCount(0); // Limpiar tabla antes de agregar nuevos datos

        for (ProductoVo producto : productos) {
            String[] datos = {
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    String.valueOf(producto.getPrecio()),
                    String.valueOf(producto.getCantidad()),

            };
            modeloTabla.addRow(datos); // Agregar producto a la tabla
        }
    }

    // Estilo de botones
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 122, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 200), 2));
    }

    // Manejo de eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerProductos) {
            actualizarTablaProductos();
            abrirVistaProductos();
        }
    }

    // Método para abrir VentanaProductos
    private void abrirVistaProductos() {
        if (miCoordinador != null) {
            VentanaProductos vistaProductos = new VentanaProductos(this, true);
            vistaProductos.setCoordinador(miCoordinador); // Pasar el coordinador
            vistaProductos.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Coordinador no asignado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para establecer el Coordinador
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    // Método principal para iniciar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Coordinador coordinador = new Coordinador();
            VentanaListaProductos ventanaLista = new VentanaListaProductos();
            ventanaLista.setCoordinador(coordinador); // Asignar coordinador
            ventanaLista.actualizarTablaProductos();
            ventanaLista.setVisible(true);

        });
    }

}