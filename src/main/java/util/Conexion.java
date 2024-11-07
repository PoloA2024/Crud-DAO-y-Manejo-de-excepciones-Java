package util;

import java.sql.*;

public class Conexion {
    // Definir los parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/recursos_humanos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() {
        try {
            // Intentar establecer la conexión
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return connection;
        } catch (SQLException ex) {
            // Capturar errores en la conexión
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
            return null;
        }
    }

    // Método para verificar la conexión (test)
    public static boolean verificarConexion() {
        try (Connection conn = getConnection()) {
            // Verificar si la conexión es exitosa
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                return true;
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
                return false;
            }
        } catch (SQLException ex) {
            // Mostrar error si no se puede conectar
            System.out.println("Error al verificar la conexión: " + ex.getMessage());
            return false;
        }
    }

    // Método para cerrar la conexión (opcional)
    public static void cerrarConexion(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}