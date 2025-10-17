import java.util.HashMap;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        Mapa mapa = new Mapa();


        // Crear hormigas
        Hormiga h1 = new HormigaObrera("H1", TipoHormiga.OBRERA, new Posicion(0, 0));
        Hormiga h2 = new HormigaObrera("H2", TipoHormiga.OBRERA, new Posicion(3, 2));
        Hormiga h3 = new HormigaObrera("H3", TipoHormiga.OBRERA, new Posicion(4, 4));

        // Guardarlas en un HashMap
        HashMap<String, Hormiga> hormigas = new HashMap<>();
        hormigas.put(h1.getIdHormiga(), h1);
        hormigas.put(h2.getIdHormiga(), h2);
        hormigas.put(h3.getIdHormiga(), h3);


    }
}
