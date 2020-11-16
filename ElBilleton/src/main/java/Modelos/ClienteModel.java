package Modelos;

import Clases.PDFHistorial;
import Objetos.Cliente;
import Objetos.Cuenta;
import Objetos.Transaccion;
import SQLConnector.DbConnection;
import SQLConnector.Encriptar;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
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
            + Cliente.PASSWORD_DB_NAME + "=?," + Cliente.PDF_DB_NAME + "=? WHERE " + Cliente.CODIGO_DB_NAME + " =?";
    private final String DPI_CLIENTE = "SELECT " + Cliente.PDF_DB_NAME + " FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CODIGO_DB_NAME + "= ?";
    private final String REPORTE_2 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME
            + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo WHERE T.monto>? GROUP BY C.codigo";
    private final String REPORTE_3 = "SELECT C.*,SUM(T.monto) AS TOTAL FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME
            + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo GROUP BY C.codigo HAVING SUM(T.monto)>?";
    private final String REPORTE_4 = "SELECT C.*,SUM(CU.monto) AS TOTAL FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo GROUP BY C.codigo ORDER BY TOTAL DESC LIMIT 10";
    private final String REPORTE_5 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C WHERE nombre NOT IN(SELECT C.nombre FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON CU.cliente_codigo=C.codigo RIGHT JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo WHERE T.fecha BETWEEN ? AND ? GROUP BY C.codigo )";
    private final String REPORTE_6 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON CU.cliente_codigo=C.codigo WHERE CU.monto>? && C.nombre LIKE ? GROUP BY C.codigo ORDER BY C.nombre DESC";
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
            PDFHistorial crearPDF = new PDFHistorial(cliente.getDPI_copia());
            InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
            InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
            preSt.setBinaryStream(8, pdf1);
            preSt.executeUpdate();
            cliente.setDPI_copia(pdf2);
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
     * Obtenemos el reporte 2 por medio del limite del reporte 2
     *
     * @param limite2
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte2(Double limite2) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_2);
            preSt.setDouble(1, limite2);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    /**
     * Obtenemos el reporte 3 por medio del limite del reporte 3
     *
     * @param limite2
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte3(Double limite2) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_3);
            preSt.setDouble(1, limite2);
            ResultSet result = preSt.executeQuery();
            ArrayList clientes = new ArrayList();
            Cliente cliente = null;
            while (result.next()) {
                cliente = new Cliente(
                        result.getLong(Cliente.CODIGO_DB_NAME),
                        result.getString(Cliente.NOMBRE_DB_NAME),
                        result.getString(Cliente.DPI_DB_NAME),
                        result.getString(Cliente.DIRECCION_DB_NAME),
                        result.getString(Cliente.SEXO_DB_NAME),
                        result.getDouble("TOTAL")
                );
                clientes.add(cliente);
            }
            return clientes;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    /**
     * Obtenemos el reporte 4 por medio del limite del reporte 4
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte4() throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_4);
            ResultSet result = preSt.executeQuery();
            ArrayList clientes = new ArrayList();
            Cliente cliente = null;
            while (result.next()) {
                cliente = new Cliente(
                        result.getLong(Cliente.CODIGO_DB_NAME),
                        result.getString(Cliente.NOMBRE_DB_NAME),
                        result.getString(Cliente.DPI_DB_NAME),
                        result.getString(Cliente.DIRECCION_DB_NAME),
                        result.getString(Cliente.SEXO_DB_NAME),
                        result.getDouble("TOTAL")
                );
                clientes.add(cliente);
            }
            return clientes;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    /**
     * Obtenemos el reporte 5 por medio del limite del reporte 5
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte5(Date fechaInicio, Date fechaFinal) throws SQLException {
        try {

            PreparedStatement preSt = connection.prepareStatement(REPORTE_5);
            preSt.setDate(1, fechaInicio);
            preSt.setDate(2, fechaFinal);

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    /**
     * Obtenemos el reporte 6 por medio del limite del reporte 6
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte6(Double limite, String nombre) throws SQLException {
        try {

            PreparedStatement preSt = connection.prepareStatement(REPORTE_6);
            preSt.setDouble(1, limite);
            preSt.setString(2, "%" + nombre + "%");
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
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Editamos el cliente por medio de un codig
     *
     * @param codigoCliente
     * @throws SQLException
     */
    public void actualizarCliente(Cliente cliente, Long codigoCliente) throws SQLException {
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
     * @param codigo
     * @return
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
