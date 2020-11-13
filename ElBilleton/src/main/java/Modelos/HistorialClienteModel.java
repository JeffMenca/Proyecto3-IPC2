package Modelos;

import Objetos.HistorialCliente;
import Objetos.Cliente;
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
public class HistorialClienteModel {

    private final String HISTORIAL_CLIENTE = "SELECT * FROM " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME;
    private final String BUSCAR_HISTORIAL_CLIENTE = HISTORIAL_CLIENTE + " WHERE " + HistorialCliente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = HISTORIAL_CLIENTE + " WHERE " + HistorialCliente.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_HISTORIAL_CLIENTE = "INSERT INTO " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME + " (" + HistorialCliente.NOMBRE_DB_NAME + ","
            + HistorialCliente.FECHA_DB_NAME + "," + HistorialCliente.DPI_DB_NAME + "," + HistorialCliente.DIRECCION_DB_NAME + "," + HistorialCliente.SEXO_DB_NAME + ","
            + HistorialCliente.PASSWORD_DB_NAME + "," + HistorialCliente.PDF_DB_NAME + "," + HistorialCliente.CLIENTE_CODIGO_DB_NAME +") VALUES (?,?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos un nuevo Historial Cliente al completar la insercion devuelve el codigo
     * autogenerado del historial cliente. De no existir nos devolvera <code>-1</code>.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarHistorialCliente(Cliente cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_CLIENTE, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cliente.getNombre());
        preSt.setDate(2, cliente.getFechaNacimiento());
        preSt.setString(3, cliente.getDPI());
        preSt.setString(4, cliente.getDireccion());
        preSt.setString(5, cliente.getSexo());
        preSt.setString(6, cliente.getPassword());
        preSt.setBlob(7, cliente.getDPI_copia());
        preSt.setLong(8, cliente.getCodigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    

    /**
     * Realizamos una busqueda en base al codigo del usuario. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoHistorialCliente
     * @return
     * @throws SQLException
     */
    public HistorialCliente obtenerHistorialCliente(int codigoHistorialCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_HISTORIAL_CLIENTE);
        preSt.setInt(1, codigoHistorialCliente);
        ResultSet result = preSt.executeQuery();
        HistorialCliente cliente = null;
        while (result.next()) {
            cliente = new HistorialCliente(
                    result.getInt(HistorialCliente.CODIGO_DB_NAME),
                    result.getString(HistorialCliente.NOMBRE_DB_NAME),
                    result.getDate(HistorialCliente.FECHA_DB_NAME),
                    result.getString(HistorialCliente.DPI_DB_NAME),
                    result.getString(HistorialCliente.DIRECCION_DB_NAME),
                    result.getString(HistorialCliente.SEXO_DB_NAME),
                    result.getString(HistorialCliente.PASSWORD_DB_NAME),
                    result.getBinaryStream(HistorialCliente.PDF_DB_NAME),
                    result.getLong(HistorialCliente.CLIENTE_CODIGO_DB_NAME)
            );
        }
        return cliente;
    }
}
