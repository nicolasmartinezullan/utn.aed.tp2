package utn.aed.tp2;

/**
 * Trabajo Practico 2 - Administracion de consorcios
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014- 1K9
 */
public class Enums {
    public enum Servicios {
        CABLE(0), INTERNET(1), TELEFONIA(2), JARDINERIA(3), LIMPIEZA_PILETAS(4);
        private final int value;
        private Servicios(int value){this.value = value;}
        public int getValue(){return value;}
        @Override
        public String toString(){
            switch (value) {
                case 0:
                    return "Cable";
                case 1:
                    return "Internet";
                case 2:
                    return "Telefonia";
                case 3:
                    return "Jardineria";
                default:
                    return "Limpieza Piletas";
            }
        }
    }
}
