
package SQLConnector;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author jeffrey
 */
public class Encriptar {
    
    //Metodo para encriptar
    public static String encriptar(String password) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
    }
    //Metodo para desencriptar
    public static String desencriptar(String password) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(password.getBytes());
        return new String(decode, "utf-8");
    }
}
