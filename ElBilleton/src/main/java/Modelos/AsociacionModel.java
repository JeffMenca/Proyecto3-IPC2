package Modelos;

import Objetos.AsociacionCuentas;
import Objetos.SolicitudAsociacion;
import SQLConnector.DbConnection;
import java.sql.Connection;
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
public class AsociacionModel {

    private final String ASOCIACION = "SELECT * FROM " + AsociacionCuentas.ASOCIACION_DB_NAME;
    private final String CREAR_ASOCIACION = "INSERT INTO " + AsociacionCuentas.ASOCIACION_DB_NAME + " (" + AsociacionCuentas.FECHA_DB_NAME + ","
            + AsociacionCuentas.SOLICITUD_CODIGO_DB_NAME + ") VALUES (?,?)";
    private final String COMPROBAR_ASOCIACION = "SELECT S.codigo FROM ASOCIACION_CUENTAS A INNER JOIN SOLICITUD_ASOCIACION S ON S.codigo=A.solicitud_asociacion_codigo WHERE S.cuenta_envia_codigo=? && S.cuenta2_recibe_codigo1=?";
    private final String ASOCIACIONES_CLIENTE_ENVIA = "SELECT * FROM ASOCIACION_CUENTAS A INNER JOIN SOLICITUD_ASOCIACION S ON S.codigo=A.solicitud_asociacion_codigo WHERE (S.cuenta_envia_codigo=?)";
    private final String ASOCIACIONES_CLIENTE_RECIBE = "SELECT * FROM ASOCIACION_CUENTAS A INNER JOIN SOLICITUD_ASOCIACION S ON S.codigo=A.solicitud_asociacion_codigo WHERE (S.cuenta2_recibe_codigo1=?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos una nueva asociacion y al completar la insercion devuelve el
     * codigo autogenerado de la asociacioon. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param asociacion
     * @return
     * @throws SQLException
     */
    public long crearAsociacion(AsociacionCuentas asociacion) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_ASOCIACION, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, asociacion.getFecha());
            preSt.setInt(2, asociacion.getSolicitud_codigo());
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
     * Comprobamos si la asociacion existe. De no existir la nos devuelve un
     * valor false.
     *
     * @param codigoEnvia
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public Boolean comprobarAsociacion(Long codigoEnvia, Long codigoRecibe) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(COMPROBAR_ASOCIACION);
        preSt.setLong(1, codigoEnvia);
        preSt.setLong(2, codigoRecibe);
        int codigoEncontrado = 0;
        ResultSet result = preSt.executeQuery();
        SolicitudAsociacion solicitud = null;
        while (result.next()) {

            return true;
        }
        return false;
    }

    /**
     * Realizamos una busqueda en base a el codigo de las solicitudes. De no
     * existir la nos devuelve un valor null.
     *
     * @param codigoCuentaEnvia
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerAsociacionesEnvia(Long codigoCuentaEnvia) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ASOCIACIONES_CLIENTE_ENVIA);
        preSt.setLong(1, codigoCuentaEnvia);
        ResultSet result = preSt.executeQuery();
        ArrayList asociacionesEnvia = new ArrayList();
        while (result.next()) {

            asociacionesEnvia.add(result.getLong(SolicitudAsociacion.CUENTA_RECIBE_CODIGO_DB_NAME));
        }
        return asociacionesEnvia;
    }

    /**
     * Realizamos una busqueda en base a el codigo de las solicitudes. De no
     * existir la nos devuelve un valor null.
     *
     * @param codigoCuentaRecibe
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerAsociacionesRecibe(Long codigoCuentaRecibe) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ASOCIACIONES_CLIENTE_RECIBE);
        preSt.setLong(1, codigoCuentaRecibe);
        ResultSet result = preSt.executeQuery();
        ArrayList asociacionesRecibe = new ArrayList();
        AsociacionCuentas solicitud = null;
        while (result.next()) {
            asociacionesRecibe.add(result.getLong(SolicitudAsociacion.CUENTA_ENVIA_CODIGO_DB_NAME));
        }
        return asociacionesRecibe;
    }

}
