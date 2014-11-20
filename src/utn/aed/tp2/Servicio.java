package utn.aed.tp2;

import utn.aed.tp2.Enums.Servicios;

/**
 * Trabajo Practico 2 - Administracion de consorcios
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Servicio {

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Servicio(Servicios tipo, double monto) {
        this.tipo = tipo;
        this.monto = Math.abs(monto);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private final Servicios tipo;
    private final double monto;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Servicios getTipo() {
        return tipo;
    }
    public double getMonto() {
        return monto;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    @Override
    public String toString() {
        return String.format("Servicio[tipo: %s, monto: $ %.2f]", this.getTipo(), this.getMonto());
    }
    //</editor-fold>
}
