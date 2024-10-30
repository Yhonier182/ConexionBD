package Vista;

import java.util.ArrayList;
import Controlador.Coordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.*;

public class VentanaCarritoCompras extends JDialog implements ActionListener {
        private Coordinador miCoordinador;

        private JPanel panelCarrito;
        private JLabel lblTitulo;
        private JButton btnRefrescar;
        private JList<String> listaCompras;  // Especificamos el tipo JList<String>
        private JScrollPane escroleable;
        private DefaultListModel<String> modelo;  // Especificamos el tipo DefaultListModel<String>

        public VentanaCarritoCompras(Frame parent, boolean modal) {
            super(parent, modal);
            initComponents();
            setSize(500, 320);
            setResizable(false);
            setLocationRelativeTo(null);
        }

        private void initComponents() {
            panelCarrito = new JPanel();
            lblTitulo = new JLabel();
            btnRefrescar = new JButton();
            listaCompras = new JList<>();
            escroleable = new JScrollPane();
            modelo = new DefaultListModel<>();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            panelCarrito.setBackground(new java.awt.Color(204, 204, 204));
            panelCarrito.setLayout(null);

            btnRefrescar.setFont(new Font("Verdana", 0, 14));
            btnRefrescar.setText("Refrescar");
            panelCarrito.add(btnRefrescar);
            btnRefrescar.setBounds(170, 80, 150, 25);
            btnRefrescar.addActionListener(this);  // Mantenemos la implementación de ActionListener

            lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
            lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTitulo.setText("Carrito");
            lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            panelCarrito.add(lblTitulo);
            lblTitulo.setBounds(20, 10, 440, 60);

            escroleable.setViewportView(listaCompras);
            listaCompras.setModel(modelo);
            panelCarrito.add(escroleable);
            escroleable.setBounds(20, 115, 440, 130);

            getContentPane().add(panelCarrito);
            panelCarrito.setBounds(0, 0, 500, 350);
            pack();
        }

        public void setCoordinador(Coordinador miCoordinador) {
            this.miCoordinador = miCoordinador;
        }

        public void refrescar() {
            String documentoUsuario = "usuario_id";
            ArrayList<String> compras = miCoordinador.listarCarrito();
            if (compras != null) {
                modelo.clear();
                if (!compras.isEmpty()) {
                    for (String producto : compras) {
                        modelo.addElement(producto);
                    }
                } else {
                    modelo.addElement("No hay productos en el carrito");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar la lista de productos comprados", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnRefrescar) {
                refrescar();
            }
        }
    // Método main para probar la ventana de carrito
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame parentFrame = new JFrame();
            VentanaCarritoCompras ventanaCarrito = new VentanaCarritoCompras(parentFrame, true);

            // Crear coordinador y configurar
            Coordinador coordinador = new Coordinador();
            ventanaCarrito.setCoordinador(coordinador);

            // Mostrar ventana
            ventanaCarrito.setVisible(true);
        });
    }
}
