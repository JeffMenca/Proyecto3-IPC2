package Modelos;

import Objetos.Transaccion;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffmenca
 */
public class TransaccionModel {

    private final String TRANSACCION = "SELECT * FROM " + Transaccion.TRANSACCION_DB_NAME;
    private final String BUSCAR_TRANSACCION = TRANSACCION + " WHERE " + Transaccion.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_CAJERO = TRANSACCION + " WHERE " + Transaccion.CAJERO_CODIGO_DB_NAME + " LIKE ?";
    private final String CREAR_TRANSACCION = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + ","
            + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + ","
            + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_TRANSACCION_MANUALMENTE = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.CODIGO_DB_NAME + "," + Transaccion.FECHA_DB_NAME + ","
            + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + ","
            + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos un nuevo Transaccion al completar la insercion devuelve el
     * codigo autogenerado del cajero. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregarTransaccion(Transaccion transaccion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_TRANSACCION, Statement.RETURN_GENERATED_KEYS);

        preSt.setDate(1, transaccion.getFecha());
        preSt.setTime(2, transaccion.getHora());
        preSt.setString(3, transaccion.getTipo());
        preSt.setDouble(4, transaccion.getMonto());
        preSt.setLong(5, transaccion.getCuenta_codigo());
        preSt.setLong(6, transaccion.getCajero_codigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * Agregamos un nuevo Transaccion al completar la insercion devuelve el
     * codigo del cajero. De no existir nos devolvera <code>-1</code>.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregarTransaccionManualmente(Transaccion transaccion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_TRANSACCION_MANUALMENTE, Statement.RETURN_GENERATED_KEYS);
        preSt.setLong(1, transaccion.getCodigo());
        preSt.setDate(2, transaccion.getFecha());
        preSt.setTime(3, transaccion.getHora());
        preSt.setString(4, transaccion.getTipo());
        preSt.setDouble(5, transaccion.getMonto());
        preSt.setLong(6, transaccion.getCuenta_codigo());
        preSt.setLong(7, transaccion.getCajero_codigo());
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
     * @param codigoTransaccion
     * @return
     * @throws SQLException
     */
    public Transaccion obtenerTransaccion(int codigoTransaccion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_TRANSACCION);
        preSt.setInt(1, codigoTransaccion);
        ResultSet result = preSt.executeQuery();
        Transaccion cajero = null;
        while (result.next()) {
            cajero = new Transaccion(
                    result.getLong(Transaccion.CODIGO_DB_NAME),
                    result.getDate(Transaccion.FECHA_DB_NAME),
                    result.getTime(Transaccion.HORA_DB_NAME),
                    result.getString(Transaccion.TIPO_DB_NAME),
                    result.getDouble(Transaccion.MONTO_DB_NAME),
                    result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                    result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
            );
        }
        return cajero;
    }

}
