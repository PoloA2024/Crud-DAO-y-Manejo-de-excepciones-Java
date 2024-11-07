package dao;

import modelo.Funcionario;
import util.Conexion;
import java.sql.*;

public class FuncionarioDAO {
    private Connection connection;

    public FuncionarioDAO() {
        connection = Conexion.getConnection();
    }

    public void guardarFuncionario(Funcionario funcionario) throws SQLException {
        String query = "INSERT INTO funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, funcionario.getTipoIdentificacion());
            pst.setString(2, funcionario.getNumeroIdentificacion());
            pst.setString(3, funcionario.getNombres());
            pst.setString(4, funcionario.getApellidos());
            pst.setString(5, funcionario.getEstadoCivil());
            pst.setString(6, funcionario.getSexo());
            pst.setString(7, funcionario.getDireccion());
            pst.setString(8, funcionario.getTelefono());
            pst.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            pst.executeUpdate();
        }
    }

    // Método para editar un funcionario existente
    public void editarFuncionario(Funcionario funcionario) throws SQLException {
        String query = "UPDATE funcionario SET tipo_identificacion = ?, numero_identificacion = ?, nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, funcionario.getTipoIdentificacion());
            pst.setString(2, funcionario.getNumeroIdentificacion());
            pst.setString(3, funcionario.getNombres());
            pst.setString(4, funcionario.getApellidos());
            pst.setString(5, funcionario.getEstadoCivil());
            pst.setString(6, funcionario.getSexo());
            pst.setString(7, funcionario.getDireccion());
            pst.setString(8, funcionario.getTelefono());
            pst.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            pst.setInt(10, funcionario.getIdFuncionario());  // Cambié a getIdFuncionario()
            pst.executeUpdate();
        }
    }

    // Método para obtener un funcionario por su ID
    public Funcionario obtenerFuncionarioPorId(int id) throws SQLException {
        String query = "SELECT * FROM funcionario WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id"));  // Cambié a setIdFuncionario()
                funcionario.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                funcionario.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                funcionario.setNombres(rs.getString("nombres"));
                funcionario.setApellidos(rs.getString("apellidos"));
                funcionario.setEstadoCivil(rs.getString("estado_civil"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setDireccion(rs.getString("direccion"));
                funcionario.setTelefono(rs.getString("telefono"));
                funcionario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                return funcionario;
            }
            return null;  // Retorna null si no se encuentra el funcionario
        }
    }

    // Método para eliminar un funcionario por su ID
    public void eliminarFuncionario(int id) throws SQLException {
        String query = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}
