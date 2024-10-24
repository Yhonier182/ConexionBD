package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Controlador.Coordinador;
import Modelo.UsuarioVo;

public class VentanaConsultaIndividual extends JDialog implements ActionListener {

    private JLabel LabelDireccion, TituloConsulta, labelDocumento, labelEdad, labelNombre, labelProfesion, labelTelefono, labelPassword, labelTipo, labelUsername;
    private JButton btonCancelar, btonConsultar, btonActualizar, btonEliminar, btonRegistrar;
    private JTextField campoTelefono, campoDireccion, campoDocumento, campoEdad, campoNombre, campoProfesion, campoPassword, campoBuscarUsuario, campoTipo, campoUsername;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JSeparator separadorInferior, separadorSuperior;
    private Coordinador miCoordinador;

    public VentanaConsultaIndividual(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(800, 450);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelConsulta = new javax.swing.JPanel();
        TituloConsulta = new javax.swing.JLabel();
        labelProfesion = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelDocumento = new javax.swing.JLabel();
        labelEdad = new javax.swing.JLabel();
        LabelDireccion = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        separadorInferior = new javax.swing.JSeparator();
        campoNombre = new javax.swing.JTextField();
        campoDireccion = new javax.swing.JTextField();
        campoTelefono = new javax.swing.JTextField();
        campoProfesion = new javax.swing.JTextField();
        campoEdad = new javax.swing.JTextField();
        campoTipo = new javax.swing.JTextField();
        campoUsername = new javax.swing.JTextField();
        separadorSuperior = new javax.swing.JSeparator();
        btonCancelar = new javax.swing.JButton();
        btonConsultar = new javax.swing.JButton();
        campoDocumento = new javax.swing.JTextField();
        campoPassword = new javax.swing.JTextField();
        campoBuscarUsuario = new javax.swing.JTextField();
        btonActualizar = new javax.swing.JButton();
        btonEliminar = new javax.swing.JButton();
        btonRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelConsulta.setBackground(new java.awt.Color(204, 204, 204));
        panelConsulta.setLayout(null);

        TituloConsulta.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36));
        TituloConsulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloConsulta.setText("Consultar Usuario");
        TituloConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelConsulta.add(TituloConsulta);
        TituloConsulta.setBounds(20, 10, 660, 60);

        labelProfesion.setFont(new java.awt.Font("Verdana", 0, 12));
        labelProfesion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProfesion.setText("Profesión:");
        panelConsulta.add(labelProfesion);
        labelProfesion.setBounds(0, 190, 90, 20);

        labelTelefono.setFont(new java.awt.Font("Verdana", 0, 12));
        labelTelefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTelefono.setText("Teléfono:");
        panelConsulta.add(labelTelefono);
        labelTelefono.setBounds(400, 220, 100, 20);

        labelDocumento.setFont(new java.awt.Font("Verdana", 0, 12));
        labelDocumento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDocumento.setText("*Documento:");
        panelConsulta.add(labelDocumento);
        labelDocumento.setBounds(0, 130, 90, 20);

        labelEdad.setFont(new java.awt.Font("Verdana", 0, 12));
        labelEdad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelEdad.setText("Edad:");
        panelConsulta.add(labelEdad);
        labelEdad.setBounds(400, 190, 100, 20);

        LabelDireccion.setFont(new java.awt.Font("Verdana", 0, 12));
        LabelDireccion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelDireccion.setText("Dirección:");
        panelConsulta.add(LabelDireccion);
        LabelDireccion.setBounds(0, 220, 90, 20);

        labelNombre.setFont(new java.awt.Font("Verdana", 0, 12));
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombre.setText("*Nombre:");
        panelConsulta.add(labelNombre);
        labelNombre.setBounds(0, 160, 90, 20);

        labelPassword.setFont(new java.awt.Font("Verdana", 0, 12));
        labelPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelPassword.setText("*Contraseña:");
        panelConsulta.add(labelPassword);
        labelPassword.setBounds(400, 130, 100, 20);

        labelTipo.setFont(new java.awt.Font("Verdana", 0, 12));
        labelTipo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTipo.setText("Tipo:");
        panelConsulta.add(labelTipo);
        labelTipo.setBounds(0, 250, 90, 20);

        labelUsername.setFont(new java.awt.Font("Verdana", 0, 12));
        labelUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUsername.setText("Usuario:");
        panelConsulta.add(labelUsername);
        labelUsername.setBounds(400, 160, 100, 20);

        panelConsulta.add(separadorInferior);
        separadorInferior.setBounds(20, 320, 660, 10);
        panelConsulta.add(campoNombre);
        campoNombre.setBounds(100, 160, 300, 20);
        panelConsulta.add(campoDireccion);
        campoDireccion.setBounds(100, 220, 300, 20);
        panelConsulta.add(campoTelefono);
        campoTelefono.setBounds(510, 220, 170, 20);
        panelConsulta.add(campoProfesion);
        campoProfesion.setBounds(100, 190, 300, 20);
        panelConsulta.add(campoEdad);
        campoEdad.setBounds(510, 190, 170, 20);
        panelConsulta.add(campoPassword);
        campoPassword.setBounds(510, 130, 170, 20);
        panelConsulta.add(campoTipo);
        campoTipo.setBounds(100, 250, 300, 20);
        panelConsulta.add(campoUsername);
        campoUsername.setBounds(510, 160, 170, 20);
        panelConsulta.add(separadorSuperior);
        separadorSuperior.setBounds(20, 120, 660, 10);

        campoBuscarUsuario.setBounds(100, 90, 300, 20);
        panelConsulta.add(campoBuscarUsuario);

        btonCancelar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonCancelar.setText("Cancelar");
        panelConsulta.add(btonCancelar);
        btonCancelar.setBounds(510, 350, 170, 30);
        btonCancelar.addActionListener(this);

        btonConsultar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonConsultar.setText("Buscar");
        btonConsultar.addActionListener(e -> buscarUsuario());
        panelConsulta.add(btonConsultar);
        btonConsultar.setBounds(420, 90, 110, 20);

        btonActualizar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonActualizar.setText("Actualizar");
        panelConsulta.add(btonActualizar);
        btonActualizar.setBounds(110, 350, 170, 30);
        btonActualizar.addActionListener(this);

        btonEliminar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonEliminar.setText("Eliminar");
        panelConsulta.add(btonEliminar);
        btonEliminar.setBounds(310, 350, 170, 30);
        btonEliminar.addActionListener(this);

        btonRegistrar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonRegistrar.setText("Registrar Usuario");
        panelConsulta.add(btonRegistrar);
        btonRegistrar.setBounds(510, 250, 170, 30);
        btonRegistrar.addActionListener(e -> registrarUsuario());

        panelConsulta.add(campoDocumento);
        campoDocumento.setBounds(100, 130, 300, 20);

        getContentPane().add(panelConsulta);
        panelConsulta.setBounds(0, 0, 710, 390);
        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btonCancelar) {
            limpiarVentana();
            dispose();
        }

        if (e.getSource() == btonActualizar) {

            actualizaUsuario();
        }

        if (e.getSource() == btonEliminar) {
            eliminaUsuario();
        }
    }

    public void asignarPrivilegios(int index, String nombre) {
        switch (index) {
            case 1: // Administrador
                btonActualizar.setVisible(true);
                btonRegistrar.setVisible(true);
                btonEliminar.setVisible(true);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                break;
            case 2: // Usuario
                btonActualizar.setVisible(true);
                btonRegistrar.setVisible(false);
                btonEliminar.setVisible(false);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                break;
            case 3: // Secretaria
                btonActualizar.setVisible(false);
                btonRegistrar.setVisible(true);
                btonEliminar.setVisible(false);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void limpiarVentana() {
        campoNombre.setText("");
        campoDocumento.setText("");
        campoBuscarUsuario.setText("");
        campoPassword.setText("");
        campoProfesion.setText("");
        campoDireccion.setText("");
        campoTelefono.setText("");
        campoEdad.setText("");
        campoTipo.setText("");
        campoUsername.setText("");
    }

    private void buscarUsuario() {
        String documento = campoBuscarUsuario.getText().trim();

        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacío",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioVo usuarioVO = miCoordinador.buscarUsuarioPorDocumento(documento);

        if (usuarioVO != null) {
            campoNombre.setText(usuarioVO.getNombre());
            campoDocumento.setText(usuarioVO.getDocumento());
            campoProfesion.setText(usuarioVO.getProfesion());
            campoDireccion.setText(usuarioVO.getDireccion());
            campoTelefono.setText(usuarioVO.getTelefono());
            campoEdad.setText(String.valueOf(usuarioVO.getEdad()));
            campoPassword.setText(usuarioVO.getPassword());
            campoTipo.setText(String.valueOf(usuarioVO.getTipo()));
            campoUsername.setText(usuarioVO.getUsername());
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en el sistema",
                    "Datos Inexistentes", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void actualizaUsuario() {
        Integer edad = miCoordinador.validarEdad(Integer.parseInt(campoEdad.getText().trim()));
        if (edad != null) {
            UsuarioVo miUsuarioVo = new UsuarioVo();
            miUsuarioVo.setDocumento(campoDocumento.getText().trim());
            miUsuarioVo.setNombre(campoNombre.getText().trim());
            miUsuarioVo.setEdad(edad);
            miUsuarioVo.setProfesion(campoProfesion.getText().trim());
            miUsuarioVo.setTelefono(campoTelefono.getText().trim());
            miUsuarioVo.setDireccion(campoDireccion.getText().trim());
            miUsuarioVo.setPassword(campoPassword.getText().trim());
            miUsuarioVo.setTipo(Integer.parseInt(campoTipo.getText().trim()));
            miUsuarioVo.setUsername(campoUsername.getText().trim());

            String actualiza = miCoordinador.validarCampos(miUsuarioVo) ?
                    miCoordinador.actualizaUsuario(miUsuarioVo) : "mas_datos";

            if ("ok".equals(actualiza)) {
                JOptionPane.showMessageDialog(null, "Se ha Modificado Correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String mensaje = "mas_datos".equals(actualiza) ?
                        "Debe Ingresar los campos obligatorios" : "Error al Modificar";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar una edad válida!!!", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        limpiarVentana();
    }


    private void eliminaUsuario() {
        String documento = campoDocumento.getText().trim();
        if (!documento.equals("")) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario " + documento + "?");
            if (JOptionPane.OK_OPTION == resp) {
                String elimina = miCoordinador.eliminarUsuario(documento);

                if (elimina.equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarVentana();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar ", "Información", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un documento ", "Información", JOptionPane.WARNING_MESSAGE);
        }
        limpiarVentana();
    }

    private void registrarUsuario() {
        UsuarioVo nuevoUsuario = new UsuarioVo();
        nuevoUsuario.setNombre(campoNombre.getText().trim());
        nuevoUsuario.setDocumento(campoDocumento.getText().trim());
        nuevoUsuario.setEdad(Integer.parseInt(campoEdad.getText().trim()));
        nuevoUsuario.setProfesion(campoProfesion.getText().trim());
        nuevoUsuario.setTelefono(campoTelefono.getText().trim());
        nuevoUsuario.setDireccion(campoDireccion.getText().trim());
        nuevoUsuario.setPassword(campoPassword.getText().trim());
        nuevoUsuario.setTipo(Integer.parseInt(campoTipo.getText().trim()));
        nuevoUsuario.setUsername(campoUsername.getText().trim());

        String resultado = miCoordinador.registrarUsuario(nuevoUsuario);
        if ("ok".equals(resultado)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            limpiarVentana();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
        limpiarVentana();
    }
}
