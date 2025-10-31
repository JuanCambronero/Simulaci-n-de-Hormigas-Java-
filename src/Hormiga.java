import java.util.Random;
/**
 * Clase abstracta que representa una hormiga dentro del sistema.
 * Cada hormiga se ejecuta en un hilo independiente y puede moverse
 * aleatoriamente dentro de los límites del mapa.
 *
 * <p>Esta clase sirve como base para distintos tipos de hormigas
 * que extenderán su comportamiento.</p>
 *
 * @author Juan
 * @version 1.0
 */

public abstract class Hormiga extends Thread {
    //ATRIBUTOS
    /** Identificador único de la hormiga. */
    protected String idHormiga;
    /** Tipo de hormiga (obrera, soldado, reina)*/
    protected TipoHormiga tipo;
    /** Posición actual de la hormiga en el mapa. */
    protected Posicion posicion;
    /** Indica si el hilo de la hormiga está activo. */
    protected boolean activo;
    /** Objeto para generar movimientos aleatorios. */
    protected Random random;
    /** Direcciones posibles de movimiento (arriba, abajo, derecha, izquierda). */
    protected int [][] direcciones;


    //CONSTRUCTOR
    /**
     * Constructor que inicializa los atributos principales de la hormiga.
     *
     * @param idHormiga identificador único de la hormiga
     * @param tipo tipo de hormiga (enum TipoHormiga)
     * @param posicion posición inicial de la hormiga
     */
    public Hormiga(String idHormiga, TipoHormiga tipo, Posicion posicion) {
        this.idHormiga = idHormiga;
        this.tipo = tipo;
        this.posicion = posicion;
        this.activo = true;
        this.random = new Random();
        this.direcciones = new int[][]{
                {-1, 0}, {1, 0}, {0, 1}, {0, -1}
        };
    }


    //METODOS
    /**
     * Obtiene el id de la hormiga.
     *
     * @return id de la hormiga
     */
    public String getIdHormiga() {
        return idHormiga;
    }
    /**
     * Devuelve el tipo de hormiga.
     *
     * @return tipo de la hormiga
     */
    public TipoHormiga getTipo() {
        return tipo;
    }
    /**
     * Obtiene la posición actual de la hormiga.
     *
     * @return posición de la hormiga
     */
    public Posicion getPosicion() {
        return posicion;
    }
    /**
     * Actualiza la posición de la hormiga.
     *
     * @param nuevaPosicion nueva posición a asignar
     */
    public void setPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }
    /**
     * Comprueba si el hilo de la hormiga está activo.
     *
     * @return true si está activo, false en caso contrario
     */
    public boolean isActivo() {
        return activo;
    }
    /**
     * Detiene la ejecución del hilo de la hormiga.
     */
    public void detener(){
        activo = false;
    }
    /**
     * Comprueba si una posición coincide con la posición del hormiguero.
     *
     * @param pos posición a verificar
     * @param hormiguero posición del hormiguero
     * @return true si es la misma posición, false en caso contrario
     */
    private boolean esHormiguero(Posicion pos, Posicion hormiguero) {
        return pos.getX() == hormiguero.getX() && pos.getY() == hormiguero.getY();
    }
    /**
     * Método principal del hilo que controla el movimiento de la hormiga.
     * La hormiga se mueve aleatoriamente dentro del mapa y evita entrar en el hormiguero.
     * Se pausa brevemente entre cada movimiento.
     */
    @Override
    public void run() {
        while (isActivo()) {
            try {
                // Mover aleatoriamente
                int direccion = random.nextInt(direcciones.length);
                int dx = direcciones[direccion][0];
                int dy = direcciones[direccion][1];
                Posicion nuevaPos = posicion.mover(dx, dy);

                Posicion hormiguero = new Posicion(Mapa.ancho/2, Mapa.alto/2);
                // Verifica límites antes de moverse
                if (nuevaPos.dentroLimites(Mapa.ancho, Mapa.alto) && !esHormiguero(nuevaPos, hormiguero)) {
                    setPosicion(nuevaPos);
                }

                // Espera un poco antes del siguiente movimiento
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                detener();
            }
        }
    }
}
