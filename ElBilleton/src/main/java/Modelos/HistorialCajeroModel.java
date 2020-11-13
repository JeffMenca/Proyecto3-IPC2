package Modelos;

import Objetos.HistorialCajero;
import Objetos.Cajero;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jeffmenca
 */
public class HistorialCajeroModel {

    private final String HISTORIAL_CAJERO = "SELECT * FROM " + HistorialCajero.HISTORIAL_CAJERO_DB_NAME;
    private final String BUSCAR_HISTORIAL_CAJERO = HISTORIAL_CAJERO + " WHERE " + HistorialCajero.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = HISTORIAL_CAJERO + " WHERE " + HistorialCajero.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_HISTORIAL_CAJERO = "INSERT INTO " + HistorialCajero.HISTORIAL_CAJERO_DB_NAME + " (" + HistorialCajero.NOMBRE_DB_NAME + ","
            + HistorialCajero.TURNO_DB_NAME + "," + HistorialCajero.DPI_DB_NAME + "," + HistorialCajero.DIRECCION_DB_NAME + "," + HistorialCajero.SEXO_DB_NAME + ","
            + HistorialCajero.PASSWORD_DB_NAME + "," + HistorialCajero.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos un nuevo HistorialCajero al completar la insercion devuelve el
     * codigo autogenerado del cajero. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param cajero
     * @return
     * @throws SQLException
     */
    public long agregarHistorialCajero(Cajero cajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_CAJERO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDPI());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setString(6, cajero.getPassword());
        preSt.setLong(6, cajero.getCodigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir la
     * nos devuelve un valor null.
     *
     * @param codigoHistorialCajero
     * @return
     * @throws SQLException
     */
    public HistorialCajero obtenerHistorialCajero(int codigoHistorialCajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_HISTORIAL_CAJERO);
        preSt.setInt(1, codigoHistorialCajero);
        ResultSet result = preSt.executeQuery();
        HistorialCajero cajero = null;
        while (result.next()) {
            cajero = new HistorialCajero(
                    result.getInt(HistorialCajero.CODIGO_DB_NAME),
                    result.getString(HistorialCajero.NOMBRE_DB_NAME),
                    result.getString(HistorialCajero.TURNO_DB_NAME),
                    result.getString(HistorialCajero.DPI_DB_NAME),
                    result.getString(HistorialCajero.DIRECCION_DB_NAME),
                    result.getString(HistorialCajero.SEXO_DB_NAME),
                    result.getString(HistorialCajero.PASSWORD_DB_NAME),
                    result.getInt(HistorialCajero.CAJERO_CODIGO_DB_NAME)
            );
        }
        return cajero;
    }

    
}
