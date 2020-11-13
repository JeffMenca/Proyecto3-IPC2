package Modelos;

import Objetos.Cliente;
import Objetos.Cuenta;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            + Cliente.PASSWORD_DB_NAME + "," + Cliente.PDF_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String CREAR_CLIENTE_MANUALMENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.CODIGO_DB_NAME + "," + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.FECHA_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + ","
            + Cliente.PASSWORD_DB_NAME + "," + Cliente.PDF_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    private static Connection connection = DbConnection.getConnection();
    HistorialClienteModel historialCliente=new HistorialClienteModel();
    CuentaModel cuentasModel=new CuentaModel();

    /**
     * Agregamos un nuevo Cliente al completar la insercion devuelve el codigo
     * autogenerado del cliente. De no existir nos devolvera <code>-1</code>.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarCliente(Cliente cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cliente.getNombre());
        preSt.setDate(2, cliente.getFechaNacimiento());
        preSt.setString(3, cliente.getDPI());
        preSt.setString(4, cliente.getDireccion());
        preSt.setString(5, cliente.getSexo());
        preSt.setString(6, cliente.getPassword());
        preSt.setBinaryStream(7, cliente.getDPI_copia());

        preSt.executeUpdate();
        historialCliente.agregarHistorialCliente(cliente);
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * Agregamos un nuevo Cliente con el codigo manualmente. Al completar la
     * insercion devuelve el codigo del cliente. De no existir nos
     * devolvera <code>-1</code>.
     *
     * @param cliente
     * @param cuentas
     * @return
     * @throws SQLException
     */
    public long agregarClienteManualmente(Cliente cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_MANUALMENTE, Statement.RETURN_GENERATED_KEYS);
        preSt.setLong(1, cliente.getCodigo());
        preSt.setString(2, cliente.getNombre());
        preSt.setDate(3, cliente.getFechaNacimiento());
        preSt.setString(4, cliente.getDPI());
        preSt.setString(5, cliente.getDireccion());
        preSt.setString(6, cliente.getSexo());
        preSt.setString(7, cliente.getPassword());
        preSt.setBinaryStream(8, cliente.getDPI_copia());

        preSt.executeUpdate();
        
        
        
        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * Realizamos una busqueda en base al codigo del cliente. De no existir nos
     * devuelve un valor null.
     *
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public Cliente obtenerCliente(long codigoCliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
        preSt.setLong(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        Cliente cliente = null;
        while (result.next()) {
            cliente = new Cliente(
                    result.getLong(Cliente.CODIGO_DB_NAME),
                    result.getString(Cliente.NOMBRE_DB_NAME),
                    result.getDate(Cliente.FECHA_DB_NAME),
                    result.getString(Cliente.DPI_DB_NAME),
                    result.getString(Cliente.DIRECCION_DB_NAME),
                    result.getString(Cliente.SEXO_DB_NAME),
                    result.getString(Cliente.PASSWORD_DB_NAME),
                    result.getBinaryStream(Cliente.PDF_DB_NAME)
            );
        }
        return cliente;
    }

    /**
     * Verifica que las credenciales del cliente sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cliente loginValidation(long codigo, String password) throws SQLException {
        Cliente cliente = obtenerCliente(codigo);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }
}
