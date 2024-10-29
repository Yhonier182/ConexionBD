package Vista;

import Controlador.Coordinador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton botonConsultar;
    private JButton botonRegistrar;
    private JButton botonMostrarProductos;
    private JButton botonInactivar;  // Declaración del botón Inactivar
    private JLabel labelTitulo, labelInferior;
    private JPanel miPanelPrincipal, panelTitulo, panelInferior, panelCentral, panelMenu;

    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemOpciones;

    private Coordinador miCoordinador;

    public VentanaPrincipal() {
        initComponents();
        setTitle("Ventana Principal");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        barraMenu = new JMenuBar();
        menu = new JMenu("Opciones");
        itemOpciones = new JMenuItem("Cambiar de Usuario");
        itemOpciones.addActionListener(this);
        menu.add(itemOpciones);
        barraMenu.add(menu);
        setJMenuBar(barraMenu);

        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 32));
        labelTitulo.setForeground(Color.WHITE);

        miPanelPrincipal.setBackground(new Color(240, 240, 240));
        panelTitulo.setBackground(new Color(50, 50, 50));
        panelInferior.setBackground(new Color(50, 50, 50));
    }

    private void initComponents() {
        miPanelPrincipal = new JPanel(new BorderLayout());
        panelTitulo = new JPanel(new BorderLayout());
        panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentral = new JPanel(new BorderLayout());
        panelMenu = new JPanel();

        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBackground(new Color(240, 240, 240));

        labelTitulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        labelInferior = new JLabel("http://codejavu.blogspot.com", SwingConstants.CENTER);

        botonConsultar = new JButton("CONSULTAS");
        botonRegistrar = new JButton("REGISTRAR");
        botonMostrarProductos = new JButton("PRODUCTOS");
        botonInactivar = new JButton("INACTIVAR");  // Inicialización del botón Inactivar

        labelTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 32));
        labelTitulo.setForeground(Color.WHITE);
        panelTitulo.add(labelTitulo, BorderLayout.CENTER);

        labelInferior.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        labelInferior.setForeground(Color.WHITE);
        panelInferior.add(labelInferior);

        configurarBoton(botonConsultar);
        configurarBoton(botonRegistrar);
        configurarBoton(botonMostrarProductos);
        configurarBoton(botonInactivar);  // Configurar el botón Inactivar

        panelMenu.add(botonConsultar);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(botonRegistrar);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(botonMostrarProductos);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(botonInactivar);  // Agregar el botón Inactivar al panel

        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.add(panelMenu, BorderLayout.WEST);

        miPanelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        miPanelPrincipal.add(panelCentral, BorderLayout.CENTER);
        miPanelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        getContentPane().add(miPanelPrincipal);
    }

    private void configurarBoton(JButton boton) {
        boton.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(0, 122, 255));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 50));
        boton.addActionListener(this);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void asignarPrivilegios(int index, String nombre) {
        labelTitulo.setText("Bienvenido: " + nombre);
        switch (index) {
            case 1: // Administrador
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(true);
                break;
            case 2: // Usuario
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(true);
                botonInactivar.setVisible(false);
                break;
            case 3: // Secretaria
                botonConsultar.setVisible(true);
                botonRegistrar.setVisible(false);
                botonMostrarProductos.setVisible(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemOpciones) {
            miCoordinador.mostrarLogin();
        }
        if (e.getSource() == botonConsultar) {
            miCoordinador.mostrarVentanaConsulta();
        }
        if (e.getSource() == botonInactivar) {
            miCoordinador.mostrarVentanaInactivacion();
        }
        if (e.getSource() == botonMostrarProductos) {
            miCoordinador.mostrarVentanaProductos();
            // Lógica para mostrar productos
        }
    }
}
