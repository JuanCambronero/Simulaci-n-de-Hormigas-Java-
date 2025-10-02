public enum TipoHormiga {
    OBRERA('O',"Obrera"),
    GUERRERA('G',"Guerrera"),
    REINA('R',"Reina");

    //CONSTANTES
    private final char simbolo;
    private final String nombre;

    //CONSTRUCTOR
    TipoHormiga(char simbolo, String nombre) {
        this.simbolo = simbolo;
        this.nombre = nombre;
    }

    //METODOS
    public char getSimbolo() {
        return simbolo;
    }

    public String getNombre() {
        return nombre;
    }
}