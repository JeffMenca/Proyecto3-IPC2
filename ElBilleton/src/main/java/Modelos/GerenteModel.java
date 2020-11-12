package Modelos;

import Objetos.Gerente;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffmenca
 */
public class GerenteModel {

    private final String GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME;
    private final String BUSCAR_GERENTE = GERENTE + " WHERE " + Gerente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = GERENTE + " WHERE " + Gerente.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_GERENTE = "INSERT INTO " + Gerente.GERENTE_DB_NAME + " (" + Gerente.NOMBRE_DB_NAME + ","
            + Gerente.TURNO_DB_NAME + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME + "," + Gerente.SEXO_DB_NAME + ","
            + Gerente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Realizamos una busqueda en base al id del usuario. De no existir la nota
     * nos devuelve un valor null.
     *
     * @param codigoGerente
     * @return
     * @throws SQLException
     */
    public Gerente obtenerGerente(int codigoGerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setInt(1, codigoGerente);
        ResultSet result = preSt.executeQuery();
        Gerente gerente = null;
        while (result.next()) {
            gerente = new Gerente(
                    result.getInt(Gerente.CODIGO_DB_NAME),
                    result.getString(Gerente.NOMBRE_DB_NAME),
                    result.getString(Gerente.TURNO_DB_NAME),
                    result.getInt(Gerente.DPI_DB_NAME),
                    result.getString(Gerente.DIRECCION_DB_NAME),
                    result.getString(Gerente.SEXO_DB_NAME),
                    result.getString(Gerente.PASSWORD_DB_NAME)
            );
        }
        return gerente;
    }

    /**
     * Verifica que las credenciales del gerente sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Gerente loginValidation(int codigo, String password) throws SQLException {
        Gerente gerente = obtenerGerente(codigo);
        if (gerente != null && gerente.getPassword().equals(password)) {
            return gerente;
        }
        return null;
    }

    public Boolean enHora(int codigoGerente) throws SQLException {
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio, horaFinal;
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setInt(1, codigoGerente);
        ResultSet result = preSt.executeQuery();
        String turno = "";
        while (result.next()) {

            turno = result.getString(Gerente.TURNO_DB_NAME);
        }
        if (turno.equals("Vespertino")) {
            horaInicio = LocalTime.of(1, 0);
            horaFinal = LocalTime.of(22, 0);
        } else {
            horaInicio = LocalTime.of(6, 0);
            horaFinal = LocalTime.of(14, 30);
        }

        if (horaActual.isAfter(horaInicio) && (horaActual.isBefore(horaFinal))) {
            return true;
        } else {
            return false;
        }
    }
}
