package Modelos;

import Objetos.Cajero;
import Objetos.Cliente;
import SQLConnector.DbConnection;
import SQLConnector.Encriptar;
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
public class CajeroModel {

    private final String CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME;
    private final String BUSCAR_CAJERO = CAJERO + " WHERE " + Cajero.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_CAJEROS = "SELECT * FROM CAJERO";
    private final String BUSCAR_POR_NOMBRE = CAJERO + " WHERE " + Cajero.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_CAJERO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.NOMBRE_DB_NAME + ","
            + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + ","
            + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_CAJERO_MANUALMENTE = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.CODIGO_DB_NAME + "," + Cajero.NOMBRE_DB_NAME + ","
            + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + ","
            + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String EDITAR_CAJERO = "UPDATE " + Cajero.CAJERO_DB_NAME + " SET " + Cajero.NOMBRE_DB_NAME + "=?,"
            + Cajero.TURNO_DB_NAME + "=?," + Cajero.DPI_DB_NAME + "=?," + Cajero.DIRECCION_DB_NAME + "=?," + Cajero.SEXO_DB_NAME + "=?,"
            + Cajero.PASSWORD_DB_NAME + "=? WHERE codigo=?";
    private static Connection connection = DbConnection.getConnection();
    HistorialCajeroModel historialCajero = new HistorialCajeroModel();

    /**
     * Agregamos un nuevo Cajero al completar la insercion devuelve el codigo
     * autogenerado del cajero. De no existir nos devolvera <code>-1</code>.
     *
     * @param cajero
     * @return
     * @throws SQLException
     */
    public long agregarCajero(Cajero cajero) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CAJERO, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getTurno());
            preSt.setString(3, cajero.getDPI());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(cajero.getPassword()));
            } catch (Exception e) {
            }

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
     * Agregamos un nuevo Cajero al completar la insercion devuelve el codigo
     * del cajero. De no existir nos devolvera <code>-1</code>.
     *
     * @param cajero
     * @return
     * @throws SQLException
     */
    public long agregarCajeroManualmente(Cajero cajero) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CAJERO_MANUALMENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, cajero.getCodigo());
            preSt.setString(2, cajero.getNombre());
            preSt.setString(3, cajero.getTurno());
            preSt.setString(4, cajero.getDPI());
            preSt.setString(5, cajero.getDireccion());
            preSt.setString(6, cajero.getSexo());
            try {
                preSt.setString(7, Encriptar.encriptar(cajero.getPassword()));
            } catch (Exception e) {
            }

            preSt.executeUpdate();
            historialCajero.agregarHistorialCajero(cajero);
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
     * @param codigoCajero
     * @return
     * @throws SQLException
     */
    public Cajero obtenerCajero(Long codigoCajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJERO);
        preSt.setLong(1, codigoCajero);
        ResultSet result = preSt.executeQuery();
        Cajero cajero = null;
        while (result.next()) {
            cajero = new Cajero(
                    result.getLong(Cajero.CODIGO_DB_NAME),
                    result.getString(Cajero.NOMBRE_DB_NAME),
                    result.getString(Cajero.TURNO_DB_NAME),
                    result.getString(Cajero.DPI_DB_NAME),
                    result.getString(Cajero.DIRECCION_DB_NAME),
                    result.getString(Cajero.SEXO_DB_NAME),
                    result.getString(Cajero.PASSWORD_DB_NAME)
            );
        }
        return cajero;
    }

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerCajeros() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJEROS + " WHERE codigo!=101");
        ResultSet result = preSt.executeQuery();
        ArrayList cajeros = new ArrayList();
        Cajero cajero = null;
        while (result.next()) {
            cajero = new Cajero(
                    result.getLong(Cajero.CODIGO_DB_NAME),
                    result.getString(Cajero.NOMBRE_DB_NAME),
                    result.getString(Cajero.TURNO_DB_NAME),
                    result.getString(Cajero.DPI_DB_NAME),
                    result.getString(Cajero.DIRECCION_DB_NAME),
                    result.getString(Cajero.SEXO_DB_NAME),
                    result.getString(Cajero.PASSWORD_DB_NAME)
            );
            cajeros.add(cajero);
        }
        return cajeros;
    }

    /**
     * Realizamos una busqueda en base al codigo del cliente. De no existir nos
     * devuelve un valor null.
     *
     * @param filtro
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerCajerosLike(String filtro) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJEROS + " WHERE codigo LIKE '%" + filtro + "%' && codigo!=101");
        ResultSet result = preSt.executeQuery();
        ArrayList cajeros = new ArrayList();
        Cajero cajero = null;
        while (result.next()) {
            cajero = new Cajero(
                    result.getLong(Cajero.CODIGO_DB_NAME),
                    result.getString(Cajero.NOMBRE_DB_NAME),
                    result.getString(Cajero.TURNO_DB_NAME),
                    result.getString(Cajero.DPI_DB_NAME),
                    result.getString(Cajero.DIRECCION_DB_NAME),
                    result.getString(Cajero.SEXO_DB_NAME),
                    result.getString(Cajero.PASSWORD_DB_NAME)
            );
            cajeros.add(cajero);
        }
        return cajeros;
    }

    /**
     * Editamos el cajero por medio de un codigo
     *
     * @param cajero
     * @throws SQLException
     */
    public void actualizarCajero(Cajero cajero, Long codigoCajero) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_CAJERO, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getTurno());
            preSt.setString(3, cajero.getDPI());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(cajero.getPassword()));
            } catch (Exception e) {
            }
            preSt.setLong(7, codigoCajero);
            preSt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * Verifica que las credenciales del cajero sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cajero loginValidation(Long codigo, String password) throws SQLException {
        Cajero cajero = obtenerCajero(codigo);
        try {
            if (cajero != null && Encriptar.desencriptar(cajero.getPassword()).equals(password)) {
                return cajero;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
