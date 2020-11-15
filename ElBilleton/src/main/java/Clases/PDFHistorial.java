package Clases;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author jeffrey
 */
public class PDFHistorial {

    private InputStream pdfDPI;

    ByteArrayOutputStream baOS = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len;

    public PDFHistorial(InputStream pdfDPI) {
        this.pdfDPI = pdfDPI;
    }

    public byte[] obtenerArrayDatos() {
        try {

            while ((len = getPdfDPI().read(buffer)) > -1) {
                baOS.write(buffer, 0, len);
            }
            baOS.flush();

            return baOS.toByteArray();

        } catch (IOException e) {
            System.out.println("Error al duplicar archivo");
            return null;
        }
    }

    public InputStream getPdfDPI() {
        return pdfDPI;
    }
}
