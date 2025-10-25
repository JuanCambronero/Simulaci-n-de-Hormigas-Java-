import java.util.HashMap;
import java.util.Random;

public class SimuladorHormigas {
    //ESTATICOS
    public static final int numObreras = 3;
    public static final int intervaloActualizacion = 2000;
    private static final int[][] direcciones = {
            {-1,0},{1,0},{0,1},{0,-1}
    };
    private final Random random;

    //ATRIBUTOS
    private Mapa mapa;
    private HashMap<String, Hormiga> hormigas;
    private volatile boolean simulacionActiva;

    //CONSTRUCTORES
    public SimuladorHormigas(Random random, Mapa mapa, HashMap<String, Hormiga> hormigas, boolean simulacionActiva) {
        this.random = new Random();
        this.mapa = new Mapa();
        this.hormigas = new HashMap<>();
        this.simulacionActiva = false;
    }

    //METODOS
    //Metodo para generar hormigas obreras - 0(n) n = numero de obreras
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
    public void ejecutar(){
        simulacionActiva = true;
        generarObreras();
        for (Hormiga hormiga : hormigas.values()) {
            hormiga.start();
        }
        actualizarVisualizacion();

    }
    //Metodo para detener la simulación - 0(n) n = numero de hormigas
    public void detenerSimulacion(){
        simulacionActiva = false;
        for (Hormiga hormiga : hormigas.values()) {
            hormiga.detener();
        }
    }
    //En construcción
    //Método para mostrar estadísticas básicas - O(n)
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
     * Este metodo mueve de forma aleatoria cada hormiga
     * @param hormiga
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

//    private synchronized void moverHormigaTodasLasHormigas(){
//        for(Hormiga hormiga : hormigas.values()){
//            if(hormiga.isActivo()){
//                moverHormigaAleatoriamente(hormiga);
//            }
//        }
//    }
    //Metodo para limpiar la vista entre simulaciones - 0(1)
    private void limpiarConsola(){
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }
}