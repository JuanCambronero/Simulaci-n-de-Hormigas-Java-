import java.util.HashMap;

/**
 * Representa el terreno o tablero donde se mueven las hormigas.
 *
 * <p>El mapa es una matriz bidimensional que contiene las posiciones vacías,
 * las hormigas y el hormiguero en el centro. Esta clase ofrece métodos para
 * preparar, actualizar y mostrar el estado del mapa en consola.</p>
 *
 * <p>El tamaño del mapa es fijo (5x5) y está definido mediante constantes.
 * Cada celda se representa con un carácter, según su contenido.</p>
 *
 * @author Juan
 * @version 1.0
 */
public class Mapa {
    //Clase que representa el terreno donde se mueven las hormigas

    //CONSTANTES
    /** Ancho del mapa (número de columnas). */
    public static final int ancho = 10;
    /** Alto del mapa (número de filas). */
    public static final int alto = 10;
    /** Carácter que representa una celda vacía. */
    public static final char vacio = '-';
    /** Carácter que representa el hormiguero. */
    public static final char hormigero = 'H';

    //ATRIBUTOS
    /** Matriz que representa las posiciones del mapa. */
    private final char[][] mapa;
    /** Posición central donde se ubica el hormiguero. */
    private final Posicion hormiguero;

    //CONSTRUCTO - 0(n)
    /**
     * Crea un nuevo mapa cuadrado y coloca el hormiguero en el centro.
     *
     * <p>Complejidad: O(n)</p>
     */
    public Mapa() {
        mapa = new char[ancho][alto];   //Crear matriz con el tamaño del mapa
        for (int i = 0; i < ancho; i++) { //Rellenar el mapa con vacio
            for (int j = 0; j < alto; j++) {
                mapa[i][j] = vacio;
            }
        }

        int centroAncho = ancho / 2;//Centro de la anchura
        int centroAlto = alto / 2;//Centro del alto
        this.mapa[centroAlto][centroAncho] = hormigero;//Colocar el hormiguero en el mapa
        this.hormiguero = new Posicion(centroAncho, centroAlto);//Posicionar el hormiguero
    }

    //METODOS
    /**
     * Devuelve la posición central donde se encuentra el hormiguero.
     *
     * @return posición del hormiguero
     */
    public Posicion getHormiguero() {
        return hormiguero;
    }
    //Desde este metodo se comprueba si la posición está dentro de los límites llamando al metodo de posicion
    public boolean dentroLimites(Posicion posicion) {

        return posicion.dentroLimites(ancho, alto);
    }
    //Este metodo muestra el mapa por pantalla - O(n²)
    /**
     * Muestra el estado actual del mapa por consola.
     *
     * <p>Complejidad: O(n²)</p>
     *
     * <p>Este método es sincronizado para evitar conflictos cuando varios hilos
     * (hormigas) acceden al mapa al mismo tiempo.</p>
     */
    public synchronized void mostarMapa() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                System.out.print(this.mapa[i][j]);//Imprimir el mapa recorriendo todas las posiciones de la matriz
            }
            System.out.println();
        }

    }
    //Este metodo prepara el mapa para ser mostrado colacando cada hormiga y horigero en su posicion - O(n² + n)
    /**
     * Prepara el mapa para ser mostrado, colocando todas las hormigas
     * y el hormiguero en sus posiciones correspondientes.
     *
     * <p>Complejidad: O(n² + n)</p>
     *
     * @param hormigas mapa que asocia el ID de cada hormiga con su instancia
     */
    public void prepararMapa(HashMap<String, Hormiga> hormigas){
        //1º LLenar de vacio
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                this.mapa[i][j] = vacio;
            }
            System.out.println();
        }
        //2º Colocar las hormigas
        for (String clave : hormigas.keySet()) {
            Hormiga hormigaColocar = hormigas.get(clave);
            int x = hormigaColocar.getPosicion().getX();
            int y = hormigaColocar.getPosicion().getY();
            this.mapa[x][y] = hormigaColocar.getTipo().getSimbolo();
        }
        //3º Colocar el hormigero
        int centroAncho = ancho / 2;//Centro de la anchura
        int centroAlto = alto / 2;//Centro del alto
        this.mapa[centroAlto][centroAncho] = hormigero;//Colocar el hormiguero en el mapa
    }
}
