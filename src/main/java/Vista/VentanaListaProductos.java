package Vista;


import Controlador.Coordinador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class VentanaListaProductos extends JFrame {

    private JButton btnVerProductos;  // Botón para abrir la ventana de productos
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coordinador miCoordinador;

    public VentanaListaProductos() {
        setTitle("Lista de Productos");
        setSize(900, 600);
        setLocationRelativeTo(null);  // Centra la ventana de VentanaLista
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicializar los datos de la tabla
        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPaneProductos = new JScrollPane(tablaProductos);

        // Crear un panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Crear el botón para abrir VistaProductos
        btnVerProductos = new JButton("Ver Productos");
        styleButton(btnVerProductos);

        // Acción para abrir VistaProductos
        btnVerProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVistaProductos();
            }
        });

        // Añadir el botón al panel
        panelBotones.add(btnVerProductos);

        // Crear un panel principal y añadir el panel de botones y la tabla
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPaneProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Añadir el panel principal a la ventana
        add(panelPrincipal);

        // Para probar la tabla con datos de ejemplo
        List<String[]> productos = new ArrayList<>();
        productos.add(new String[]{"Producto 1", "10"});
        productos.add(new String[]{"Producto 2", "20"});
        productos.add(new String[]{"Producto 3", "30"});

        actualizarTabla(productos);
    }

    // Método para actualizar la tabla con los productos
    public void actualizarTabla(List<String[]> productos) {
        // Limpiar la tabla antes de llenarla
        modeloTabla.setRowCount(0);

        // Añadir los productos a la tabla
        for (String[] producto : productos) {
            modeloTabla.addRow(producto);
        }
    }

    // Método para abrir VistaProductos
    private void abrirVistaProductos() {
        if (miCoordinador != null) { // Verifica que miCoordinador esté inicializado
            VentanaProductos vistaProductos = new VentanaProductos(null, true);
            vistaProductos.setCoordinador(miCoordinador); // Asegura que el coordinador está configurado
            vistaProductos.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Coordinador no asignado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para dar estilo a los botones
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 122, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 200), 2));
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            VentanaListaProductos ventanaLista = new VentanaListaProductos();
            ventanaLista.setVisible(true);
        });
    }

    public void setCoordinador(Coordinador coordinador) {
        this.miCoordinador=miCoordinador;
    }
}