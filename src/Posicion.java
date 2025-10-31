/**
 * Representa una posición inmutable (x, y) dentro del mapa.
 *
 * <p>Esta clase se utiliza para definir la ubicación de las hormigas
 * y del hormiguero en el mapa. Permite crear nuevas posiciones
 * simulando movimientos y comprobar si se encuentran dentro de los límites.</p>
 *
 * <p>Todos los métodos tienen complejidad constante O(1).</p>
 *
 * @author Juan
 * @version 1.0
 */
public class Posicion {
    //Clase inmutable que representa una posición (x, y) en el mapa
    //Permite crear nuevas posiciones simulando movimientos y verificar si están dentro de los límites

    //ATRIBUTOS

    /** Coordenada horizontal (eje X). */
    private final int x;    //Posición horizontal
    /** Coordenada vertical (eje Y). */
    private final int y;    //Posición Vertical

    //CONSTRUCTOR
    /**
     * Crea una nueva posición con las coordenadas especificadas.
     *
     * @param x valor en el eje horizontal
     * @param y valor en el eje vertical
     */
    public Posicion(int x, int y) { //Asignar los valores de la posición
        this.x = x;
        this.y = y;
    }

    //METODOS
    //Este metodo captura el valor del eje horizontal
    /**
     * Devuelve el valor de la coordenada X.
     *
     * @return valor del eje X
     */
    public int getX() {
        return x;
    }
    //Este metodo captura el valor del eje vertical
    /**
     * Devuelve el valor de la coordenada Y.
     *
     * @return valor del eje Y
     */
    public int getY() {
        return y;
    }
    //Este metodo contiene la logica que válida que la posición está dentro de límites - O(1)
    /**
     * Comprueba si la posición está dentro de los límites del mapa.
     *
     * @param maxX valor máximo permitido en el eje X
     * @param maxY valor máximo permitido en el eje Y
     * @return true si la posición está dentro de los límites, false en caso contrario
     */
    public boolean dentroLimites(int maxX, int maxY) {
        return (x >= 0 && x < maxX) && (y >= 0 && y < maxY);
    }
    //Este metodo simula el movimiento de la hormiga -O(1)
    /**
     * Devuelve una nueva posición desplazada según los valores indicados.
     *
     * <p>No modifica la posición actual, ya que la clase es inmutable.</p>
     *
     * @param deltaX desplazamiento en el eje X
     * @param deltaY desplazamiento en el eje Y
     * @return nueva instancia de {@code Posicion} con las coordenadas actualizadas
     */
    public Posicion mover(int deltaX, int deltaY) {
        return new Posicion(x + deltaX, y + deltaY);//Crea la nueva posición a donde se mueve la hormiga sumando la posicion actual de ella con la del movimiento
    }
    /**
     * Devuelve una representación en texto de la posición,
     * mostrando las coordenadas entre paréntesis.
     *
     * @return cadena con formato "(x, y)"
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
