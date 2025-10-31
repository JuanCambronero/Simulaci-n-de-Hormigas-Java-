/**
 * Representa una hormiga obrera dentro de la colonia.
 *
 * <p>Las hormigas obreras heredan el comportamiento básico de {@link Hormiga},
 * incluyendo su movimiento aleatorio y su ejecución en un hilo independiente.</p>
 *
 * @author Juan
 * @version 1.0
 */
public class HormigaObrera extends Hormiga {
    //CONSTRUCTOR
    /**
     * Crea una nueva hormiga obrera con los datos proporcionados.
     *
     * @param idHormiga identificador único de la hormiga
     * @param tipo tipo de hormiga (debería ser {@code TipoHormiga.OBRERA})
     * @param posicion posición inicial de la hormiga en el mapa
     */
    public HormigaObrera(String idHormiga, TipoHormiga tipo, Posicion posicion) {
        super(idHormiga, tipo, posicion);
    }

}
