package utn.aed.tp2;
import utn.aed.tp2.Enums.Servicios;

/**
 * Trabajo Practico 2 - Administracion de consorcios
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Consorcio {

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Consorcio(String nombre) {
        this.nombre = nombre.trim();
        this.indiceProximaPropiedadParaAgregar = 0;
        this.cantidadMaximaPropiedadesAlcanzada = false;
        this.propiedades = new Propiedad[CANTIDAD_MAXIMA_PROPIEDADES];
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final int CANTIDAD_MAXIMA_PROPIEDADES = 100;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private final String nombre;
    private final Propiedad[] propiedades;
    private int indiceProximaPropiedadParaAgregar;
    private boolean cantidadMaximaPropiedadesAlcanzada;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public String getNombre() {
        return nombre;
    }

    public Propiedad[] getPropiedades() {
        return propiedades;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    /**
     * Calcula la cantidad de propiedades cargadas
     *
     * @return la cantidad de propiedades cargadas
     */
    public int calcularCantidadPropiedadesCargadas() {
        return indiceProximaPropiedadParaAgregar;
    }

    /**
     * Verifica si existe una propiedad con el id especificado
     *
     * @param id identificacion con la que se quiere verificar
     * @return true en caso que exista una propiedad con ese id; false en caso
     * contrario
     */
    public boolean verificarExistenciaPropiedadPorId(int id) {
        boolean flag = false;
        for (Propiedad p : this.propiedades) {
            if (p != null && p.getId() == id) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Permite agregar un servicio indicando su código y monto a pagar.
     *
     * @param propiedad propiedad que se desea agregar al consorcio
     * @return true si la propiedad fue agregada exitosamente; false en caso
     * contrario.
     */
    public boolean agregarPropiedad(Propiedad propiedad) {
        boolean flag = false;
        if (!cantidadMaximaPropiedadesAlcanzada && !verificarExistenciaPropiedadPorId(propiedad.getId())) {
            propiedades[indiceProximaPropiedadParaAgregar] = propiedad;
            ordernarPropiedadesPorId();
            cantidadMaximaPropiedadesAlcanzada = (indiceProximaPropiedadParaAgregar == CANTIDAD_MAXIMA_PROPIEDADES);
            flag = true;
        }
        return flag;
    }

    /**
     * Permite buscar y eliminar una propiedad por id.
     *
     * @param id identificacion de propiedad que se quiere eliminar
     */
    public void eliminarPropiedadPorId(int id) {
        for (int i = 0; i < this.propiedades.length; i++) {
            if (propiedades[i] != null && propiedades[i].getId() == id) {
                propiedades[i] = null;
                ordernarPropiedadesPorId();
                break;
            }
        }
    }

    /**
     * Permite buscar y retornar una propiedad por identificacion.
     *
     * @param id identificacion por la que se desea buscar
     * @return la instancia de la propiedad buscada en caso de encontrarla, y
     * null en caso contrario
     */
    public Propiedad buscarPropiedadPorId(int id) {
        for (Propiedad p : this.propiedades) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Ordena las propiedades de menor a mayor en funcion del id y actualiza el
     * indice en donde se deberia cargar la proxima propiedad
     */
    private void ordernarPropiedadesPorId() {
        Propiedad aux;
        int i, j;
        // Ordeno las propiedades de menor a mayor
        for (i = 0; i < propiedades.length - 1; i++) {
            for (j = i + 1; j < propiedades.length; j++) {
                if (propiedades[j] != null) {
                    if (propiedades[i] == null) {
                        propiedades[i] = propiedades[j];
                        propiedades[j] = null;
                    }
                    else if (propiedades[j].getId() < propiedades[i].getId()) {
                        aux = propiedades[i];
                        propiedades[i] = propiedades[j];
                        propiedades[j] = aux;
                    }
                }
            }
        }
        // Actualizo el indice para la proxima propiedad a cargar
        for (i = 0; i < propiedades.length; i++) {
            if (propiedades[i] == null) {
                indiceProximaPropiedadParaAgregar = i;
                break;
            }
        }
    }

    /**
     * Permite buscar las propiedades que mas aportan en funcion al calculo de
     * sus expensas.
     *
     * @return las propiedades que mas aportan, si hay propiedades cargadas, y
     * null si no hay propiedades cargadas.
     */
    public Propiedad[] calcularPropiedadesQueMasAportan() {
        Propiedad[] listado = null;
        Propiedad masAporta = null;
        double pExpensas;
        double masAportaExpensas;
        String ids = "";
        for (Propiedad p : this.propiedades) {
            if (p != null) {
                if (masAporta == null) {
                    masAporta = p;
                    ids = "" + masAporta.getId();
                }
                else {
                    pExpensas = p.calcularExpensas();
                    masAportaExpensas = masAporta.calcularExpensas();
                    if (pExpensas == masAportaExpensas) {
                        ids += "," + p.getId();
                    }
                    else if (pExpensas > masAportaExpensas) {
                        masAporta = p;
                        ids = "" + masAporta.getId();
                    }
                }
            }
        }
        int cantidad = ids.split(",").length;
        if (cantidad > 0) {
            listado = new Propiedad[cantidad];
            int i = 0;
            for (String id : ids.split(",")) {
                listado[i] = buscarPropiedadPorId(Integer.parseInt(id));
                i++;
            }
        }
        return listado;
    }

    /**
     * Permite calcular el porcentaje de aportes de terrenos.
     *
     * @return el portentaje de aportes de terrenos.
     */
    public double calcularPorcentajeAportesTerrenos() {
        double sumaCubiertos = 0, sumaTerrenos = 0;
        for (Propiedad p : this.propiedades) {
            if (p != null) {
                if (p.estaConstruida()) {
                    sumaCubiertos += p.calcularExpensas();
                }
                else {
                    sumaTerrenos += p.calcularExpensas();
                }
            }
        }
        return ((sumaCubiertos == 0 && sumaTerrenos == 0) ? sumaTerrenos : (sumaTerrenos * 100.0 / (sumaTerrenos + sumaCubiertos)));
    }

    /**
     * Calcula el monto promedio por servicio para las propiedades en
     * construccion
     *
     * @return un arreglo con el promedio del monto sacado por servicio solo
     * para propiedades en construccion
     */
    public Servicio[] calcularMontoPromedioPorServicioPropiedadesEnConstruccion() {
        double suma, contador, promedio;
        Servicio[] servicios = new Servicio[Servicios.values().length];
        for (Servicios s : Servicios.values()) {
            suma = contador = promedio = 0;
            for (Propiedad p : this.getPropiedades()) {
                if (p != null && p.estaConstruida() && p.getServicios() != null && p.getServicios()[s.getValue()] != null) {
                    suma += p.getServicios()[s.getValue()].getMonto();
                    contador++;
                }
            }
            promedio = ((contador > 0) ? (suma / contador) : 0);
            servicios[s.getValue()] = new Servicio(s, promedio);
        }
        return servicios;
    }

    /**
     * Calcula el servicio mas costoso entre todas las propiedades
     * @return el servicio mas costoso entre todas las propiedades
     */
    public Servicio calcularServicioMasCostoso() {
        Servicio servicioMayorMonto = null;
        for (Propiedad propiedad : getPropiedades()) {
            if (propiedad != null) {
                for (Servicio servicio : propiedad.getServicios()) {
                    if (servicio != null) {
                        if (servicioMayorMonto == null) {
                            servicioMayorMonto = servicio;
                        }
                        else if (servicio.getMonto() > servicioMayorMonto.getMonto()) {
                            servicioMayorMonto = servicio;
                        }
                    }
                }
            }
        }
        return servicioMayorMonto;
    }

    @Override
    public String toString() {
        return String.format("Consorcio[nombre: %s]", this.getNombre());
    }
//</editor-fold>
}
