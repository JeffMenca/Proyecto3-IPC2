package Modelos;

import Objetos.Cajero;
import Objetos.SolicitudAsociacion;
import Objetos.Transaccion;
import SQLConnector.DbConnection;
import SQLConnector.Encriptar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffmenca
 */
public class SolicitudModel {

    private final String SOLICITUD = "SELECT * FROM " + SolicitudAsociacion.SOLICITUD_DB_NAME;
    private final String BUSCAR_SOLICITUD = SOLICITUD + " WHERE " + SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME + " = ? &&  " + SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_SOLICITUD_VECES = "SELECT COUNT(*) AS VECES FROM " + SolicitudAsociacion.SOLICITUD_DB_NAME+ " WHERE " + SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME + " = ? &&  " + SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME + " = ?";
    private final String CREAR_SOLICITUD = "INSERT INTO " + SolicitudAsociacion.SOLICITUD_DB_NAME + " (" + SolicitudAsociacion.FECHA_DB_NAME + ","
            + SolicitudAsociacion.ESTADO_DB_NAME + "," + SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME + ","
            + SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    private final String EDITAR_SOLICITUD = "UPDATE " + SolicitudAsociacion.SOLICITUD_DB_NAME + " SET " + SolicitudAsociacion.FECHA_DB_NAME + "=?,"
            + SolicitudAsociacion.ESTADO_DB_NAME + "=?," + SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME + "=?,"
            + SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME + "=? WHERE codigo=?";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos unanueva solicitud al completar la insercion devuelve el codigo
     * autogenerado de la solicitud. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param solicitud
     * @return
     * @throws SQLException
     */
    public long crearSolicitud(SolicitudAsociacion solicitud) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuenta_envia_codigo());
            preSt.setLong(4, solicitud.getCuenta2_recibe_codigo1());
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
     * @param codigoEnvia
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public SolicitudAsociacion obtenerSolicitud(Long codigoEnvia, Long codigoRecibe) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_SOLICITUD);
        preSt.setLong(1, codigoRecibe);
        preSt.setLong(2, codigoEnvia);
        ResultSet result = preSt.executeQuery();
        SolicitudAsociacion solicitud = null;
        while (result.next()) {
            solicitud = new SolicitudAsociacion(
                    result.getLong(SolicitudAsociacion.CODIGO_DB_NAME),
                    result.getDate(SolicitudAsociacion.FECHA_DB_NAME),
                    result.getString(SolicitudAsociacion.ESTADO_DB_NAME),
                    result.getLong(SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
        }
        return solicitud;
    }

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir la
     * nos devuelve un valor null.
     *
     * @param codigoEnvia
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public int comprobarSolicitud(Long codigoEnvia, Long codigoRecibe) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_SOLICITUD_VECES);
        preSt.setLong(1, codigoEnvia);
        preSt.setLong(2, codigoRecibe);
        ResultSet result = preSt.executeQuery();
        int veces=0;
        while (result.next()) {
            veces=result.getInt("VECES");
        }
        return veces;
    }

    /**
     * Realizamos una busqueda en de la cantidad solicitudes. De no existir nos devuelve
     * un valor null.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerSolicitudes() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(SOLICITUD);
        ResultSet result = preSt.executeQuery();
        ArrayList solicitudes = new ArrayList();
        SolicitudAsociacion solicitud = null;
        while (result.next()) {
            solicitud = new SolicitudAsociacion(
                    result.getLong(SolicitudAsociacion.CODIGO_DB_NAME),
                    result.getDate(SolicitudAsociacion.FECHA_DB_NAME),
                    result.getString(SolicitudAsociacion.ESTADO_DB_NAME),
                    result.getLong(SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    /**
     * Editamos el cajero por medio de un codigo
     *
     * @param solicitud
     * @param codigoSolicitud
     * @throws SQLException
     */
    public void actualizarCajero(SolicitudAsociacion solicitud, Long codigoSolicitud) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuenta_envia_codigo());
            preSt.setLong(4, solicitud.getCuenta2_recibe_codigo1());
            preSt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
