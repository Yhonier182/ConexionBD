package Vista;

import Controlador.Coordinador;
import Modelo.UsuarioVo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ventanaUsuarios extends JFrame {

    private Coordinador miCoordinador;
    private JTable tablaUsuarios;
    private DefaultTableModel modelo;

    public ventanaUsuarios() {
        initComponents();  // Inicializar los componentes de la ventana
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setSize(800, 400);  // Ajustar el tamaño de la ventana
    }

    /**
     * Método para establecer el coordinador.
     */
    public void setCoordinador(Coordinador coordinador) {
        this.miCoordinador = coordinador;
        mostrarTabla();  // Llenar la tabla con los datos del coordinador
    }

    /**
     * Método para inicializar componentes de la ventana.
     */
    private void initComponents() {
        miCoordinador = new Coordinador();
        // Crear el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(204, 204, 204));
        panelPrincipal.setLayout(new BorderLayout());

        // Crear la tabla y el modelo de la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("Documento");
        modelo.addColumn("Nombre");
        modelo.addColumn("Profesión");
        modelo.addColumn("Edad");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Tipo");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Usuario");
        modelo.addColumn("Estado");

        tablaUsuarios = new JTable(modelo);
        tablaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  // Desactivar el ajuste automático

        // Agregar la tabla dentro de un scroll pane
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        this.add(panelPrincipal);
    }

    /**
     * Método para mostrar los datos en la tabla.
     */
    public void mostrarTabla() {
        modelo.setRowCount(0);  // Limpiar el modelo antes de agregar los datos

        // Obtener la lista de usuarios desde el Coordinador
        ArrayList<UsuarioVo> listaUsuarios = miCoordinador.listarUsuarios();

        // Llenar la tabla con los datos de la lista de usuarios
        for (UsuarioVo usuario : listaUsuarios) {
            String[] datos = new String[10];
            datos[0] = usuario.getDocumento();
            datos[1] = usuario.getNombre();
            datos[2] = usuario.getProfesion();
            datos[3] = String.valueOf(usuario.getEdad());
            datos[4] = usuario.getDireccion();
            datos[5] = usuario.getTelefono();
            datos[6] = String.valueOf(usuario.getTipo());
            datos[7] = usuario.getPassword();
            datos[8] = usuario.getUsername();
            datos[9] = String.valueOf(usuario.getEstado());
            modelo.addRow(datos);  // Agregar fila al modelo
        }

        // Ajustar el tamaño de las columnas
        ajustarColumnas();
    }

    /**
     * Método para ajustar el tamaño de las columnas de la tabla.
     */
    private void ajustarColumnas() {
        tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);  // Documento
        tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nombre
        tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);  // Profesión
        tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(50);   // Edad
        tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(200);  // Dirección
        tablaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(100);  // Teléfono
        tablaUsuarios.getColumnModel().getColumn(6).setPreferredWidth(80);   // Tipo
        tablaUsuarios.getColumnModel().getColumn(7).setPreferredWidth(100);  // Contraseña
        tablaUsuarios.getColumnModel().getColumn(8).setPreferredWidth(150);  // Usuario
        tablaUsuarios.getColumnModel().getColumn(9).setPreferredWidth(80);   // Estado
    }

    // Método principal para ejecutar la ventana de usuarios
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ventanaUsuarios ventana = new ventanaUsuarios();
            Coordinador coordinador = new Coordinador();  // Crear instancia de Coordinador
            ventana.setCoordinador(coordinador);  // Asignar el Coordinador a la ventana
            ventana.setVisible(true);  // Hacer visible la ventana
        });
    }
}