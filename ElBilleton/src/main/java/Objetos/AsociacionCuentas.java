package Objetos;

import java.sql.Date;

/**
 *
 * @author jeffrey
 */
public class AsociacionCuentas {

    public static final String ASOCIACION_DB_NAME = "ASOCIACION_CUENTAS";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_DB_NAME = "fecha";
    public static final String SOLICITUD_CODIGO_DB_NAME = "solicitud_asociacion_codigo";

    private int codigo;
    private Date fecha;
    private int solicitud_codigo;

    public AsociacionCuentas() {
    }

    public AsociacionCuentas(int codigo, Date fecha, int solicitud_codigo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.solicitud_codigo = solicitud_codigo;
    }

    public int getCodigo() {
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

    public int getSolicitud_codigo() {
        return solicitud_codigo;
    }

    public void setSolicitud_codigo(int solicitud_codigo) {
        this.solicitud_codigo = solicitud_codigo;
    }

}
