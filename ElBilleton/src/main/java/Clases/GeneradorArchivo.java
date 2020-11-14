package Clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author jeffrey
 */
public class GeneradorArchivo {

    private String path;

    public InputStream extraerArchivo(String nombre, HttpServletRequest request) {
        try {
            Part filePart = request.getPart(nombre);
            return filePart.getInputStream();
        } catch (IOException | ServletException e) {
            return null;
        }
    }

    public void escribirArchivo(Part filePart) {
        try {
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
            inputStream.transferTo(outputStream);
            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            setPath(file.getAbsolutePath().replace("/" + file.getName(), "/"));
        } catch (IOException e) {
            //No se logro escribir el archivo
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
