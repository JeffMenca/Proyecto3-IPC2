 
package Clases;

import Modelos.GerenteModel;
import Objetos.Gerente;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeffrey
 */
public class EtiquetasGerente {
    
    /**
     * Generador de etiquetas GERENTE
     *
     * @param listadoGerente
     */
    public void ingresarEtiquetaGerente(NodeList listadoGerente) {
        // Imprime en consola la entidad
        
        System.out.println(" <========>Gerente");
        //Se crea un gerente
        Gerente gerente;

        for (int i = 0; i < listadoGerente.getLength(); i++) {

            gerente = new Gerente();
            //Crea un nodo y elemento con hijos
            Node nodo = listadoGerente.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nodo;
                // Busca etiquetas hijas y las recorre
                NodeList hijos = e.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Crea nodo hijo y elemento
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {

                        //Imprime el valor en consola
                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearGerente(gerente, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envia los datos del gerente al model
                 */

                try {
                    //Modelo del gerente
                    GerenteModel nuevoGerente = new GerenteModel();
                nuevoGerente.agregarGerenteManualmente(gerente);
                System.out.println("");
                } catch (Exception ee) {
                    
                }
            }

        }
    }
    
    
    /**
     * Metodo para asignarle los datos al objeto gerente
     *
     * @param gerente
     * @param tag = tag del XML
     * @param atributo = datos del objeto gerente
     */
    public void crearGerente(Gerente gerente, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                gerente.setCodigo(Long.parseLong(atributo));
                break;
            case "NOMBRE":
                gerente.setNombre(atributo);
                break;
            case "TURNO":
                    if (atributo.equalsIgnoreCase("MATUTINO")) {
                    gerente.setTurno(atributo);
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    gerente.setTurno(atributo);
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("No se reconoce el turno");
                }
                    break;
            case "DPI":
                gerente.setDPI(atributo);
                break;
            case "DIRECCION":
                gerente.setDireccion(atributo);
                break;
            case "SEXO":
                gerente.setSexo(atributo);
                break;
            case "PASSWORD":
                gerente.setPassword(atributo);
                break;
            default:
        }
    }
}
