import java.util.HashMap;
import java.util.Random;

public class SimuladorHormigas {
    //ESTATICOS
    public static final int numObreras = 5;
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
        this.random = random;
        this.mapa = mapa;
        this.hormigas = hormigas;
        this.simulacionActiva = simulacionActiva;
    }

    //METODOS
    //Metodo para generar hormigas obreras
    public void generarObreras(){
        for (int i = 0; i < numObreras; i++) {
            int x;
            int y;
            //Comprobar si la hormiga esta dentro de los limites del mapa hasta que se genere dentro de el
            do {
                x = random.nextInt(5);
                y = random.nextInt(5);
            } while (!mapa.dentroLimites(new Posicion(x,y)));

            //Crear el objeto de la hormiga
            Hormiga hormigaNueva = new HormigaObrera("O"+i, TipoHormiga.OBRERA, new Posicion(x,y));
            //Añadir la hormiga nueva al diccionario(HashMap)
            hormigas.put(hormigaNueva.getIdHormiga(), hormigaNueva);
        }
        //Preparar el mapa con todas las hormigas
        mapa.prepararMapa(hormigas);
    }

    public void ejecutar(){
        generarObreras();
        simulacionActiva = true;


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
                mapa.prepararMapa(hormigas);
                mapa.mostarMapa();
                Thread.sleep(intervaloActualizacion);
            } catch (InterruptedException e) {
                simulacionActiva = false;
            }
        }
    }

    private void moverHormigaAleatoriamente(Hormiga hormiga) {
        int[] direccion = direcciones[random.nextInt(direcciones.length)];
        Posicion nueva = hormiga.getPosicion().mover(direccion[0], direccion[1]);

        // Verifica que esté dentro del mapa
        if (mapa.dentroLimites(nueva)) {
            hormiga.setPosicion(nueva);
        }
    }

    private synchronized void moverHormigaTodasLasHormigas(){
        for(Hormiga hormiga : hormigas.values()){
            if(hormiga.isActivo()){
                moverHormigaAleatoriamente(hormiga);
            }
        }
    }

    private void limpiarConsola(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}