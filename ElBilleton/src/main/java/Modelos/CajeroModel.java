package Modelos;

import Objetos.Cajero;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jeffmenca
 */
public class CajeroModel {

    private final String CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME;
    private final String BUSCAR_CAJERO = CAJERO + " WHERE " + Cajero.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = CAJERO + " WHERE " + Cajero.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_CAJERO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.NOMBRE_DB_NAME + ","
            + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + ","
            + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();
    

    /**
     * Realizamos una busqueda en base al id del usuario. De no existir la nota
     * nos devuelve un valor null.
     *
     * @param codigoCajero
     * @return
     * @throws SQLException
     */
    public Cajero obtenerCajero(int codigoCajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJERO);
        preSt.setInt(1, codigoCajero);
        ResultSet result = preSt.executeQuery();
        Cajero cajero = null;
        while (result.next()) {
            cajero = new Cajero(
                    result.getInt(Cajero.CODIGO_DB_NAME),
                    result.getString(Cajero.NOMBRE_DB_NAME),
                    result.getString(Cajero.TURNO_DB_NAME),
                    result.getInt(Cajero.DPI_DB_NAME),
                    result.getString(Cajero.DIRECCION_DB_NAME),
                    result.getString(Cajero.SEXO_DB_NAME),
                    result.getString(Cajero.PASSWORD_DB_NAME)
            );
        }
        return cajero;
    }
    
    /**
     * Verifica que las credenciales del gerente sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cajero loginValidation(int codigo, String password) throws SQLException {
        Cajero cajero = obtenerCajero(codigo);
        if (cajero != null && cajero.getPassword().equals(password)) {
            return cajero;
        }
        return null;
    }
}
