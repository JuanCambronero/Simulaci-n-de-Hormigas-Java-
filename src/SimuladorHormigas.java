import java.util.HashMap;
import java.util.Random;

public class SimuladorHormigas {
    //ESTATICOS
    public static final int numObreras = 3;
    public static final int intervaloActualizacion = 1000;
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
    //Metodo para generar hormigas obreras
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


    public void ejecutar(){
        simulacionActiva = true;

        generarObreras();

        for (Hormiga hormiga : hormigas.values()) {
            hormiga.start();
        }

        actualizarVisualizacion();
    }

    public void detenerSimulacion(){
        simulacionActiva = false;
        for (Hormiga hormiga : hormigas.values()) {
            hormiga.detener();
        }
    }

    public void actualizarVisualizacion(){
        while (simulacionActiva){
            try {
                limpiarConsola();
                moverHormigaAleatoriamente(hormigas.get(hormigas.keySet().iterator().next()));
                mapa.prepararMapa(hormigas);
                mapa.mostarMapa();
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

    private void limpiarConsola(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}