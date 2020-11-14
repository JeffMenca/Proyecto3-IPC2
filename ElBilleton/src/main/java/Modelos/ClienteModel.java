package Modelos;

import Objetos.Cliente;
import SQLConnector.DbConnection;
import SQLConnector.Encriptar;
import java.io.InputStream;
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
public class ClienteModel {

    private final String CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME;
    private final String BUSCAR_CLIENTE = CLIENTE + " WHERE " + Cliente.CODIGO_DB_NAME + " = ? LIMIT 1";
    private final String BUSCAR_CLIENTES = "SELECT * FROM CLIENTE";
    private final String BUSCAR_POR_NOMBRE = CLIENTE + " WHERE " + Cliente.NOMBRE_DB_NAME + " LIKE ?";
    private final String CREAR_CLIENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.FECHA_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + ","
            + Cliente.PASSWORD_DB_NAME + "," + Cliente.PDF_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String CREAR_CLIENTE_MANUALMENTE = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.CODIGO_DB_NAME + "," + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.FECHA_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + ","
            + Cliente.PASSWORD_DB_NAME + "," + Cliente.PDF_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    private final String EDITAR_CLIENTE = "UPDATE " + Cliente.CLIENTE_DB_NAME + " SET " + Cliente.NOMBRE_DB_NAME + "=?,"
            + Cliente.FECHA_DB_NAME + "=?," + Cliente.DPI_DB_NAME + "=?," + Cliente.DIRECCION_DB_NAME + "=?," + Cliente.SEXO_DB_NAME + "=?,"
            + Cliente.PASSWORD_DB_NAME + "=?," + Cliente.PDF_DB_NAME + "=? WHERE "+Cliente.CODIGO_DB_NAME+" =?";
    private final String DPI_CLIENTE = "SELECT " + Cliente.PDF_DB_NAME + " FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CODIGO_DB_NAME + "= ?";
    private static Connection connection = DbConnection.getConnection();
    HistorialClienteModel historialCliente = new HistorialClienteModel();
    CuentaModel cuentasModel = new CuentaModel();

    /**
     * Agregamos un nuevo Cliente al completar la insercion devuelve el codigo
     * autogenerado del cliente. De no existir nos devolvera <code>-1</code>.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarCliente(Cliente cliente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cliente.getNombre());
            preSt.setDate(2, cliente.getFechaNacimiento());
            preSt.setString(3, cliente.getDPI());
            preSt.setString(4, cliente.getDireccion());
            preSt.setString(5, cliente.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(cliente.getPassword()));
            } catch (Exception e) {
            }
            preSt.setBinaryStream(7, cliente.getDPI_copia());

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
     * Agregamos un nuevo Cliente con el codigo manualmente. Al completar la
     * insercion devuelve el codigo del cliente. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarClienteManualmente(Cliente cliente) throws SQLException {

        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_MANUALMENTE, Statement.RETURN_GENERATED_KEYS);
            preSt.setLong(1, cliente.getCodigo());
            preSt.setString(2, cliente.getNombre());
            preSt.setDate(3, cliente.getFechaNacimiento());
            preSt.setString(4, cliente.getDPI());
            preSt.setString(5, cliente.getDireccion());
            preSt.setString(6, cliente.getSexo());
            try {
                preSt.setString(7, Encriptar.encriptar(cliente.getPassword()));
            } catch (Exception e) {
            }
            preSt.setBinaryStream(8, cliente.getDPI_copia());

            preSt.executeUpdate();
            historialCliente.agregarHistorialCliente(cliente);
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
     * Realizamos una busqueda en base al codigo del cliente. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerClientes() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTES);
        ResultSet result = preSt.executeQuery();
        ArrayList clientes = new ArrayList();
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
            clientes.add(cliente);
        }
        return clientes;
    }

    /**
     * Realizamos una busqueda en base al codigo del cliente. De no existir nos
     * devuelve un valor null.
     *
     * @param filtro
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerClientesLike(String filtro) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTES + " WHERE codigo LIKE '%" + filtro + "%' ");
        ResultSet result = preSt.executeQuery();
        ArrayList clientes = new ArrayList();
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
            clientes.add(cliente);
        }
        return clientes;
    }

    /**
     * Editamos el cliente por medio de un codig
     *
     * @param cliente
     * @throws SQLException
     */
    public void actualizarCliente(Cliente cliente,Long codigoCliente) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_CLIENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cliente.getNombre());
            preSt.setDate(2, cliente.getFechaNacimiento());
            preSt.setString(3, cliente.getDPI());
            preSt.setString(4, cliente.getDireccion());
            preSt.setString(5, cliente.getSexo());
            try {
                preSt.setString(6, Encriptar.encriptar(cliente.getPassword()));
            } catch (Exception e) {
            }
            preSt.setBinaryStream(7, cliente.getDPI_copia());
            preSt.setLong(8, codigoCliente);
            preSt.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    
    /**
     * Obtiene el DPI en PDF
     *
     * @param cliente
     * @throws SQLException
     */
    public InputStream obtenerDPI(long codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(DPI_CLIENTE);
            preSt.setLong(1, codigo);

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                return result.getBlob(1).getBinaryStream();
            }

        } catch (SQLException e) {
            return null;
        }
        return null;
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
        try {
            if (cliente != null && Encriptar.desencriptar(cliente.getPassword()).equals(password)) {
                return cliente;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
