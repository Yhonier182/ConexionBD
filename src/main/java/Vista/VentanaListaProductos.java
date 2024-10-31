package Vista;

import Modelo.ProductoVo;
import Controlador.Coordinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class VentanaListaProductos extends JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coordinador miCoordinador;

    public VentanaListaProductos() {
        miCoordinador = new Coordinador();
        setTitle("Lista de Productos");
        setSize(850, 650);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fondo de la ventana
        getContentPane().setBackground(new Color(250, 250, 245));
        setLayout(new BorderLayout(10, 10)); // Espaciado alrededor

        // Inicializar datos de la tabla
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);

        // Estilo de la tabla
        tablaProductos.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaProductos.setRowHeight(28);
        tablaProductos.setSelectionBackground(new Color(255, 230, 200));
        tablaProductos.setGridColor(new Color(220, 220, 220));

        // Colores alternados en las filas
        tablaProductos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 240));
                return comp;
            }
        });

        // Personalización del encabezado de la tabla
        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 15));
        header.setBackground(new Color(100, 150, 200));
        header.setForeground(Color.WHITE);

        // ScrollPane con bordes y espaciado
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1)
        ));
        add(scrollPane, BorderLayout.CENTER);

        // Botón de actualización
        JButton btnActualizar = new JButton("Actualizar Productos");
        styleButton(btnActualizar);
        btnActualizar.addActionListener(e -> actualizarTablaProductos());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(250, 250, 245));
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
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

    // Estilo de botones con efecto "hover"
    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(120, 170, 210));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(220, 45));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 180), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto "hover" en el botón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 140, 180));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(120, 170, 210));
            }
        });
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
