package Modelos;

import Objetos.LimitesGerente;
import SQLConnector.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffmenca
 */
public class LimitesGerenteModel {

    private final String LIMITES_GERENTE = "SELECT * FROM " + LimitesGerente.LIMITES_GERENTE_DB_NAME;
    private final String INGRESAR_LIMITES = "UPDATE " + LimitesGerente.LIMITES_GERENTE_DB_NAME + " SET " + LimitesGerente.LIMITE2_DB_NAME + "=?,"
            + LimitesGerente.LIMITE3_DB_NAME + "=?";
    private static Connection connection = DbConnection.getConnection();

    /**
     * Agregamos una nueva cuenta. Al completar la insercion devuelve el codigo
     * autogenerado de la cuenta. De no existir nos devolvera <code>-1</code>.
     *
     * @param limites
     * @throws SQLException
     */
    public void agregarLimites(LimitesGerente limites) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(INGRESAR_LIMITES, Statement.RETURN_GENERATED_KEYS);

            preSt.setDouble(1, limites.getLimite_reporte2());
            preSt.setDouble(2, limites.getLimite_reporte3());
            preSt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * Realizamos una busqueda en base al codigo de la cuenta. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public LimitesGerente obtenerLimites() throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(LIMITES_GERENTE);
        ResultSet result = preSt.executeQuery();
        LimitesGerente limites = null;

        while (result.next()) {
            limites = new LimitesGerente(
                    result.getDouble(LimitesGerente.LIMITE2_DB_NAME),
                    result.getDouble(LimitesGerente.LIMITE3_DB_NAME));
        }
        return limites;
    }

}
