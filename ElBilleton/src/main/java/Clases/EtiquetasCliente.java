
package Clases;

import Modelos.ClienteModel;
import Modelos.CuentaModel;
import Objetos.Cliente;
import Objetos.Cuenta;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeffrey
 */
public class EtiquetasCliente {
    
    /**
     * Generador de etiquetas CLIENTE
     *
     * @param listadoCliente
     */
    
     public void ingresarEtiquetaCliente(NodeList listadoCliente,String path) {
        Cliente cliente;
        List<Cuenta> cuentas = new ArrayList<>();
        
         try {
              for (int i = 0; i < listadoCliente.getLength(); i++) {
            cliente = new Cliente();
            cuentas.clear();
            // Cojo el nodo actual
            Node nodo = listadoCliente.item(i);
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
                        try {
                            if (hijo.getNodeName().equalsIgnoreCase("CUENTAS")) {
                            cuentas = ingresarEtiquetaCuentas(hijo);
                            //especialidades = tagEspecialidad(hijo);
                        } else {
                            crearCliente(cliente, hijo.getNodeName(), hijo.getTextContent(),path);

                        }
                        } catch (Exception ee) {
                        }
                         
                    }

                }
               
                try {
                    
                    ClienteModel nuevoCliente = new ClienteModel();
                    CuentaModel nuevaCuenta=new CuentaModel();
                    
                nuevoCliente.agregarClienteManualmente(cliente);
                for (int j = 0; j < cuentas.size(); j++) {
                        nuevaCuenta.agregarCuentaManualmente(cuentas.get(j),cliente.getCodigo());
                    }
                System.out.println("");
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee.getMessage());
                }
                
            }

        }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
       
    }
    
    
    /**
     * Metodo para asignarle los datos al objetog gerente
     *
     * @param cliente
     * @param tag = tag del XML
     * @param atributo = dato del objeto
     */
     public void crearCliente(Cliente cliente, String tag, String atributo,String path) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cliente.setCodigo(Long.parseLong(atributo));
                break;

            case "NOMBRE":
                cliente.setNombre(atributo);
                break;

            case "DPI":
                cliente.setDPI(atributo);
                break;
                
            case "BIRTH":
                cliente.setFechaNacimiento(Date.valueOf(atributo));
                break;
                
            case "DIRECCION":
                cliente.setDireccion(atributo); 
                break;

            case "SEXO":
                cliente.setSexo(atributo);
                break;
                
             case "DPI-PDF":
                 try {
                 cliente.setDPI_copia(new FileInputStream(path+ atributo));
             } catch (Exception e) {
             }
                break;
                
            case "PASSWORD":
                cliente.setPassword(atributo);
                break;

            default:
        }
    }
     
     public List<Cuenta> ingresarEtiquetaCuentas(Node cuentas) {
        // Recorro las etiquetas
        List<Cuenta> cuentasVariasCliente = new ArrayList<>();
        cuentasVariasCliente.clear();
        System.out.println("<========>CUENTAS VARIAS DEL CLIENTE<========>");
        // for (int i = 0; i < especialidad.getLength(); i++) {
        // Cojo el nodo actual
        Node nodo = cuentas;
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
                    if (hijo.getNodeName().equalsIgnoreCase("CUENTA")) {
                        System.out.println("AQUI INGRESE A LA ETIQUETA CUENTA SINGULAR Y ENVIE EL HIJO AL SIGUIENTE METODO");
                        //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                        cuentasVariasCliente = crearCuenta(hijos);
                   // especilidadesMedicas.add(hijo.getTextContent());
                    } else {
                        System.out.println("Error al Ingresar a la Etiqueta CUENTA SINGULAR");
                    }
                    
                }

            }
            System.out.println("");
        }

        return cuentasVariasCliente;
    }
     
     public List<Cuenta> crearCuenta(NodeList cuentaUnica) {
        // Recorro las etiquetas
        Cuenta cuenta;
        List<Cuenta> cuentasUnicasCliente = new ArrayList<>();
        cuentasUnicasCliente.clear();
        System.out.println("<========>CUENTA UNICA DEL CLIENTE<========>");
        for (int i = 0; i < cuentaUnica.getLength(); i++) {
            cuenta = new Cuenta();
            
        // Cojo el nodo actual
        Node nodo = cuentaUnica.item(i);
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
                    
                    //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                    //especilidadesMedicas.add(hijo.getTextContent());
                    
                            System.out.println("Etiqueta dentro de Cuenta: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            switch (hijo.getNodeName().toUpperCase()) {
                                case "CODIGO":
                                    cuenta.setCodigo(Long.parseLong(hijo.getTextContent()));
                                    break;
                                case "CREADA":
                                    cuenta.setFecha_creacion(Date.valueOf(hijo.getTextContent()));
                                    break;
                                case "CREDITO":
                                    cuenta.setMonto(Double.parseDouble(hijo.getTextContent()));
                                    break;
                                default:
                                    System.out.println("Mostrar error, etiqueta no conocida");
                                //throw new AssertionError();
                            }
                            
                            
                }//AQUI CIERRA EL NODE.ELEMENTNODE

            }
            System.out.println("AGREGO EL OBJETO CUENTA AL ARRAYLIST");
            cuentasUnicasCliente.add(cuenta);
        }

        
        }
        return cuentasUnicasCliente;
    }
}
