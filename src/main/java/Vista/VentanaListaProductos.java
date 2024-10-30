package Vista;

import Modelo.ProductoVo;
import Controlador.Coordinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaListaProductos extends JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coordinador miCoordinador;

    public VentanaListaProductos() {
        setTitle("Lista de Productos");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicializar datos de la tabla
        String[] columnas = {"idProducto", "nombreProducto", "descripcion", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPaneProductos = new JScrollPane(tablaProductos);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void actualizarTablaProductos() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        ArrayList<ProductoVo> productos = miCoordinador.listarProductos(); // Obtiene los productos del Coordinador
        for (ProductoVo producto : productos) {
            modeloTabla.addRow(new Object[] {
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad()
            });
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