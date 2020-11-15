
package Clases;

import Modelos.TransaccionModel;
import Objetos.Transaccion;
import java.sql.Date;
import java.sql.Time;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeffrey
 */
public class EtiquetasTransaccion {
    
    /**
     * Generador de etiquetas GERENTE
     *
     * @param listadoTransaccion 
     */
    public void ingresarEtiquetaTransaccion(NodeList listadoTransaccion) {
        // Imprime en consola la entidad
        System.out.println(" <========>TRANSACCION");

        Transaccion transaccion ;

        for (int i = 0; i < listadoTransaccion.getLength(); i++) {

            transaccion = new Transaccion();
            // Cojo el nodo actual
            Node nodo = listadoTransaccion.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        try {
                            crearTransaccion(transaccion, hijo.getNodeName(), hijo.getTextContent());
                        } catch (Exception ee) {
                        }
                        

                    }

                }
                /**
                 * Envio a la Base de Datos
                 */
                try {
                    TransaccionModel nuevaTransaccion = new TransaccionModel();
                nuevaTransaccion.agregarTransaccionManualmente(transaccion);
                System.out.println("");
                } catch (Exception ee) {
                   JOptionPane.showMessageDialog(null, ee.getMessage());
                }
                
            }

        }
    }
    
    
    /**
     * Metodo para asignarle los datos al objeto gerente
     *
     * @param transaccion 
     * @param tag = tag del XML
     * @param atributo = datos del objeto gerente
     */
    public void crearTransaccion(Transaccion transaccion, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                transaccion.setCodigo(Long.parseLong(atributo));
                break;

            case "CUENTA-ID":
                transaccion.setCuenta_codigo(Long.parseLong(atributo));
                break;

            case "FECHA":
                transaccion.setFecha(Date.valueOf(atributo));               
                break;

            case "HORA":
                transaccion.setHora(Time.valueOf(atributo));
                break;

            case "TIPO":
                  transaccion.setTipo(atributo);
                break;

            case "MONTO":
                transaccion.setMonto(Double.parseDouble(atributo));
                break;

            case "CAJERO-ID":
                transaccion.setCajero_codigo(Long.parseLong(atributo));
                break;

            default:
        }
    }
}
