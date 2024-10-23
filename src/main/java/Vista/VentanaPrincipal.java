package Vista;

import Controlador.Coordinador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton botonConsultar;
    private JButton botonRegistrar;
    private JButton botonMostrarProductos;  // Nuevo botón para mostrar la lista
    private JLabel labelTitulo, labelInferior;
    private JPanel miPanelPrincipal, panelTitulo, panelInferior, panelCentral, panelMenu;

    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemOpciones;

    private Coordinador miCoordinador;

    public VentanaPrincipal() {
        initComponents();
        setTitle("Ventana Principal");
        setSize(650, 450);  // Ajusté el tamaño para que tenga suficiente espacio
        setLocationRelativeTo(null);  // Centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración de la barra de menú
        barraMenu = new JMenuBar();
        menu = new JMenu("Opciones");
        itemOpciones = new JMenuItem("Cambiar de Usuario");
        itemOpciones.addActionListener(this);
        menu.add(itemOpciones);
        barraMenu.add(menu);
        setJMenuBar(barraMenu);

        // Configurar título
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 32));
        labelTitulo.setForeground(Color.WHITE);

        // Asignar fondo de color y bordes
        miPanelPrincipal.setBackground(new Color(240, 240, 240)); // Color claro
        panelTitulo.setBackground(new Color(50, 50, 50));  // Color oscuro
        panelInferior.setBackground(new Color(50, 50, 50));  // Color oscuro
    }

    private void initComponents() {
        // Inicializar paneles
        miPanelPrincipal = new JPanel(new BorderLayout());
        panelTitulo = new JPanel(new BorderLayout());
        panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentral = new JPanel(new BorderLayout());
        panelMenu = new JPanel();  // Panel para el "menú"

        // Usar un BoxLayout para organizar los botones verticalmente
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBackground(new Color(240, 240, 240)); // Color de fondo claro

        // Inicializar etiquetas
        labelTitulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        labelInferior = new JLabel("http://codejavu.blogspot.com", SwingConstants.CENTER);

        // Inicializar botones
        botonConsultar = new JButton("CONSULTAR");
        botonRegistrar = new JButton("REGISTRAR");
        botonMostrarProductos = new JButton("PRODUCTOS");  // El nuevo botón para mostrar la lista

        // Configurar panel de título
        labelTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 32));
        labelTitulo.setForeground(Color.WHITE);
        panelTitulo.add(labelTitulo, BorderLayout.CENTER);

        // Configurar panel inferior
        labelInferior.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        labelInferior.setForeground(Color.WHITE);
        panelInferior.add(labelInferior);

        // Configurar botones
        botonConsultar.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));  // Ajustamos el tamaño de la fuente
        botonConsultar.setBackground(new Color(0, 122, 255));  // Azul claro
        botonConsultar.setForeground(Color.WHITE);
        botonConsultar.setFocusPainted(false);
        botonConsultar.setPreferredSize(new Dimension(200, 50));  // Ajuste de tamaño
        botonConsultar.setToolTipText("Consultar usuario en el sistema");
        botonConsultar.addActionListener(this);

        botonRegistrar.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        botonRegistrar.setBackground(new Color(0, 122, 255));  // Azul claro
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setPreferredSize(new Dimension(200, 50));  // Ajuste de tamaño
        botonRegistrar.addActionListener(this);

        // Botón para mostrar la lista
        botonMostrarProductos.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        botonMostrarProductos.setBackground(new Color(0, 122, 255));  // Azul claro
        botonMostrarProductos.setForeground(Color.WHITE);
        botonMostrarProductos.setFocusPainted(false);
        botonMostrarProductos.setPreferredSize(new Dimension(200, 50));  // Ajuste de tamaño
        botonMostrarProductos.addActionListener(this);

        // Agregar los botones al panelMenu (menú lateral)
        panelMenu.add(botonConsultar);
        panelMenu.add(Box.createVerticalStrut(10));  // Espaciado entre los botones
        panelMenu.add(botonRegistrar);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(botonMostrarProductos);  // Agregar el nuevo botón

        // Asegurar que los botones estén alejados de los bordes con un espaciado adecuado
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Espaciado de 20 píxeles

        // Configurar el panel central para que los botones estén bien alineados
        panelCentral.add(panelMenu, BorderLayout.WEST);  // Colocamos el menú a la izquierda

        // Agregar los paneles al panel principal
        miPanelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        miPanelPrincipal.add(panelCentral, BorderLayout.CENTER);
        miPanelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar el panel principal al contenido de la ventana
        getContentPane().add(miPanelPrincipal);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void asignarPrivilegios(int index, String nombre) {
        labelTitulo.setText("Bienvenido: " + nombre);

        // Mostrar u ocultar botones según el tipo de usuario
        switch (index) {
            case 1: // Administrador
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(true);
                botonMostrarProductos.setVisible(true); // Mostrar el botón para todos
                break;
            case 2: // Usuario
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(true); // Mostrar el botón
                break;
            case 3: // Secretaria
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(true); // Mostrar el botón
                break;
            default:
                botonConsultar.setVisible(false);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(false); // Ocultar el botón si no hay usuario
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemOpciones) {
            miCoordinador.mostrarLogin();
        }
        if (e.getSource() == botonRegistrar) {

        }
        if (e.getSource() == botonConsultar) {
            miCoordinador.mostrarVentanaConsulta();
        }
        if (e.getSource() == botonMostrarProductos) {

            VistaProductos vistaProductos = new VistaProductos();
            vistaProductos.setVisible(true);
        }
    }
}
