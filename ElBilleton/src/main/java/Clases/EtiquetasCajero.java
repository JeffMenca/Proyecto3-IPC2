
package Clases;

import Modelos.CajeroModel;
import Objetos.Cajero;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeffrey
 */
public class EtiquetasCajero {
    
    /**
     * Generador de etiquetas CAJERO
     *
     * @param listadoCajero
     */
    public void ingresarEtiquetaCajero(NodeList listadoCajero) {
        // Imprime en consola la etiqueta
        System.out.println(" <========>Cajero");

        Cajero cajero;

        for (int i = 0; i < listadoCajero.getLength(); i++) {

            cajero = new Cajero();
            //Crea un nodo y un elemento
            Node nodo = listadoCajero.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nodo;
                //Busca etiquetas hijas y las recorre
                NodeList hijos = e.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Crea nodo hijo y un elemento
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        
                        //Imprime el valor en consola
                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearCajero(cajero, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envia los datos del cajero al model
                 */
                try {
                    CajeroModel nuevoCajero = new CajeroModel();
                nuevoCajero.agregarCajeroManualmente(cajero);
                System.out.println("");
                } catch (Exception ee) {
                    
                }
                
            }

        }
    }
    
    
    /**
     * Metodo para asignarle los datos al objeto cajero
     *
     * @param cajero
     * @param tag = tag del XML
     * @param atributo = datos del objeto cajero
     */
    public void crearCajero(Cajero cajero, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cajero.setCodigo(Long.parseLong(atributo));
                break;

            case "NOMBRE":
                cajero.setNombre(atributo);
                break;

            case "TURNO":
                if (atributo.equalsIgnoreCase("MATUTINO")) {
                    cajero.setTurno(atributo);
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    cajero.setTurno(atributo);
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("No se reconoce el turno");
                }
                break;

            case "DPI":
                cajero.setDPI(atributo);
                break;

            case "DIRECCION":
                cajero.setDireccion(atributo);
                break;

            case "SEXO":
                cajero.setSexo(atributo);
                break;

            case "PASSWORD":
                cajero.setPassword(atributo);
                break;

            default:
        }
    }
}
