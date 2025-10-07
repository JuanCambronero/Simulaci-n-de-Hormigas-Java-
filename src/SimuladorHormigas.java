import java.util.HashMap;
import java.util.Random;

public class SimuladorHormigas {
    //ESTATICOS
    public static final int numObreras = 5;
    public static final int intervaloActualizcion = 300;
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
        this.simulacionActiva = false;
    }


    //METODOS
    //Metodo para genererar hormigas obreras -
    public void generarObreras(){
        for (int i = 0; i < numObreras; i++) {
            int x ;
            int y ;
            //Comprobar si la hormiga esta dentro de los limites del mapa hasta que se genere dentro de el
            do {
                x = random.nextInt(5);
                y = random.nextInt(5);
            } while (!mapa.dentroLimites(new Posicion(x,y)));
            //Crear el objeto de la hormiga
            Hormiga hormigaNueva = new HormigaObrera("O"+i,TipoHormiga.OBRERA,new Posicion(x,y));
            //Añadir la hormiga nueva al diccionario(HashMap)
            hormigas.put(hormigaNueva.getIdHormiga(),hormigaNueva);
            //Colocar a la hormiga en la matriz
            mapa.prepararMapa(hormigas);

        }
    }





}
