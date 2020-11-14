package Clases;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jeffrey
 */
public class LeerXML {

    /**
     * Clase para dividir el archivo por etiquetas y generarlas
     * @param nombreArchivo
     */
    EtiquetasGerente etiquetaGerente=new EtiquetasGerente();
    EtiquetasCliente etiquetaCliente=new EtiquetasCliente();
    EtiquetasCajero etiquetasCajero=new EtiquetasCajero();
    EtiquetasTransaccion etiquetasTransaccion=new EtiquetasTransaccion();
    
    public void dividirEtiquetas(String path,File archivo) {

        try {
            
            // Variables de DocumentBuilderFactory y DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Se asigna un xml al documento
            Document documento = builder.parse(archivo);
            // Separador por etiquetas
            NodeList listadoGerentes = documento.getElementsByTagName("GERENTE");
            NodeList listadoCajeros = documento.getElementsByTagName("CAJERO");
            NodeList listadoClientes = documento.getElementsByTagName("CLIENTE");
            NodeList listadoTransacciones = documento.getElementsByTagName("TRANSACCION");
            
            etiquetaGerente .ingresarEtiquetaGerente(listadoGerentes);
            etiquetasCajero.ingresarEtiquetaCajero(listadoCajeros);
            etiquetaCliente.ingresarEtiquetaCliente(listadoClientes,path);
            etiquetasTransaccion.ingresarEtiquetaTransaccion(listadoTransacciones);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    
}
