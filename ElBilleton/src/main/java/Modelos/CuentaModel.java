package Modelos;

import Objetos.Cuenta;
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
public class CuentaModel {

    private final String CUENTA = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME;
    private final String BUSCAR_CUENTA = CUENTA + " WHERE " + Cuenta.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_CLIENTE = CUENTA + " WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + " LIKE ?";
    private final String CREAR_CUENTA = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.FECHA_CREACION_DB_NAME + ","
            + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?)";
    private final String CREAR_CUENTA_MANUALMENTE = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.CODIGO_DB_NAME + ","
            + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos una nueva cuenta. Al completar la insercion devuelve el codigo
     * autogenerado de la cuenta. De no existir nos devolvera <code>-1</code>.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuenta(Cuenta cuenta) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, cuenta.getFecha_creacion());
            preSt.setDouble(2, cuenta.getMonto());
            preSt.setLong(3, cuenta.getCliente_codigo());

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
     * Agregamos una nueva cuenta con codigo manual. Al completar la insercion
     * devuelve el codigo y de no existir nos devolvera <code>-1</code>.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuentaManualmente(Cuenta cuenta, Long codigoCliente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA_MANUALMENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, cuenta.getCodigo());
            preSt.setDate(2, cuenta.getFecha_creacion());
            preSt.setDouble(3, cuenta.getMonto());
            preSt.setLong(4, codigoCliente);

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
     * Realizamos una busqueda en base al codigo de la cuenta. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoCuenta
     * @return
     * @throws SQLException
     */
    public Cuenta obtenerCuenta(Long codigoCuenta) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTA);
        preSt.setLong(1, codigoCuenta);
        ResultSet result = preSt.executeQuery();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getInt(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
        }
        return cuenta;
    }

}
