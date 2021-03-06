package Objetos;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author jeffrey
 */
public class Cliente {

    //Variables constantes de Querys
    public static final String CLIENTE_DB_NAME = "CLIENTE";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String DPI_DB_NAME = "DPI";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String FECHA_DB_NAME = "fecha_nacimiento";
    public static final String PDF_DB_NAME = "DPI_copia";
    public static final String PASSWORD_DB_NAME = "password";

    private long codigo;
    private String nombre;
    private Date fechaNacimiento;
    private String DPI;
    private String direccion;
    private String sexo;
    private String password;
    private InputStream DPI_copia;
    private Double totalTransaccion;
    
    public Cliente() {
    }
    public Cliente(long codigo, String nombre, String DPI, String direccion, String sexo, Double totalTransaccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.DPI = DPI;
        this.direccion = direccion;
        this.sexo = sexo;
        this.totalTransaccion=totalTransaccion;
    }

    public Cliente(long codigo, String nombre, Date fechaNacimiento, String DPI, String direccion, String sexo, String password, InputStream DPI_copia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.DPI = DPI;
        this.direccion = direccion;
        this.sexo = sexo;
        this.password = password;
        this.DPI_copia = DPI_copia;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InputStream getDPI_copia() {
        return DPI_copia;
    }

    public void setDPI_copia(InputStream DPI_copia) {
        this.DPI_copia = DPI_copia;
    }

    public Double getTotalTransaccion() {
        return totalTransaccion;
    }

    public void setTotalTransaccion(Double totalTransaccion) {
        this.totalTransaccion = totalTransaccion;
    }
    

}
