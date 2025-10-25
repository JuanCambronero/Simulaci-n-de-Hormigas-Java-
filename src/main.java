import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        SimuladorHormigas simuladorHormigas = new SimuladorHormigas(new Random(),new Mapa(),new HashMap<>(), true);
        System.out.println("-----Simulador Hormigas-----");
        System.out.println("Pulse cualquier tecla para iniciar la simulación...");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextLine()) {

            simuladorHormigas.ejecutar();

        }
    }
}
