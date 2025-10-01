public enum TipoHormiga {
    OBRERA("O","Obrera"),
    GUERRERA("G","Guerrera"),
    REINA("R","Reina");

    //CONSTANTES
    private final String simbolo;
    private final String nombre;

    //CONSTRUCTOR
    TipoHormiga(String simbolo, String nombre) {
        this.simbolo = simbolo;
        this.nombre = nombre;
    }

    //METODOS
    public String getSimbolo() {
        return simbolo;
    }

    public String getNombre() {
        return nombre;
    }
}