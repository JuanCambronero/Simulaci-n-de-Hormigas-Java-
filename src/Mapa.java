import java.util.HashMap;

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

    //CONSTRUCTO - 0(n)
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
    public Posicion getHormiguero() {
        return hormiguero;
    }
    //Desde este metodo se comprueba si la posición está dentro de los límites llamando al metodo de posicion
    public boolean dentroLimites(Posicion posicion) {

        return posicion.dentroLimites(ancho, alto);
    }
    //Este metodo muestra el mapa por pantalla - O(n²)
    public synchronized void mostarMapa() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                System.out.print(this.mapa[i][j]);//Imprimir el mapa recorriendo todas las posiciones de la matriz
            }
            System.out.println();
        }

    }
    //Este metodo prepara el mapa para ser mostrado colacando cada hormiga y horigero en su posicion - O(n²)
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
