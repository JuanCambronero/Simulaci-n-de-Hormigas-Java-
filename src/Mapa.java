public class Mapa {
    //Clase que representa el terreno donde se mueven las hormigas

    //CONSTANTES
    public static final int ancho = 5;
    public static final int alto = 5;
    public static final char vacio = '-';
    public static final char hormigero = 'H';

    //ATRIBUTOS
    private final char[][] mapa;
    private final Posicion hormiguero;

    //CONSTRUCTO
    public Mapa() {
        mapa = new char[ancho][alto];   //Crear matriz con el tamaño del mapa
        for (int i = 0; i < ancho; i++) { //Rellenar el mapa con vacio
            for (int j = 0; j < alto; j++) {
                mapa[i][j] = vacio;
            }
        }

        int centroAncho = ancho / 2;//Centro de la anchura
        int centroAlto = alto / 2;//Centro del alto
        mapa[centroAlto][centroAncho] = hormigero;//Colocar el hormiguero en el mapa
        hormiguero = new Posicion(centroAncho, centroAlto);//Posicionar el hormiguero
    }

    //METODOS
    public Posicion getHormiguero() {
        return hormiguero;
    }

    public boolean dentroLimites(Posicion posicion) {
        return posicion.dentroLimites(ancho, alto);
    }

    public synchronized void mostarMapa() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                System.out.print(mapa[i][j]);//Imprimir el mapa recorriendo todas las posiciones de la matriz
            }
            System.out.println();
        }
    }

    //public void prepararMapa(HashMap<String, Hormiga> hormigas) para continuar pasar al bloque de las hormigas

}
