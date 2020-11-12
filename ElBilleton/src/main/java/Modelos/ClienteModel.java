package Modelos;

import Objetos.Cliente;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jeffmenca
 */
public class ClienteModel {

    private final String CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME;
    private final String BUSCAR_CLIENTE = CLIENTE + " WHERE " + Cliente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_POR_NOMBRE = CLIENTE + " WHERE " + Cliente.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_CLIENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.FECHA_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + ","
            + Cliente.PASSWORD_DB_NAME + "," + Cliente.PDF_DB_NAME +") VALUES (?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();
    

    /**
     * Realizamos una busqueda en base al id del usuario. De no existir la nota
     * nos devuelve un valor null.
     *
     * @param codigoCliente 
     * @return
     * @throws SQLException
     */
    public Cliente obtenerCliente(int codigoCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
        preSt.setInt(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        Cliente cliente = null;
        while (result.next()) {
            cliente = new Cliente(
                    result.getInt(Cliente.CODIGO_DB_NAME),
                    result.getString(Cliente.NOMBRE_DB_NAME),
                    result.getDate(Cliente.FECHA_DB_NAME),
                    result.getInt(Cliente.DPI_DB_NAME),
                    result.getString(Cliente.DIRECCION_DB_NAME),
                    result.getString(Cliente.SEXO_DB_NAME),
                    result.getString(Cliente.PASSWORD_DB_NAME),
                    result.getBinaryStream(Cliente.PDF_DB_NAME)
            );
        }
        return cliente;
    }
    
    /**
     * Verifica que las credenciales del gerente sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cliente loginValidation(int codigo, String password) throws SQLException {
        Cliente cliente = obtenerCliente(codigo);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }
}
