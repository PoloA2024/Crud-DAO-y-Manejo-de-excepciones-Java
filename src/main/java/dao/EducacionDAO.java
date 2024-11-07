package dao;

import modelo.Educacion;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducacionDAO {
    private Connection connection;

    public EducacionDAO() {
        connection = Conexion.getConnection();
    }

    // Guardar información educativa
    public void guardarEducacion(Educacion educacion) throws SQLException {
        String query = "INSERT INTO educacion (id_funcionario, universidad, nivel_estudio, titulo_estudio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, educacion.getIdFuncionario());
            pst.setString(2, educacion.getUniversidad());
            pst.setString(3, educacion.getNivelEstudio());
            pst.setString(4, educacion.getTituloEstudio());
            pst.executeUpdate();
        }
    }

    // Obtener toda la educación de un funcionario
    public List<Educacion> obtenerEducacionPorFuncionario(int idFuncionario) throws SQLException {
        List<Educacion> listaEducacion = new ArrayList<>();
        String query = "SELECT * FROM educacion WHERE id_funcionario = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, idFuncionario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Educacion educacion = new Educacion();
                educacion.setIdEducacion(rs.getInt("id_educacion"));
                educacion.setIdFuncionario(rs.getInt("id_funcionario"));
                educacion.setUniversidad(rs.getString("universidad"));
                educacion.setNivelEstudio(rs.getString("nivel_estudio"));
                educacion.setTituloEstudio(rs.getString("titulo_estudio"));
                listaEducacion.add(educacion);
            }
        }
        return listaEducacion;
    }

    // Eliminar información educativa
    public void eliminarEducacion(int idEducacion) throws SQLException {
        String query = "DELETE FROM educacion WHERE id_educacion = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, idEducacion);
            pst.executeUpdate();
        }
    }
}