import java.util.HashMap;
import java.util.Random;
/**
 * <h1>SimuladorHormigas</h1>
 * Esta clase gestiona la lógica principal de una simulación de hormigas obreras
 * dentro de un mapa. Controla su creación, movimiento, visualización y estadísticas.
 *
 * <p>El sistema se ejecuta en consola y simula el comportamiento básico de las hormigas
 * moviéndose de forma aleatoria dentro de los límites del mapa.</p>
 *
 * <p><b>Autor:</b> Juan Cambronero</p>
 * <p><b>Versión:</b> 1.0</p>
 */
public class SimuladorHormigas {
    //ESTATICOS
    /** Número de hormigas obreras generadas por la simulación */
    public static final int numObreras = 3;
    /** Intervalo de actualización visual (en milisegundos) */
    public static final int intervaloActualizacion = 2000;
    /** Posibles direcciones de movimiento (arriba, abajo, derecha, izquierda) */
    private static final int[][] direcciones = {
            {-1,0},{1,0},{0,1},{0,-1}
    };
    /** Generador de números aleatorios */
    private final Random random;

    //ATRIBUTOS
    /** Mapa donde se ejecuta la simulación */
    private Mapa mapa;
    /** Conjunto de hormigas activas en la simulación */
    private HashMap<String, Hormiga> hormigas;
    /** Indica si la simulación está activa */
    private volatile boolean simulacionActiva;

    //CONSTRUCTORES
    /**
     * Constructor principal de la clase SimuladorHormigas.
     *
     * @param random             Objeto Random para generar posiciones aleatorias.
     * @param mapa               Objeto Mapa donde se desarrollará la simulación.
     * @param hormigas           Colección inicial de hormigas.
     * @param simulacionActiva   Estado inicial de la simulación.
     */
    public SimuladorHormigas(Random random, Mapa mapa, HashMap<String, Hormiga> hormigas, boolean simulacionActiva) {
        this.random = new Random();
        this.mapa = new Mapa();
        this.hormigas = new HashMap<>();
        this.simulacionActiva = false;
    }

    //METODOS
    //Metodo para generar hormigas obreras - 0(n) n = numero de obreras
    /**
     * Genera las hormigas obreras iniciales en posiciones aleatorias dentro del mapa.
     * <p>Complejidad: O(n), donde n es el número de obreras.</p>
     */
    public void generarObreras() {
        for (int i = 0; i < numObreras; i++) {
            Posicion pos;
            do {
                int x = random.nextInt(5);
                int y = random.nextInt(5);
                pos = new Posicion(x, y);
            } while (!mapa.dentroLimites(pos) && mapa.getHormiguero() == (pos));

            Hormiga hormigaNueva = new HormigaObrera("O" + i, TipoHormiga.OBRERA, pos);
            hormigas.put(hormigaNueva.getIdHormiga(), hormigaNueva);
        }

        mapa.prepararMapa(hormigas);
    }

    //Metodo para ejecutra la simulación - 0(n) n = numero de hormigas
    /**
     * Ejecuta la simulación de las hormigas.
     * <p>Inicia las hebras de las hormigas y actualiza la vista en consola.</p>
     */
    public void ejecutar(){
        simulacionActiva = true;
        generarObreras();
        for (Hormiga hormiga : hormigas.values()) {
            hormiga.start();
        }
        actualizarVisualizacion();

    }
    //Metodo para detener la simulación - 0(n) n = numero de hormigas
    /**
     * Detiene la simulación en curso.
     * <p>Complejidad: O(n), donde n es el número de hormigas.</p>
     */
    public void detenerSimulacion(){
        simulacionActiva = false;
        for (Hormiga hormiga : hormigas.values()) {
            hormiga.detener();
        }
    }
    //En construcción
    //Método para mostrar estadísticas básicas - O(n)
    /**
     * Muestra estadísticas básicas de la simulación (hormigas activas, hormiguero, etc.).
     */
    public void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS ---");

        int activas = 0;
        for (Hormiga hormiga : hormigas.values()) {
            if (hormiga.isActivo()) {
                activas++;
            }
        }

        System.out.println("Hormigas activas: " + activas + "/" + hormigas.size());
        System.out.println("Hormiguero: " + mapa.getHormiguero());
        System.out.println("Tiempo de actualización: " + intervaloActualizacion);
        System.out.println("--------------------\n");
    }
    //Metodo para actualizar la vitsa de la simulación - 0(n) n = número de hormigas
    /**
     * Actualiza continuamente la visualización del mapa y las estadísticas
     * mientras la simulación esté activa.
     */
    public void actualizarVisualizacion(){
        while (simulacionActiva){
            try {
                limpiarConsola();
                mapa.prepararMapa(hormigas);
                mapa.mostarMapa();
                mostrarEstadisticas();
                Thread.sleep(intervaloActualizacion);
            } catch (InterruptedException e) {
                simulacionActiva = false;
            }
        }
    }

    /**
     * Este metodo mueve de forma aleatoria cada hormiga en las direcciones posibles
     * @param hormiga Hormiga a mover.
     */
    //Metodo para mover a las hormigas por el mapa - 0(1) ¿Metodo rebundante?
    private void moverHormigaAleatoriamente(Hormiga hormiga) {
        int[] direccion = direcciones[random.nextInt(direcciones.length)];
        Posicion nueva = hormiga.getPosicion().mover(direccion[0], direccion[1]);

        // Verifica que esté dentro del mapa
        if (mapa.dentroLimites(nueva)) {
            Posicion hormigero = mapa.getHormiguero();
            if(hormigero != nueva){
                hormiga.setPosicion(nueva);
            }

        }
    }
    //Metodo para limpiar la vista entre simulaciones - 0(1)
    /**
     * Limpia la consola entre actualizaciones de la simulación para mejorar la legibilidad.
     */
    private void limpiarConsola(){
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }
}