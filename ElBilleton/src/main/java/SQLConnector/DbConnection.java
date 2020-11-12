/*
 * Paquetes
 */
package SQLConnector;

/*
 * Librerias
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/*
 * @author jeffmenca
 */

/*
 * Clase principal
 */
public class DbConnection {
    
    //Variables
    private static Connection conexion = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "Jeffjma100";
    private static final String url = "jdbc:mysql://localhost/ELBILLETON?useSSL=false";
    private static final int MYSQL_DUPLICATE_PK = 1062;
    
    /*
     * Conexion a la base datos
     */
    public static void connectionDB() {
        conexion = null;
        try {
            //Llama al jdbc
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos " + e);
        }
    }
    
    /*
     * Metodo para llamar a la conexion
     */
    public static Connection getConnection() {
         if (conexion == null) {
            connectionDB();
        }
        return conexion;
    }

    /*
     * Metodo encargado de salir de la base de datos
     */
    public void disconnectDB() {
        conexion = null;
    }
}
