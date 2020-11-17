package Modelos;

import Objetos.AsociacionCuentas;
import Objetos.Cuenta;
import Objetos.SolicitudAsociacion;
import Objetos.Transaccion;
import SQLConnector.DbConnection;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author jeffmenca
 */
public class CuentaModel {

    private final String CUENTA = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME;
    private final String BUSCAR_CUENTA = CUENTA + " WHERE " + Cuenta.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_CUENTA_SIN_CLIENTE_DUENIO = CUENTA + " WHERE " + Cuenta.CODIGO_DB_NAME + " = ? && " + Cuenta.CLIENTE_CODIGO_DB_NAME + " !=? LIMIT 1";
    private final String BUSCAR_POR_CLIENTE = CUENTA + " WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + " = ?";
    private final String CREAR_CUENTA = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.FECHA_CREACION_DB_NAME + ","
            + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?)";
    private final String CREAR_CUENTA_MANUALMENTE = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.CODIGO_DB_NAME + ","
            + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    public static final String BUSCAR_CUENTA2 = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE codigo=? && cliente_codigo=?";
    public static final String BUSCAR_CUENTAS_ASOCIADAS = "SELECT C.* FROM " + Cuenta.CUENTA_DB_NAME + " C INNER JOIN "
            + SolicitudAsociacion.SOLICITUD_DB_NAME + " S ON (S.cuenta_envia_codigo=C.codigo||S.cuenta2_recibe_codigo1=C.codigo) INNER JOIN "
            + AsociacionCuentas.ASOCIACION_DB_NAME + " A ON A.solicitud_asociacion_codigo=S.codigo WHERE S.estado='Aceptada' && C.codigo!=? && C.cliente_codigo!=? GROUP BY C.codigo";
    public static final String BUSCAR_CUENTAS_CLIENTE = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE cliente_codigo=? && codigo!=?";
    private final String EDITAR_MONTO = "UPDATE " + Cuenta.CUENTA_DB_NAME + " SET " + Cuenta.MONTO_DB_NAME + "=? WHERE " + Cuenta.CODIGO_DB_NAME + "=?";
    private final String REPORTE3_CLIENTE="SELECT C.* FROM CUENTA C WHERE C.cliente_codigo=? ORDER BY C.monto DESC LIMIT 1";
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
            TransaccionModel transaccionModel = new TransaccionModel();
            Date fecha = Date.valueOf(LocalDate.now());
            Time hora = Time.valueOf(LocalTime.now());
            Transaccion transaccion = new Transaccion(0, fecha, hora, "Credito", cuenta.getMonto(), cuenta.getCodigo(), 101);
            transaccionModel.agregarTransaccion(transaccion);
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
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public Cuenta obtenerCuenta(Long codigoCuenta, Long codigoCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTA_SIN_CLIENTE_DUENIO);
        preSt.setLong(1, codigoCuenta);
        preSt.setLong(2, codigoCliente);
        ResultSet result = preSt.executeQuery();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
        }
        return cuenta;
    }

    /**
     * Realizamos una busqueda en base al codigo de la cuenta. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoCuenta
     *
     * @return
     * @throws SQLException
     */
    public Cuenta obtenerCuenta2(Long codigoCuenta) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTA);
        preSt.setLong(1, codigoCuenta);
        ResultSet result = preSt.executeQuery();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
        }
        return cuenta;
    }

    /**
     * Realizamos una busqueda en base al duenio de las cuentas. De no existir
     * nos devuelve un valor null.
     *
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerCuentasCliente(Long codigoCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_POR_CLIENTE);
        preSt.setLong(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        ArrayList cuentas = new ArrayList();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            cuentas.add(cuenta);
        }
        return cuentas;
    }

    /**
     * Realizamos una busqueda en base al duenio de las cuentas y un filtro. De
     * no existir nos devuelve un valor null.
     *
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerCuentasClienteLike(Long codigoCliente, String filtro) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_POR_CLIENTE + " && " + Cuenta.CODIGO_DB_NAME + " LIKE '%" + filtro + "%' ");
        preSt.setLong(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        ArrayList cuentas = new ArrayList();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            cuentas.add(cuenta);
        }
        return cuentas;
    }

    public Cuenta buscarCuenta2(Long cuenta1, Long cliente) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTA2);

            preSt.setLong(1, cuenta1);
            preSt.setLong(2, cliente);
            ResultSet result = preSt.executeQuery();
            Cuenta cuenta = null;

            while (result.next()) {
                cuenta = new Cuenta(
                        result.getLong(Cuenta.CODIGO_DB_NAME),
                        result.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                        result.getDouble(Cuenta.MONTO_DB_NAME),
                        result.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                return cuenta;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public ArrayList obtenerCuentasAsociadas(Long codigoCuenta, Long codigoCliente) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTAS_ASOCIADAS);
        preSt.setLong(1, codigoCuenta);
        preSt.setLong(2, codigoCliente);
        ResultSet result = preSt.executeQuery();
        ArrayList listacuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(Cuenta.CODIGO_DB_NAME),
                    result.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(Cuenta.MONTO_DB_NAME),
                    result.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listacuentas.add(cuenta);
        }
        return listacuentas;
    }

    public ArrayList obtenerCuentasClientesin(Long clienteCodigo, Long cuentaCodigo) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTAS_CLIENTE);
        preSt.setLong(1, clienteCodigo);
        preSt.setLong(2, +cuentaCodigo);
        ResultSet result = preSt.executeQuery();
        ArrayList listacuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(Cuenta.CODIGO_DB_NAME),
                    result.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(Cuenta.MONTO_DB_NAME),
                    result.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listacuentas.add(cuenta);
        }
        return listacuentas;
    }

    public long modificarMonto(Cuenta cuenta) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_MONTO, Statement.RETURN_GENERATED_KEYS);

            preSt.setDouble(1, cuenta.getMonto());
            preSt.setLong(2, cuenta.getCodigo());
            preSt.executeUpdate();

        } catch (SQLException e) {
        }

        return -1;
    }

    /**
     * Realizamos una busqueda en base al codigo de la cuenta. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoCuenta
     *
     * @return
     * @throws SQLException
     */
    public Cuenta Reporte3Cliente(Long codigoCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REPORTE3_CLIENTE);
        preSt.setLong(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        Cuenta cuenta = null;
        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getInt(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
        }
        return cuenta;
    }
}
