package dao;

import modelo.GrupoFamiliar;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoFamiliarDAO {
    private Connection connection;

    public GrupoFamiliarDAO() {
        connection = Conexion.getConnection();
    }

    // Guardar grupo familiar
    public void guardarGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws SQLException {
        String query = "INSERT INTO grupo_familiar (id_funcionario, nombres, apellidos, rol, sexo, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, grupoFamiliar.getIdFuncionario());
            pst.setString(2, grupoFamiliar.getNombres());
            pst.setString(3, grupoFamiliar.getApellidos());
            pst.setString(4, grupoFamiliar.getRol());
            pst.setString(5, grupoFamiliar.getSexo());
            pst.setDate(6, new java.sql.Date(grupoFamiliar.getFechaNacimiento().getTime()));
            pst.executeUpdate();
        }
    }

    // Obtener todos los grupos familiares de un funcionario
    public List<GrupoFamiliar> obtenerGrupoFamiliar(int idFuncionario) throws SQLException {
        List<GrupoFamiliar> listaGrupoFamiliar = new ArrayList<>();
        String query = "SELECT * FROM grupo_familiar WHERE id_funcionario = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, idFuncionario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdGrupoFamiliar(rs.getInt("id_grupo_familiar"));
                grupoFamiliar.setIdFuncionario(rs.getInt("id_funcionario"));
                grupoFamiliar.setNombres(rs.getString("nombres"));
                grupoFamiliar.setApellidos(rs.getString("apellidos"));
                grupoFamiliar.setRol(rs.getString("rol"));
                grupoFamiliar.setSexo(rs.getString("sexo"));
                grupoFamiliar.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                listaGrupoFamiliar.add(grupoFamiliar);
            }
        }
        return listaGrupoFamiliar;
    }

    // Eliminar grupo familiar
    public void eliminarGrupoFamiliar(int idGrupoFamiliar) throws SQLException {
        String query = "DELETE FROM grupo_familiar WHERE id_grupo_familiar = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, idGrupoFamiliar);
            pst.executeUpdate();
        }
    }
}