import java.util.HashMap;
import java.util.Random;

public class main {
    public static void main(String[] args) {


        SimuladorHormigas simuladorHormigas = new SimuladorHormigas(new Random(),new Mapa(),new HashMap<>(), true);
        simuladorHormigas.ejecutar();


    }
}
