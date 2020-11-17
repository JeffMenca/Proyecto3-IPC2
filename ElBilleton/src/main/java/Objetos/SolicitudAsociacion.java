package Objetos;

import java.sql.Date;

/**
 *
 * @author jeffrey
 */
public class SolicitudAsociacion {

    //Variables constantes de Querys
    public static final String SOLICITUD_DB_NAME = "SOLICITUD_ASOCIACION";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_DB_NAME = "fecha";
    public static final String ESTADO_DB_NAME = "estado";
    public static final String CUENTA_ENVIA_CODIGO_DB_NAME = "cuenta_envia_codigo";
    public static final String CUENTA_RECIBE_CODIGO_DB_NAME = "cuenta2_recibe_codigo1";

    
    private int codigo;
    private Date fecha;
    private String estado;
    private Long cuenta_envia_codigo;
    private Long cuenta2_recibe_codigo1;

    public SolicitudAsociacion() {
    }

    public SolicitudAsociacion(int codigo, Date fecha, String estado, Long cuenta_envia_codigo, Long cuenta2_recibe_codigo1) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.cuenta_envia_codigo = cuenta_envia_codigo;
        this.cuenta2_recibe_codigo1 = cuenta2_recibe_codigo1;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCuenta_envia_codigo() {
        return cuenta_envia_codigo;
    }

    public void setCuenta_envia_codigo(Long cuenta_envia_codigo) {
        this.cuenta_envia_codigo = cuenta_envia_codigo;
    }

    public Long getCuenta2_recibe_codigo1() {
        return cuenta2_recibe_codigo1;
    }

    public void setCuenta2_recibe_codigo1(Long cuenta2_recibe_codigo1) {
        this.cuenta2_recibe_codigo1 = cuenta2_recibe_codigo1;
    }
    
     
}
