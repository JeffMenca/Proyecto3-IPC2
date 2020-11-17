package Modelos;

import Objetos.Cliente;
import Objetos.Cuenta;
import Objetos.Transaccion;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private final String REPORTE_2 = "SELECT T.* FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON T.cuenta_codigo=CU.codigo INNER JOIN "
            + Cliente.CLIENTE_DB_NAME + " C ON C.codigo=CU.cliente_codigo WHERE C.codigo=? && T.monto>?";
    private final String OBTENER_TRANSACCIONES_CUENTA = "SELECT * FROM " + Transaccion.TRANSACCION_DB_NAME + " WHERE " + Transaccion.CUENTA_CODIGO_DB_NAME + "=?";
    private final String REPORTE1 = "SELECT * FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN "
            + Cuenta.CUENTA_DB_NAME + " C ON C.codigo=T.cuenta_codigo WHERE C.codigo=? && T.fecha >= date_sub(CURDATE(), INTERVAL 1 YEAR) ORDER BY T.monto DESC LIMIT 15  ";
    private final String REPORTE3_CLIENTE = "SELECT * FROM " + Transaccion.TRANSACCION_DB_NAME + " WHERE "+Transaccion.CUENTA_CODIGO_DB_NAME+"=? && "+Transaccion.FECHA_DB_NAME+" BETWEEN ? AND ?";
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
        try {
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
        } catch (Exception e) {
            return -1;
        }

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
        try {
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
        } catch (Exception e) {
            return -1;
        }

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

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir la
     * nos devuelve un valor null.
     *
     * @param limiteReporte2
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerTransaccionLimite(Double limiteReporte2, Long codigoCliente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_2);
            preSt.setLong(1, codigoCliente);
            preSt.setDouble(2, limiteReporte2);
            ArrayList transacciones = new ArrayList();
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
                transacciones.add(cajero);
            }
            return transacciones;
        } catch (Exception e) {

            return null;
        }

    }

    /**
     * Obtenemos las transacciones de una cuenta en especifico
     *
     * @param cuenta_codigo return
     */
    public ArrayList<Transaccion> obtenerTransaccionesCuenta(long cuenta_codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_TRANSACCIONES_CUENTA);
            preSt.setLong(1, cuenta_codigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Obtenemos las transacciones del reporte 1
     *
     * @param cuentaCodigo return
     */
    public ArrayList<Transaccion> obtenerReporte1Cliente(long cuentaCodigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE1);
            preSt.setLong(1, cuentaCodigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            return null;
        }
    }
    
    /**
     * Obtenemos las transacciones del reporte 1
     *
     * @param cuentaCodigo return
     */
    public ArrayList<Transaccion> obtenerReporte3Cliente(long cuentaCodigo,Date fecha1, Date fecha2) {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE3_CLIENTE);
            preSt.setLong(1, cuentaCodigo);
            preSt.setDate(2, fecha1);
            preSt.setDate(3, fecha2);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            return null;
        }
    }
}
