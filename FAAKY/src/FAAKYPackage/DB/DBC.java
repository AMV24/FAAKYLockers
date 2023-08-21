package FAAKYPackage.DB;
import java.sql.*;

public class DBC {
    // Datos de conexión a la base de datos
    private String host = "jdbc:mysql://localhost:3306/faakycorpv2"; // URL de la base de datos
    private String user = "root"; // Usuario de la base de datos
    private String pass = ""; // Contraseña de la base de datos

    // Creación de la conexión
    private Connection dbFAAKY = null; // Objeto Connection que representa la conexión a la base de datos

    // Constructor de la clase DBC
    public DBC() {
        try {
            conectarDB(); // Intenta conectar con la base de datos al crear un objeto DBC
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e); // Si ocurre un error al conectar, muestra un mensaje
        }
    }

    // Método privado para establecer la conexión con la base de datos
    private void conectarDB() throws SQLException {
        dbFAAKY = DriverManager.getConnection(host, user, pass); // Establece la conexión con la base de datos usando los datos de conexión
    }

    // Método público para obtener la conexión a la base de datos
    public Connection getConexion() {
        return dbFAAKY; // Retorna el objeto Connection que representa la conexión a la base de datos
    }

    // Método público para cerrar la conexión a la base de datos
    public void cerrarConexion() throws SQLException {
        if (dbFAAKY != null) {
            dbFAAKY.close(); // Cierra la conexión si existe una conexión abierta
        }
    }
}
