package utn.aed.tp2;
import utn.aed.tp2.Enums.Servicios;
/**
 * Trabajo Practico 2 - Administracion de consorcios
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Menu {
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Menu() {
        limpiarPantalla();
        imprimirMarco();
        System.out.print("\nNombre de consorcio (maximo 20 caracteres): ");
        String nombreConsorcio = Consola.readLine().trim();
        while (nombreConsorcio.equals("")) {
            System.out.print("Debe ingresar un nombre: ");
            nombreConsorcio = Consola.readLine().trim();
        }
        if (nombreConsorcio.length() > CONSORCIO_NOMBRE_MAX) {
            nombreConsorcio = nombreConsorcio.substring(0, CONSORCIO_NOMBRE_MAX);
        }
        this.consorcio = new Consorcio(nombreConsorcio.toUpperCase());
        this.consorcio.agregarPropiedad(new Propiedad(1, "Carlos", 11, 10, new Servicio[]{new Servicio(Servicios.CABLE, 100), new Servicio(Servicios.TELEFONIA, 30)}));
        this.consorcio.agregarPropiedad(new Propiedad(2, "Claudia", 22, 0, new Servicio[]{new Servicio(Servicios.JARDINERIA, 20), new Servicio(Servicios.TELEFONIA, 15)}));
        this.consorcio.agregarPropiedad(new Propiedad(3, "Miguel", 55, 0, new Servicio[]{new Servicio(Servicios.LIMPIEZA_PILETAS, 30), new Servicio(Servicios.INTERNET, 67)}));
        this.consorcio.agregarPropiedad(new Propiedad(4, "Sabrina", 87, 88, new Servicio[]{new Servicio(Servicios.TELEFONIA, 66), new Servicio(Servicios.INTERNET, 85)}));
        this.consorcio.agregarPropiedad(new Propiedad(44, "Lorena", 87, 88, new Servicio[]{new Servicio(Servicios.TELEFONIA, 66), new Servicio(Servicios.INTERNET, 85)}));
        this.consorcio.agregarPropiedad(new Propiedad(5, "Alvar", 47, 0, new Servicio[]{new Servicio(Servicios.CABLE, 44), new Servicio(Servicios.INTERNET, 43)}));
        this.consorcio.agregarPropiedad(new Propiedad(6, "Micaela", 34, 0, new Servicio[]{new Servicio(Servicios.INTERNET, 99), new Servicio(Servicios.INTERNET, 22)}));
        this.consorcio.agregarPropiedad(new Propiedad(7, "Juan", 488, 23, new Servicio[]{new Servicio(Servicios.CABLE, 66), new Servicio(Servicios.JARDINERIA, 120)}));
        this.consorcio.agregarPropiedad(new Propiedad(77, "Jesica", 488, 23, new Servicio[]{new Servicio(Servicios.CABLE, 66), new Servicio(Servicios.JARDINERIA, 120)}));
        this.consorcio.agregarPropiedad(new Propiedad(8, "Iara", 99, 10, new Servicio[]{new Servicio(Servicios.CABLE, 10), new Servicio(Servicios.LIMPIEZA_PILETAS, 30)}));
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private final int MENU_PRINCIPAL_AGREGAR_PROPIEDAD = 1;
    private final int MENU_PRINCIPAL_MOSTRAR_O_AGREGAR_PROPIEDAD = 2;
    private final int MENU_PRINCIPAL_ELIMINAR_PROPIEDAD = 3;
    private final int MENU_PRINCIPAL_EMITIR_LISTADO_EXPENSAS = 4;
    private final int MENU_PRINCIPAL_PROPIEDADES_QUE_MAS_PAGAN = 5;
    private final int MENU_PRINCIPAL_APORTE_TERRENOS = 6;
    private final int MENU_PRINCIPAL_PAGO_PROMEDIO_EXTRAS = 7;
    private final int MENU_PRINCIPAL_PUNTO_BONUS = 8;
    private final int MENU_PRINCIPAL_SALIR = 9;
    private final int TABLA_COLUMNA_ANCHO = 15;
    private final String TABLA_ENCABEZADO_SEPARADOR = "--------------------------------------------------------------------";
    private final char TABLA_COLUMNA_SEPARADOR = '|';
    private final int DETALLES_ANCHO_COLUMNA = 25;
    private final int CONSORCIO_NOMBRE_MAX = 20;
    private final int PROPIEDAD_NOMBRE_MAX = 30;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private final Consorcio consorcio;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public void ejecutar() {
        int opcion;
        do {
            opcion = imprimirMenuPrincipal();
            procesarMenuPrincipal(opcion);
        } while (opcion != MENU_PRINCIPAL_SALIR);
    }

    private int verificarIdIngresada(int id) {
        while (id <= 0) {
            System.out.print("Ingrese un numero positivo: ");
            id = Consola.readInt();
        }
        return id;
    }

    private void procesarMenuPrincipal(int opcion) {
        int id;
        Propiedad p;
        limpiarPantalla();
        switch (opcion) {
            case MENU_PRINCIPAL_AGREGAR_PROPIEDAD: {
                boolean idAprobada = false;
                do {
                    imprimirTitulo("Agregar Propiedad");
                    System.out.print("Identificacion: ");
                    id = verificarIdIngresada(Consola.readInt());
                    if (consorcio.buscarPropiedadPorId(id) == null) {
                        idAprobada = true;
                    }
                    else {
                        System.out.println("Identificacion en uso. Escriba una distinta.");
                        imprimirPresioneEnterParaContinuar();
                        Consola.readLine();
                        limpiarPantalla();
                    }
                } while (!idAprobada);
                imprimirAgregarPropiedad(id);
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_MOSTRAR_O_AGREGAR_PROPIEDAD: {
                imprimirTitulo("Mostrar o Agregar Propiedad");
                System.out.print("Identificacion: ");
                id = verificarIdIngresada(Consola.readInt());
                p = this.consorcio.buscarPropiedadPorId(id);
                if (p == null) {
                    // Agregar propiedad
                    System.out.println("Numero de identificacion disponible. Cargue el resto de los datos.");
                    imprimirAgregarPropiedad(id);
                }
                else {
                    // Mostrar detalles propiedad
                    limpiarPantalla();
                    imprimirTitulo("Detalles Propiedad");
                    imprimirDetallesPropiedad(p);
                    imprimirPresioneEnterParaContinuar();
                }
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_ELIMINAR_PROPIEDAD: {
                imprimirTitulo("Eliminar Propiedad");
                System.out.print("Identificacion: ");
                id = verificarIdIngresada(Consola.readInt());
                if (consorcio.buscarPropiedadPorId(id) == null) {
                    System.out.println("No existe una propiedad vinculada al numero de identificacion ingresado.");
                    imprimirPresioneEnterParaContinuar();
                    Consola.readLine();
                    break;
                }
                this.consorcio.eliminarPropiedadPorId(id);
                System.out.println("Propiedad eliminada.");
                imprimirPresioneEnterParaContinuar();
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_EMITIR_LISTADO_EXPENSAS: {
                imprimirTitulo("Listado Expensas");
                StringBuilder tabla = new StringBuilder();
                double totalExpensas = 0;
                double expensas;
                Propiedad[] propiedades = this.consorcio.getPropiedades();
                for (Propiedad a : propiedades) {
                    if (a != null) {
                        expensas = a.calcularExpensas();
                        totalExpensas += expensas;
                        Object[] columnas = {a.getId(), a.getPropietario(), String.format("$ %.2f", expensas)};
                        tabla.append(armarFilaTabla(columnas));
                    }
                }
                if (tabla.toString().equals("")) {
                    System.out.println("No se encuentran propiedades cargadas en el sistema.");
                    imprimirPresioneEnterParaContinuar();
                }
                else {
                    // Agregar encabezados a tabla
                    String filaEncabezados = armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR});
                    filaEncabezados += armarFilaTabla(new Object[]{"ID", "PROPIETARIO", "EXPENSAS"});
                    filaEncabezados += armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR});
                    tabla.insert(0, filaEncabezados);
                    tabla.insert(tabla.length(), armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR}));
                    System.out.println("Consorcio " + consorcio.getNombre());
                    System.out.print(tabla);
                    System.out.println(String.format("Total expensas: $ %.2f", totalExpensas));
                    imprimirPresioneEnterParaContinuar();
                }
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_PROPIEDADES_QUE_MAS_PAGAN: {
                imprimirTitulo("Propiedades Que Mas Pagan");
                if (consorcio.calcularCantidadPropiedadesCargadas() == 0) {
                    System.out.println("No se encuentran propiedades cargadas en el sistema.");
                }
                else {
                    String tabla = armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR});
                    tabla += armarFilaTabla(new Object[]{"ID", "PROPIETARIO", "EXPENSAS"});
                    tabla += armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR});
                    for (Propiedad a : consorcio.calcularPropiedadesQueMasAportan()) {
                        tabla += armarFilaTabla(new Object[]{a.getId(), a.getPropietario(), a.calcularExpensas()});
                    }
                    tabla += armarFilaTabla(new Object[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR});
                    System.out.print(tabla);
                }
                imprimirPresioneEnterParaContinuar();
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_APORTE_TERRENOS: {
                imprimirTitulo("Aporte de Terrenos");
                System.out.println(String.format("Porcentaje de aporte de terrenos: %.2f%%", consorcio.calcularPorcentajeAportesTerrenos()));
                imprimirPresioneEnterParaContinuar();
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_PAGO_PROMEDIO_EXTRAS: {
                // Monto promedio por servicio que abonan las propiedades con construccion.
                imprimirTitulo("Pago Promedio Extras");
                System.out.println("Monto promedio por servicio que abonan las propiedades con construccion");
                for (Servicio s : consorcio.calcularMontoPromedioPorServicioPropiedadesEnConstruccion()) {
                    System.out.println(String.format(rellenarDerecha(s.getTipo() + ":", DETALLES_ANCHO_COLUMNA, ' ') + "$ %.2f", s.getMonto()));
                }
                imprimirPresioneEnterParaContinuar();
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_PUNTO_BONUS: {
                // Mayor monto pagado por un extra entre todas las propiedades
                imprimirTitulo("Punto Bonus");
                Servicio servicioMayorMonto = consorcio.calcularServicioMasCostoso();
                if (servicioMayorMonto == null) {
                    System.out.println("Ninguna propiedad tiene cargado servicios.");
                }
                else {
                    System.out.println(String.format("Servicio\n\t%s\n\tMonto: $ %.2f", servicioMayorMonto.getTipo(), servicioMayorMonto.getMonto()));
                }
                imprimirPresioneEnterParaContinuar();
                Consola.readLine();
                break;
            }
            case MENU_PRINCIPAL_SALIR: {
                // Do nothing...
                break;
            }
            default: {
                System.out.println("-------------------------------------------------");
                System.out.println("|         Elija una opcion correcta.            |");
                System.out.println("|         Presione ENTER para continuar.        |");
                System.out.println("-------------------------------------------------");
                Consola.readLine();
            }
        }
    }

    private String armarFilaTabla(Object[] columnas) {
        StringBuilder fila = new StringBuilder();
        fila.append(TABLA_COLUMNA_SEPARADOR);
        for (Object c : columnas) {
            fila.append(armarColumnaTabla(c));
            fila.append(TABLA_COLUMNA_SEPARADOR);
        }
        fila.append("\n");
        return fila.toString();
    }

    private String armarColumnaTabla(Object contenido) {
        String linea = " ";
        linea += contenido.toString().trim();
        int caracteresLibres = TABLA_COLUMNA_ANCHO - linea.length();
        if (caracteresLibres > 1) {
            for (int i = 0; i < caracteresLibres - 1; i++) {
                linea += " ";
            }
        }
        else if (caracteresLibres != 1) {
            linea = linea.substring(0, TABLA_COLUMNA_ANCHO - 1);
        }
        linea += " ";
        return linea;
    }

    private int imprimirMenuPrincipal() {
        limpiarPantalla();
        imprimirTitulo("Menu Principal");
        System.out.println(MENU_PRINCIPAL_AGREGAR_PROPIEDAD + ". Agregar Propiedad");
        System.out.println(MENU_PRINCIPAL_MOSTRAR_O_AGREGAR_PROPIEDAD + ". Buscar y mostrar o agregar propiedad");
        System.out.println(MENU_PRINCIPAL_ELIMINAR_PROPIEDAD + ". Eliminar propiedad");
        System.out.println(MENU_PRINCIPAL_EMITIR_LISTADO_EXPENSAS + ". Emitir listado de expensas");
        System.out.println(MENU_PRINCIPAL_PROPIEDADES_QUE_MAS_PAGAN + ". Propiedades que mas pagan");
        System.out.println(MENU_PRINCIPAL_APORTE_TERRENOS + ". Aporte de Terrenos");
        System.out.println(MENU_PRINCIPAL_PAGO_PROMEDIO_EXTRAS + ". Pago promedio de extras");
        System.out.println(MENU_PRINCIPAL_PUNTO_BONUS + ". Punto Bonus");
        System.out.println(MENU_PRINCIPAL_SALIR + ". Salir");
        System.out.print("Opcion: ");
        return Consola.readInt();
    }

    private void imprimirAgregarPropiedad(int id) {
        System.out.print("Nombre propietario (maximo " + PROPIEDAD_NOMBRE_MAX + " caracteres): ");
        String propietario = Consola.readLine().trim();
        while (propietario.equals("")) {
            System.out.print("Debe ingresar un nombre: ");
            propietario = Consola.readLine().trim();
        }
        if (propietario.length() > PROPIEDAD_NOMBRE_MAX) {
            propietario = propietario.substring(0, PROPIEDAD_NOMBRE_MAX);
        }
        System.out.print("Metros terreno: ");
        int metrosTerreno = Consola.readInt();
        while (metrosTerreno <= 0) {
            System.out.print("Ingrese un numero positivo: ");
            metrosTerreno = Consola.readInt();
        }
        System.out.print("Metros cubiertos: ");
        int metrosCubiertos = Consola.readInt();
        while (metrosCubiertos < 0) {
            System.out.print("Ingrese un numero mayor o igual a cero: ");
            metrosCubiertos = Consola.readInt();
        }
        int opcion;
        double monto;
        Servicio[] servicios = new Servicio[Servicios.values().length];
        Servicio servicio;
        for (Servicios s : Servicios.values()) {
            System.out.print("Servicio " + s + " [(1) Agregar   (2) No Agregar]: ");
            opcion = Consola.readInt();
            while (opcion != 1 && opcion != 2) {
                System.out.print("Escriba una opcion correcta (1 o 2): ");
                opcion = Consola.readInt();
            }
            if (opcion == 1) {
                System.out.print(String.format("Monto %s ($): ", s));
                monto = Consola.readDouble();
                while (monto <= 0) {
                    System.out.print("Ingrese un monto positivo: ");
                    monto = Consola.readInt();
                }
                servicio = new Servicio(s, monto);
                servicios[s.getValue()] = servicio;
            }
        }
        if (this.consorcio.agregarPropiedad(new Propiedad(id, propietario, metrosTerreno, metrosCubiertos, servicios))) {
            System.out.println("\nPropiedad con identificacion [" + id + "] creada exitosamente.");
        }
        else {
            System.out.println("No se pudo agregar la propiedad. Probablemente debido a que se haya alcanzado el maximo de propiedades a cargar.");
        }
        imprimirPresioneEnterParaContinuar();
    }

    private String rellenarDerecha(String texto, int longitud, char caracter) {
        for (int i = 0; i < longitud; i++) {
            texto += caracter;
        }
        return texto.substring(0, longitud);
    }

    private void imprimirDetallesPropiedad(Propiedad p) {
        System.out.println(rellenarDerecha("Identificacion:", DETALLES_ANCHO_COLUMNA, ' ') + p.getId());
        System.out.println(rellenarDerecha("Propietario:", DETALLES_ANCHO_COLUMNA, ' ') + p.getPropietario());
        System.out.println(rellenarDerecha("Metros terreno:", DETALLES_ANCHO_COLUMNA, ' ') + p.getMetrosTerreno());
        System.out.println(rellenarDerecha("Metros cubiertos:", DETALLES_ANCHO_COLUMNA, ' ') + p.getMetrosCubiertos());
        boolean tituloServiciosImpreso = false;
        for (Servicio s : p.getServicios()) {
            if (s != null) {
                if (!tituloServiciosImpreso) {
                    System.out.println("Servicios");
                    tituloServiciosImpreso = true;
                }
                System.out.println(rellenarDerecha("  " + s.getTipo() + ":", DETALLES_ANCHO_COLUMNA, ' ') + "$ " + s.getMonto());
            }
        }
        System.out.println(rellenarDerecha("Expensas:", DETALLES_ANCHO_COLUMNA, ' ') + String.format("$ %.2f", p.calcularExpensas()));
        System.out.println(rellenarDerecha("Promedio servicios:", DETALLES_ANCHO_COLUMNA, ' ') + String.format("$ %.2f", p.calcularPromedioServicios()));
        System.out.println(rellenarDerecha("Sumatoria servicios:", DETALLES_ANCHO_COLUMNA, ' ') + String.format("$ %.2f", p.calcularSumatoriaServiciosExtras()));
    }

    private void imprimirMarco() {
        System.out.println("=============================================================================");
        System.out.println("=                       ADMINISTRACION DE CONSORCIOS                        =");
        System.out.println("=                        Te manejo el complejo S.A.                         =");
        System.out.println("=============================================================================");
    }

    private void imprimirTitulo(String titulo) {
        imprimirMarco();
        System.out.println(String.format("Consorcio %s", consorcio.getNombre()));
        System.out.println("");
        if (!titulo.trim().equals("")) {
            System.out.println("" + titulo.trim().toUpperCase() + "\n");
        }
    }

    private void imprimirPresioneEnterParaContinuar() {
        System.out.println("\nPresione ENTER para continuar.");
    }

    private void limpiarPantalla() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    //</editor-fold>
}
