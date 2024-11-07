package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import java.sql.*;
import java.util.Date;
import java.util.Vector;
import util.Conexion;

public class FuncionarioForm extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel panelFuncionarios;
    private JPanel panelCrearFuncionario;

    private JTextField txtBuscarFuncionario;
    private JButton btnEditarFuncionario;
    private JButton btnEliminarFuncionario;
    private JTable tablaFuncionarios;

    // Campos de Funcionario
    private JTextField txtIdFuncionario;
    private JTextField txtTipoIdentificacion;
    private JTextField txtNumeroIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEstadoCivil;
    private JTextField txtSexo;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JSpinner spnFechaNacimiento;

    // Nuevos campos para Grupo Familiar y Educación
    private JTextField txtIdGrupoFamiliar;
    private JTextField txtIdFuncionarioGrupoFamiliar;
    private JTextField txtNombreFamiliar;
    private JTextField txtRolFamiliar;
    private JTextField txtSexoFamiliar;
    private JTextField txtFechaNacimientoFamiliar;

    private JTextField txtIdEducacion;
    private JTextField txtIdFuncionarioEducacion;
    private JTextField txtUniversidad;
    private JTextField txtNivelEstudio;
    private JTextField txtTituloEstudio;

    private DefaultTableModel modeloTabla;
    private boolean editando = false; // Nuevo indicador para el modo de edición
    private String documentoOriginal; // Documento del funcionario que se está editando

    public FuncionarioForm() {
        setTitle("Base de Datos Funcionarios");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        panelFuncionarios = crearPanelFuncionarios();
        panelCrearFuncionario = crearPanelCrearFuncionario();

        tabbedPane.addTab("Funcionarios", panelFuncionarios);
        tabbedPane.addTab("Crear Funcionario", panelCrearFuncionario);

        add(tabbedPane, BorderLayout.CENTER);

        cargarFuncionarios();
    }

    private JPanel crearPanelFuncionarios() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        txtBuscarFuncionario = new JTextField(20);
        panelSuperior.add(new JLabel("Buscar:"));
        panelSuperior.add(txtBuscarFuncionario);

        btnEditarFuncionario = new JButton("Editar");
        btnEliminarFuncionario = new JButton("Eliminar");
        panelSuperior.add(btnEditarFuncionario);
        panelSuperior.add(btnEliminarFuncionario);

        panel.add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"Tipo Identificación", "Número Identificación", "Nombres", "Apellidos", "Sexo", "Estado Civil", "Dirección", "Teléfono", "Fecha de Nacimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaFuncionarios = new JTable(modeloTabla);
        tablaFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tablaFuncionarios);

        panel.add(scrollPane, BorderLayout.CENTER);

        btnEditarFuncionario.addActionListener(e -> editarFuncionario());
        btnEliminarFuncionario.addActionListener(e -> eliminarFuncionario());

        // Agregar un listener para filtrar mientras se escribe
        txtBuscarFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarFuncionarios();
            }
        });

        return panel;
    }

    private JPanel crearPanelCrearFuncionario() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(10, 2, 5, 5));

        // Campos del formulario de Funcionario
        panelFormulario.add(new JLabel("Id Funcionario:"));
        txtIdFuncionario = new JTextField();
        panelFormulario.add(txtIdFuncionario);

        panelFormulario.add(new JLabel("Tipo Identificación:"));
        txtTipoIdentificacion = new JTextField();
        panelFormulario.add(txtTipoIdentificacion);

        panelFormulario.add(new JLabel("Número Identificación:"));
        txtNumeroIdentificacion = new JTextField();
        panelFormulario.add(txtNumeroIdentificacion);

        panelFormulario.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelFormulario.add(txtNombres);

        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos);

        panelFormulario.add(new JLabel("Sexo:"));
        txtSexo = new JTextField();
        panelFormulario.add(txtSexo);

        panelFormulario.add(new JLabel("Estado Civil:"));
        txtEstadoCivil = new JTextField();
        panelFormulario.add(txtEstadoCivil);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Fecha de Nacimiento:"));
        spnFechaNacimiento = new JSpinner(new SpinnerDateModel());
        spnFechaNacimiento.setEditor(new JSpinner.DateEditor(spnFechaNacimiento, "dd/MM/yyyy"));
        panelFormulario.add(spnFechaNacimiento);

        // Formulario Grupo Familiar
        JPanel panelGrupoFamiliar = new JPanel(new GridLayout(6, 2, 5, 5));
        panelGrupoFamiliar.setBorder(BorderFactory.createTitledBorder("Grupo Familiar"));
        panelGrupoFamiliar.add(new JLabel("Id Grupo Familiar:"));
        txtIdGrupoFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtIdGrupoFamiliar);

        panelGrupoFamiliar.add(new JLabel("Id Funcionario:"));
        txtIdFuncionarioGrupoFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtIdFuncionarioGrupoFamiliar);

        panelGrupoFamiliar.add(new JLabel("Nombre Familiar:"));
        txtNombreFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtNombreFamiliar);

        panelGrupoFamiliar.add(new JLabel("Rol:"));
        txtRolFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtRolFamiliar);

        panelGrupoFamiliar.add(new JLabel("Sexo Familiar:"));
        txtSexoFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtSexoFamiliar);

        panelGrupoFamiliar.add(new JLabel("Fecha Nacimiento Familiar:"));
        txtFechaNacimientoFamiliar = new JTextField();
        panelGrupoFamiliar.add(txtFechaNacimientoFamiliar);

        // Formulario de Educación
        JPanel panelEducacion = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEducacion.setBorder(BorderFactory.createTitledBorder("Educación"));
        panelEducacion.add(new JLabel("Id Educación:"));
        txtIdEducacion = new JTextField();
        panelEducacion.add(txtIdEducacion);

        panelEducacion.add(new JLabel("Id Funcionario:"));
        txtIdFuncionarioEducacion = new JTextField();
        panelEducacion.add(txtIdFuncionarioEducacion);

        panelEducacion.add(new JLabel("Universidad:"));
        txtUniversidad = new JTextField();
        panelEducacion.add(txtUniversidad);

        panelEducacion.add(new JLabel("Nivel Estudio:"));
        txtNivelEstudio = new JTextField();
        panelEducacion.add(txtNivelEstudio);

        panelEducacion.add(new JLabel("Título Estudio:"));
        txtTituloEstudio = new JTextField();
        panelEducacion.add(txtTituloEstudio);

        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(panelGrupoFamiliar, BorderLayout.CENTER);
        panel.add(panelEducacion, BorderLayout.SOUTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        panelBotones.add(btnGuardar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarFuncionario());

        return panel;
    }

    private void cargarFuncionarios() {
        modeloTabla.setRowCount(0);
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM funcionarios")) {

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("tipo_identificacion"));
                row.add(rs.getString("numero_identificacion"));
                row.add(rs.getString("nombres"));
                row.add(rs.getString("apellidos"));
                row.add(rs.getString("sexo"));
                row.add(rs.getString("estado_civil"));
                row.add(rs.getString("direccion"));
                row.add(rs.getString("telefono"));
                row.add(rs.getString("fecha_nacimiento"));
                modeloTabla.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filtrarFuncionarios() {
        String searchText = txtBuscarFuncionario.getText().toLowerCase();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            boolean match = false;
            for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                String value = modeloTabla.getValueAt(i, j).toString().toLowerCase();
                if (value.contains(searchText)) {
                    match = true;
                    break;
                }
            }
            tablaFuncionarios.setRowSelectionAllowed(match);
        }
    }

    private void editarFuncionario() {
        int row = tablaFuncionarios.getSelectedRow();
        if (row != -1) {
            String documento = (String) modeloTabla.getValueAt(row, 1);
            try (Connection conn = Conexion.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionarios WHERE numero_identificacion = ?")) {
                stmt.setString(1, documento);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        editando = true;
                        documentoOriginal = rs.getString("numero_identificacion");
                        txtTipoIdentificacion.setText(rs.getString("tipo_identificacion"));
                        txtNumeroIdentificacion.setText(rs.getString("numero_identificacion"));
                        txtNombres.setText(rs.getString("nombres"));
                        txtApellidos.setText(rs.getString("apellidos"));
                        txtSexo.setText(rs.getString("sexo"));
                        txtEstadoCivil.setText(rs.getString("estado_civil"));
                        txtDireccion.setText(rs.getString("direccion"));
                        txtTelefono.setText(rs.getString("telefono"));
                        spnFechaNacimiento.setValue(rs.getDate("fecha_nacimiento"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardarFuncionario() {
        try (Connection conn = Conexion.getConnection()) {
            String sql;
            if (editando) {
                sql = "UPDATE funcionarios SET tipo_identificacion = ?, numero_identificacion = ?, nombres = ?, apellidos = ?, sexo = ?, estado_civil = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE numero_identificacion = ?";
            } else {
                sql = "INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, sexo, estado_civil, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, txtTipoIdentificacion.getText());
                stmt.setString(2, txtNumeroIdentificacion.getText());
                stmt.setString(3, txtNombres.getText());
                stmt.setString(4, txtApellidos.getText());
                stmt.setString(5, txtSexo.getText());
                stmt.setString(6, txtEstadoCivil.getText());
                stmt.setString(7, txtDireccion.getText());
                stmt.setString(8, txtTelefono.getText());
                stmt.setDate(9, new java.sql.Date(((Date) spnFechaNacimiento.getValue()).getTime()));

                if (editando) {
                    stmt.setString(10, documentoOriginal);
                }

                stmt.executeUpdate();
                cargarFuncionarios();
                limpiarCampos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminarFuncionario() {
        int row = tablaFuncionarios.getSelectedRow();
        if (row != -1) {
            String documento = (String) modeloTabla.getValueAt(row, 1);
            try (Connection conn = Conexion.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM funcionarios WHERE numero_identificacion = ?")) {
                stmt.setString(1, documento);
                stmt.executeUpdate();
                cargarFuncionarios();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void limpiarCampos() {
        txtTipoIdentificacion.setText("");
        txtNumeroIdentificacion.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtSexo.setText("");
        txtEstadoCivil.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        spnFechaNacimiento.setValue(new Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FuncionarioForm().setVisible(true));
    }
}