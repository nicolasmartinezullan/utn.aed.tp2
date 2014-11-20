package utn.aed.tp2;

import utn.aed.tp2.Enums.Servicios;

/**
 * Trabajo Practico 2 - Administracion de consorcios
 *
 * @author Nicolas Martinez - 66947 - 2014 - 1K9
 */
public class Propiedad {
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Permite crear la propiedad en base a la identificación, propietario,
     * metros de terreno, metros cubiertos y servicios.
     *
     * @param id identificacion de la propiedad
     * @param propietario nombre del propietario de la propiedad. '[sin-nombre]'
     * por defecto
     * @param metrosTerreno metros cuadrados del terreno
     * @param metrosCubiertos metros cuadrados cubiertos
     * @param servicios servicios con los que se desea inicializar la propiedad
     */
    public Propiedad(int id, String propietario, int metrosTerreno, int metrosCubiertos, Servicio[] servicios) {
        this.id = Math.abs(id);
        this.propietario = (propietario.trim().equals("") ? "[sin-nombre]" : propietario.trim());
        this.metrosTerreno = Math.abs(metrosTerreno);
        this.metrosCubiertos = (this.metrosTerreno > 0) ? Math.abs(metrosCubiertos) : 0;
        this.servicios = new Servicio[Servicios.values().length];
        if (servicios != null) {
            for (Servicio s : servicios) {
                if (s != null) {
                    this.servicios[s.getTipo().getValue()] = s;
                }
            }
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private final int id;
    private final String propietario;
    private final int metrosTerreno;
    private final int metrosCubiertos;
    private final Servicio[] servicios;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public int getId() {
        return id;
    }
    public String getPropietario() {
        return propietario;
    }
    public int getMetrosTerreno() {
        return metrosTerreno;
    }
    public int getMetrosCubiertos() {
        return metrosCubiertos;
    }
    public Servicio[] getServicios() {
        return servicios;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    /**
     * Permite saber si la propiedad esta construida o no.
     *
     * @return true si la propiedad esta construida; false si es un terreno sin
     * construccion.
     */
    public boolean estaConstruida() {
        return (metrosCubiertos != 0);
    }

    /**
     * Permite conocer el monto a pagar de expensas.
     *
     * @return el monto a pagar de expensas.
     */
    public double calcularExpensas() {
        return ((double) metrosTerreno * 1.2 + (double) metrosCubiertos * 0.3 + calcularSumatoriaServiciosExtras());
    }

    /**
     * Permite conocer el promedio pagado por servicios.
     *
     * @return el promedio pagado por servicios.
     */
    public double calcularPromedioServicios() {
        double contador = 0;
        for (Servicio s : this.servicios) {
            if (s != null) {
                contador++;
            }
        }
        return ((contador != 0) ? (calcularSumatoriaServiciosExtras() / contador) : 0);
    }

    /**
     * Calcula la sumatoria de los montos de los servicios extras que la
     * propiedad consume
     *
     * @return la sumatoria de los montos de los servicios extras que la
     * propiedad consume
     */
    public double calcularSumatoriaServiciosExtras() {
        double suma = 0;
        for (Servicio s : servicios) {
            if (s != null) {
                suma = +s.getMonto();
            }
        }
        return suma;
    }

    @Override
    public String toString() {
        return String.format("Propiedad[id: %s, propietario: %s, mtsTerreno: %s, mtsCubiertos: %s]", this.getId(), this.getPropietario(), this.getMetrosTerreno(), this.getMetrosCubiertos());
    }
//</editor-fold>
}
