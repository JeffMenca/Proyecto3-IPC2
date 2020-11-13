package Objetos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jeffrey
 */
public class Transaccion {

    //Variables constantes de Querys
    public static final String TRANSACCION_DB_NAME = "TRANSACCION";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_DB_NAME = "fecha";
    public static final String HORA_DB_NAME = "hora";
    public static final String TIPO_DB_NAME = "tipo";
    public static final String MONTO_DB_NAME = "monto";
    public static final String CUENTA_CODIGO_DB_NAME = "cuenta_codigo";
    public static final String CAJERO_CODIGO_DB_NAME = "cajero_codigo";

    
    private long codigo;
    private Date fecha;
    private Time hora;
    private String tipo;
    private Double monto;
    private long cuenta_codigo;
    private long cajero_codigo;

    public Transaccion() {
    }

    public Transaccion(long codigo, Date fecha, Time hora, String tipo, Double monto, long cuenta_codigo, long cajero_codigo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.cuenta_codigo = cuenta_codigo;
        this.cajero_codigo = cajero_codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public long getCuenta_codigo() {
        return cuenta_codigo;
    }

    public void setCuenta_codigo(long cuenta_codigo) {
        this.cuenta_codigo = cuenta_codigo;
    }

    public long getCajero_codigo() {
        return cajero_codigo;
    }

    public void setCajero_codigo(long cajero_codigo) {
        this.cajero_codigo = cajero_codigo;
    }

   

}
