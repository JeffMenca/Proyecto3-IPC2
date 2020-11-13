package Modelos;

import Objetos.HistorialGerente;
import Objetos.Gerente;
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
public class HistorialGerenteModel {

    private final String HISTORIAL_GERENTE = "SELECT * FROM " + HistorialGerente.HISTORIAL_GERENTE_DB_NAME;
    private final String BUSCAR_HISTORIAL_GERENTE = HISTORIAL_GERENTE + " WHERE " + HistorialGerente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_GERENTE = HISTORIAL_GERENTE + " WHERE " + HistorialGerente.GERENTE_CODIGO_DB_NAME + " LIKE ?";
    private final String CREAR_HISTORIAL_GERENTE = "INSERT INTO " + HistorialGerente.HISTORIAL_GERENTE_DB_NAME + " (" + HistorialGerente.NOMBRE_DB_NAME + ","
            + HistorialGerente.TURNO_DB_NAME + "," + HistorialGerente.DPI_DB_NAME + "," + HistorialGerente.DIRECCION_DB_NAME + "," + HistorialGerente.SEXO_DB_NAME + ","
            + HistorialGerente.PASSWORD_DB_NAME + "," + HistorialGerente.GERENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos una nuevo Historial de gerente. Al completar la insercion devuelve el codigo
     * autogenerado del historial de gerente. De no existir nos devolvera <code>-1</code>.
     *
     * @param gerente
     * @throws SQLException
     */
    public void agregarHistorialGerente(Gerente gerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_GERENTE, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, gerente.getNombre());
        preSt.setString(2, gerente.getTurno());
        preSt.setString(3, gerente.getDPI());
        preSt.setString(4, gerente.getDireccion());
        preSt.setString(5, gerente.getSexo());
        preSt.setString(6, gerente.getPassword());
        preSt.setLong(7, gerente.getCodigo());
        preSt.executeUpdate();
    }


    /**
     * Realizamos una busqueda en base al codigo del gerente. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoHistorialGerente
     * @return
     * @throws SQLException
     */
    public HistorialGerente obtenerGerente(int codigoHistorialGerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_HISTORIAL_GERENTE);
        preSt.setInt(1, codigoHistorialGerente);
        ResultSet result = preSt.executeQuery();
        HistorialGerente gerente = null;
        while (result.next()) {
            gerente = new HistorialGerente(
                    result.getInt(HistorialGerente.CODIGO_DB_NAME),
                    result.getString(HistorialGerente.NOMBRE_DB_NAME),
                    result.getString(HistorialGerente.TURNO_DB_NAME),
                    result.getString(HistorialGerente.DPI_DB_NAME),
                    result.getString(HistorialGerente.DIRECCION_DB_NAME),
                    result.getString(HistorialGerente.SEXO_DB_NAME),
                    result.getString(HistorialGerente.PASSWORD_DB_NAME),
                    result.getLong(HistorialGerente.GERENTE_CODIGO_DB_NAME)
            );
        }
        return gerente;
    }

   

    
}
