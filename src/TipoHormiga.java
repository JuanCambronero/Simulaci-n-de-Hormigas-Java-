/**
 * <h1>TipoHormiga</h1>
 * Enumeración que define los distintos tipos de hormigas presentes en la simulación.
 * Cada tipo tiene asociado un símbolo y un nombre descriptivo.
 *
 * <p>Los tipos disponibles son:</p>
 * <ul>
 *   <li><b>OBRERA</b>: Se encarga de recolectar recursos y moverse por el mapa.</li>
 *   <li><b>GUERRERA</b>: (Reservado para futuras versiones) Defenderá el hormiguero.</li>
 *   <li><b>REINA</b>: (Reservado para futuras versiones) Representa la hormiga reina del hormiguero.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Juan Cambronero</p>
 * <p><b>Versión:</b> 1.0</p>
 */
public enum TipoHormiga {
    /** Hormiga obrera: realiza tareas básicas de exploración y movimiento */
    OBRERA('O',"Obrera"),
    /** Hormiga guerrera: destinada a la defensa del hormiguero (no implementada) */
    GUERRERA('G',"Guerrera"),
    /** Hormiga reina: núcleo del hormiguero (no implementada) */
    REINA('R',"Reina");

    //CONSTANTES
    /** Símbolo asociado al tipo de hormiga (usado para su representación en el mapa) */
    private final char simbolo;
    /** Nombre completo del tipo de hormiga */
    private final String nombre;

    //CONSTRUCTOR
    /**
     * Constructor privado del enumerado TipoHormiga.
     *
     * @param simbolo Carácter que representa el tipo de hormiga en el mapa.
     * @param nombre  Nombre descriptivo del tipo de hormiga.
     */
    TipoHormiga(char simbolo, String nombre) {
        this.simbolo = simbolo;
        this.nombre = nombre;
    }

    //METODOS
    /**
     * Devuelve el símbolo asociado al tipo de hormiga.
     *
     * @return Carácter que representa gráficamente a la hormiga.
     */
    public char getSimbolo() {
        return simbolo;
    }
    /**
     * Devuelve el nombre completo del tipo de hormiga.
     *
     * @return Nombre descriptivo del tipo.
     */
    public String getNombre() {
        return nombre;
    }
}