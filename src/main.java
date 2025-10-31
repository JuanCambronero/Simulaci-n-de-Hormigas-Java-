import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal del programa "Simulador de Hormigas".
 *
 * <p>Inicia la simulación creando un objeto {@link SimuladorHormigas}
 * y ejecutándolo cuando el usuario lo indica.</p>
 *
 * <p>Esta clase representa el punto de entrada del proyecto y se encarga
 * únicamente de inicializar los componentes principales.</p>
 *
 * @author Juan
 * @version 1.0
 */
public class main {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // Inicialización del simulador
        SimuladorHormigas simuladorHormigas = new SimuladorHormigas(
                new Random(),
                new Mapa(),
                new HashMap<>(),
                true
        );

        // Presentación visual mejorada
        System.out.println("========================================");
        System.out.println("     🐜  SIMULADOR DE HORMIGAS  🐜");
        System.out.println("========================================");
        System.out.println(" Presione ENTER para iniciar la simulación...");

        // Espera la entrada del usuario
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        // Inicio de la simulación
        System.out.println("\nIniciando simulación...");
        simuladorHormigas.ejecutar();
    }
}
