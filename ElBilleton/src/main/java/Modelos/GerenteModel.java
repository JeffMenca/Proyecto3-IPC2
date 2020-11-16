package Modelos;

import Objetos.Cajero;
import Objetos.Gerente;
import SQLConnector.DbConnection;
import SQLConnector.Encriptar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author jeffmenca
 */
public class GerenteModel {

    private final String GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME;
    private final String BUSCAR_GERENTES = "SELECT * FROM GERENTE";
    private final String BUSCAR_GERENTE = GERENTE + " WHERE " + Gerente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = GERENTE + " WHERE " + Gerente.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_GERENTE = "INSERT INTO " + Gerente.GERENTE_DB_NAME + " (" + Gerente.NOMBRE_DB_NAME + ","
            + Gerente.TURNO_DB_NAME + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME + "," + Gerente.SEXO_DB_NAME + ","
            + Gerente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_GERENTE_MANUAL = "INSERT INTO " + Gerente.GERENTE_DB_NAME + " (" + Gerente.CODIGO_DB_NAME + "," + Gerente.NOMBRE_DB_NAME + ","
            + Gerente.TURNO_DB_NAME + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME + "," + Gerente.SEXO_DB_NAME + ","
            + Gerente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String EDITAR_GERENTE = "UPDATE " + Gerente.GERENTE_DB_NAME + " SET " + Gerente.NOMBRE_DB_NAME + "=?,"
            + Gerente.TURNO_DB_NAME + "=?," + Gerente.DPI_DB_NAME + "=?," + Gerente.DIRECCION_DB_NAME + "=?," + Gerente.SEXO_DB_NAME + "=?,"
            + Gerente.PASSWORD_DB_NAME + "=? WHERE codigo=?";
    private static Connection connection = DbConnection.getConnection();
    HistorialGerenteModel historialGerente = new HistorialGerenteModel();

    /**
     * Agregamos una nuevo gerente. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param gerente
     * @return
     * @throws SQLException
     */
    public long agregarGerente(Gerente gerente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_GERENTE, Statement.RETURN_GENERATED_KEYS);
            preSt.setString(1, gerente.getNombre());
            preSt.setString(2, gerente.getTurno());
            preSt.setString(3, gerente.getDPI());
            preSt.setString(4, gerente.getDireccion());
            preSt.setString(5, gerente.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(gerente.getPassword()));
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
     * Agregamos una nuevo gerente con codigo manual. Al completar la insercion
     * devuelve el codigo De no existir nos devolvera <code>-1</code>.
     *
     * @param gerente
     * @return
     * @throws SQLException
     */
    public long agregarGerenteManualmente(Gerente gerente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_GERENTE_MANUAL, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, gerente.getCodigo());
            preSt.setString(2, gerente.getNombre());
            preSt.setString(3, gerente.getTurno());
            preSt.setString(4, gerente.getDPI());
            preSt.setString(5, gerente.getDireccion());
            preSt.setString(6, gerente.getSexo());
            try {
                preSt.setString(7, Encriptar.encriptar(gerente.getPassword()));
            } catch (Exception e) {
            }

            preSt.executeUpdate();
            historialGerente.agregarHistorialGerente(gerente);
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
     * Realizamos una busqueda en base al codigo del gerente. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoGerente
     * @return
     * @throws SQLException
     */
    public Gerente obtenerGerente(Long codigoGerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setLong(1, codigoGerente);
        ResultSet result = preSt.executeQuery();
        Gerente gerente = null;
        while (result.next()) {
            gerente = new Gerente(
                    result.getLong(Gerente.CODIGO_DB_NAME),
                    result.getString(Gerente.NOMBRE_DB_NAME),
                    result.getString(Gerente.TURNO_DB_NAME),
                    result.getString(Gerente.DPI_DB_NAME),
                    result.getString(Gerente.DIRECCION_DB_NAME),
                    result.getString(Gerente.SEXO_DB_NAME),
                    result.getString(Gerente.PASSWORD_DB_NAME)
            );
        }
        return gerente;
    }

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerGerentes() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTES);
        ResultSet result = preSt.executeQuery();
        ArrayList gerentes = new ArrayList();
        Gerente gerente = null;
        while (result.next()) {
            gerente = new Gerente(
                    result.getLong(Gerente.CODIGO_DB_NAME),
                    result.getString(Gerente.NOMBRE_DB_NAME),
                    result.getString(Gerente.TURNO_DB_NAME),
                    result.getString(Gerente.DPI_DB_NAME),
                    result.getString(Gerente.DIRECCION_DB_NAME),
                    result.getString(Gerente.SEXO_DB_NAME),
                    result.getString(Gerente.PASSWORD_DB_NAME)
            );
            gerentes.add(gerente);
        }
        return gerentes;
    }

    /**
     * Realizamos una busqueda en base al codigo del cliente. De no existir nos
     * devuelve un valor null.
     *
     * @param filtro
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerGerentesLike(String filtro) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTES + " WHERE codigo LIKE '%" + filtro + "%'");
        ResultSet result = preSt.executeQuery();
        ArrayList gerentes = new ArrayList();
        Gerente gerente = null;
        while (result.next()) {
            gerente = new Gerente(
                    result.getLong(Gerente.CODIGO_DB_NAME),
                    result.getString(Gerente.NOMBRE_DB_NAME),
                    result.getString(Gerente.TURNO_DB_NAME),
                    result.getString(Gerente.DPI_DB_NAME),
                    result.getString(Gerente.DIRECCION_DB_NAME),
                    result.getString(Gerente.SEXO_DB_NAME),
                    result.getString(Gerente.PASSWORD_DB_NAME)
            );
            gerentes.add(gerente);
        }
        return gerentes;
    }

    /**
     * Editamos el cajero por medio de un codig
     *
     * @param cajero
     * @throws SQLException
     */
    public void actualizarGerente(Gerente gerente, Long codigoGerente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_GERENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, gerente.getNombre());
            preSt.setString(2, gerente.getTurno());
            preSt.setString(3, gerente.getDPI());
            preSt.setString(4, gerente.getDireccion());
            preSt.setString(5, gerente.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(gerente.getPassword()));
            } catch (Exception e) {
            }
            preSt.setLong(7, codigoGerente);
            preSt.executeUpdate();

        } catch (Exception e) {
        }

    }

    /**
     * Verifica que las credenciales del gerente sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Gerente loginValidation(Long codigo, String password) throws SQLException {
        Gerente gerente = obtenerGerente(codigo);
        try {
            if (gerente != null && Encriptar.desencriptar(gerente.getPassword()).equals(password)) {
                return gerente;
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Verifica si el gerente estra trabajando dentro de su turno
     *
     * @param codigoGerente
     * @return
     * @throws SQLException
     */
    public Boolean enHora(Long codigoGerente) throws SQLException {
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio, horaFinal;
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setLong(1, codigoGerente);
        ResultSet result = preSt.executeQuery();
        String turno = "";
        while (result.next()) {

            turno = result.getString(Gerente.TURNO_DB_NAME);
        }
        if (turno.equalsIgnoreCase("Vespertino")) {
            horaInicio = LocalTime.of(1, 0);
            horaFinal = LocalTime.of(22, 0);
        } else {
            horaInicio = LocalTime.of(0, 0);
            horaFinal = LocalTime.of(23, 59);
        }

        if (horaActual.isAfter(horaInicio) && (horaActual.isBefore(horaFinal))) {
            return true;
        } else {
            return false;
        }
    }
}
