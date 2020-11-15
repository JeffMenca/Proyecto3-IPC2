package Objetos;

/**
 *
 * @author jeffrey
 */
public class LimitesGerente {

    //Variables constantes de Querys
    public static final String LIMITES_GERENTE_DB_NAME = "LIMITES_GERENTE";
    public static final String LIMITE2_DB_NAME = "limite_reporte2";
    public static final String LIMITE3_DB_NAME = "limite_reporte3";

   
    private Double limite_reporte2;
    private Double limite_reporte3;

    public LimitesGerente() {
    }

    public LimitesGerente(Double limite_reporte2, Double limite_reporte3) {
        this.limite_reporte2 = limite_reporte2;
        this.limite_reporte3 = limite_reporte3;
    }

    public Double getLimite_reporte2() {
        return limite_reporte2;
    }

    public void setLimite_reporte2(Double limite_reporte2) {
        this.limite_reporte2 = limite_reporte2;
    }

    public Double getLimite_reporte3() {
        return limite_reporte3;
    }

    public void setLimite_reporte3(Double limite_reporte3) {
        this.limite_reporte3 = limite_reporte3;
    }


}
